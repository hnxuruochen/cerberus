(ns jingles.config
  (:refer-clojure :exclude [get print])
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [jingles.http :as http]
            [jingles.api :as api]
            [goog.net.cookies]
            [jingles.utils :refer [goto]]
            [jingles.state :refer [clear-state! app-state set-state! delete-state! update-state!]]))

(enable-console-print!)

(def updates (atom []))

(def metadata-root [:metadata :jingles])


(defn apply-updates [updates]
  (do
    (doall
     (map
      (fn [[[section uuid path] value]]
        (api/update-metadata section uuid path value))
      updates))
    []))

(defn add-update [updates path value]
  (conj (vec (filter #(not= (first %) path) updates)) [path value]))

(defn flush! []
  (swap! updates apply-updates))


(defn load []
  (go (let [resp (<! (http/get "sessions"))]
        (if (= 200 (:status resp))
          (let [conf (get-in (:body resp) metadata-root)
                uuid (:uuid (:body resp))]
            (set-state! :config conf)
            (set-state! :user uuid)
            conf)))))

(defn login [token expires-in]
  (do
    (.set goog.net.cookies "token" token expires-in)
    (set-state! :token token)
    (load)
    (goto)))

(defn logout []
  (flush!)
  (.remove goog.net.cookies "token")
  (clear-state!))


(defn clear []
  (go
    (let [req (<! (http/delete (str "users/" (:user @app-state) "/metadata/jingles")))]
      (logout))))

(defn write! [path value]
  (let [path (if (vector? path) path [path])]
    (if-let [uuid (:user @app-state)]
      (swap! updates add-update [:users uuid (vec (concat [:jingles] path))] value))
    (set-state! (vec (concat [:config] path)) value)
    value))

(defn get
  ([path default]
   (let [path (if (vector? path) path [path])
         v (get-in @app-state (concat [:config] path) :no-value-set)]
       (if (= v :no-value-set)
         (do
           (write! path default)
           default)
         v)))
  ([path]
   (let [path (if (vector? path) path [path])]
     (get-in @app-state (concat [:config] path)))))

(defn update! [path update-fn]
  (write! path (update-fn (get path))))

(defn delete! [path]
  (let [uuid (:user @app-state)
        path (if (vector? path) path [path])]
    (api/delete-metadata :users uuid (concat [:jingles] path))
    (if (> (count path) 1)
      (let [key (last path)
            path (butlast path)]
        (api/delete-metadata :users uuid path)
        (set-state! (vec (concat [:config] path))
                    (dissoc (get path) key)))
      (update-state! [:config] #(dissoc % (first path))))))


(defn print [] (pr (get-in @app-state [:config])))

(if-let [token (.get goog.net.cookies "token")]
  (do (set-state! :token token)
      (load)))

(js/setInterval flush! 10000)

