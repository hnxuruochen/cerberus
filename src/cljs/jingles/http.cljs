(ns jingles.http
  (:refer-clojure :exclude [get])
  (:require [cljs-http.client]
            [cljs-http.core]
            [jingles.state :refer [app-state]]))

(enable-console-print!)

(defn api [url]
  (str "/api/0.2.0/" url))

(defn default-headers []
  {"Accept" "application/json"
   "Authorization" (str "Bearer " (:token @app-state))})

(defn get
  "Like #'request, but sets the :method and :url as appropriate."
  [url & [hdrs req]]
  (let [hdrs (merge hdrs (default-headers))]
    (cljs-http.client/get (api url) (assoc req :headers hdrs))))


(defn post
  "Like #'request, but sets the :method and :url as appropriate."
  [url & [req]]
  (cljs-http.client/post (api url) (merge req {:headers {"Accept" "application/json"
                                                         "Authorization" (str "Bearer " (:token @app-state))}})))

(defn delete
  "Like #'request, but sets the :method and :url as appropriate."
  [url & [req]]
  (cljs-http.client/delete (api url) (merge req {:headers {"Accept" "application/json"
                                                           "Authorization" (str "Bearer " (:token @app-state))}})))
