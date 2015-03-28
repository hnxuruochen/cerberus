(ns jingles.utils
  (:require-macros [cljs.core.match.macros :refer [match]])
  (:require
   [cljs.core.match]
   [om-tools.dom :as d :include-macros true]
   [jingles.state]
   [om-bootstrap.table :refer [table]]
   [om-bootstrap.panel :as p]
   [om-bootstrap.grid :as g]
   [om-bootstrap.button :as b]
   [om-bootstrap.random :as r]))

(defn tr-color [e]
  (cond
    (> (:raised e) 0) "danger"
    (> (:confirmed e) 0) "warning"
    (> (:cleared e) 0) "info"
    :else "success"))

(defn goto [& page]
  (set! (.-hash js/location) (apply str "#" page)))

(defn state [e]
  (d/span
   "(" (r/label {:bs-style "danger"} (:raised e))
   "/" (r/label {:bs-style "warning"} (:confirmed e))
   "/" (r/label {:bs-style "success"} (:cleared e))
   ")"))

(defn a [fun e & content]
  (let [path (fun {:id (:id e)})]
    (d/a #js{:href path} (:name e) content)))


(defn child-list [hdr link-fn elements]
  (p/panel
   {:header hdr}
   (table
    {:striped? true :bordered? true :condensed? true :hover? true}
    (d/thead
     (d/tr
      (d/td "Name")
      (d/td "Alerts")
      (d/td "Confirmed")
      (d/td "Cleared")))
    (d/tbody
     (map
      #(d/tr
        #js{:className (tr-color %)}
        (d/td (a link-fn %))
        (d/td (:raised %))
        (d/td (:confirmed %))
        (d/td (:cleared %)))
      elements)))))

(defn main-list [hdr link-fn elements]
  (g/grid
   {}
   (g/row
    {}
    (g/col {:md 18}
           (child-list hdr link-fn elements)))))

(defn by-id [id]
  (. js/document (getElementById id)))

(defn val-by-id [id]
  (.-value (by-id id)))

(defn make-event [fun]
  (fn [event]
    (fun)
    (.stopPropagation event)
    (.preventDefault event)))

(defn initial-state [config]
  (reduce
   (fn [acc e]
     (-> acc
         (assoc-in [e :show] true)
         (assoc-in [e :order] (get-in config [:fields e :order] 0))))
   {}
   (keys (:fields config))))

(defn value-by-key [key element]
  (cond
    (keyword? key) (key element)
    (fn? key) (key element)
    (list? key) (get-in element (vec key))
    (vector? key) (get-in element (vec key))
    :else ""))

(defn grid-row [& body]
  (g/grid {} (g/row {} body)))

(defn str->int [v]
  (js/parseInt v))

(defn ip->int [ip]
  (let [parts (clojure.string/split ip #"\.")
        [a b c d] (map str->int parts)]
    (bit-or (* 16777216 a) (* 65536 b) (* 256 c) d)))


(defn menu-items [& items]
  (map-indexed
   (fn [idx data]
     (match
      data
      :divider (b/menu-item {:divider? true})
      [title target] (if (fn? target)
                       (b/menu-item {:key (inc idx) :on-click (make-event target)} title)
                       (b/menu-item {:key (inc idx) :href target} title))
      [title opts target] (if (fn? target)
                            (b/menu-item (merge {:key (inc idx) :on-click (make-event target)} opts) title)
                            (b/menu-item (merge {:key (inc idx) :href target} opts) title))))
   (filter boolean items)))

(defn vec-or-seq? [e]
  (or (vector? e) (seq? e)))

(defn path-vec [e]
  (if (vec-or-seq? e) e [e]))
