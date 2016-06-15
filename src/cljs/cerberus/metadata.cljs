(ns cerberus.metadata
  (:require
   [om.core :as om :include-macros true]
   [om-tools.dom :as d :include-macros true]
   [om-bootstrap.table :refer [table]]
   [om-bootstrap.grid :as g]
   [om-bootstrap.random :as r]
   [om-bootstrap.button :as b]
   [om-bootstrap.input :as i]
   [cerberus.api :as api]
   [cerberus.utils :refer [grid-row make-event menu-items ->state]]))

(defn display-value [path v]
  (cond
    (map? v) (d/ul
              (map
               (fn [[k v]]
                 (d/li
                  (name k) ": " (display-value (conj path k) v)))
               v))
    (number? v) v
    (string? v) ["\"" (clojure.string/replace v #"\"" "\\\"") "\""]
    (= true v) "true"
    (= false v) "false"
    (empty? v) ""
    :else [(type v) ": " (str v)]))

(defn render [data owner opts]
  (reify
    om/IDisplayName
    (display-name [_]
      "metadata-well")
    om/IRenderState
    (render-state [_ state]
      (r/well
       {}
       (g/row
        {}
        (g/col
         {:sm 3}
         (i/input {:type "text" :value (:key state) :placeholder "Key"
                   :on-change (->state owner :key)}))
        (g/col
         {:sm 7}
         (i/input {:type "text" :value (:val state) :placeholder "Value"
                   :on-change (->state owner :val)}))
        (g/col
         {:sm 2}
         (b/button
          {:bs-style "primary"
           :on-click #(api/update-metadata (:root opts) (:uuid data) [(:key state)] (:val state))
           :disabled? (or (empty? (:key state)) (empty? (:val state)))}
          "Add")))
        (g/row
         {}
         (g/col
          {:sm 10}
          (table
           {:condensed? true}
            (d/thead
             (d/tr
              (d/th "Key")
              (d/th "Value")))
            (d/tbody
             (map (fn [[k v]]
              (d/tr
               (d/td (name k))
               (d/td v)
               (d/td {:on-click #(api/delete-metadata (:root opts) (:uuid data) [k])} (r/glyphicon {:glyph "trash"}))))
             (:metadata data))))))))))
