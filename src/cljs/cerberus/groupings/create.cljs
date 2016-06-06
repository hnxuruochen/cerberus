(ns cerberus.groupings.create
  (:require
   [om.core :as om :include-macros true]
   [cerberus.create :as create]
   [cerberus.multi-lang.entry :as ml]))

(defn render [app]
  (reify
    om/IDisplayName
    (display-name [_]
      "groupingadd")
    om/IRenderState
    (render-state [_ _]
      (create/render
       app
       {:type :select :label (ml/t :groupings-create/type) :id "grouping-type" :key :type
        :options [[(ml/t :groupings-create/cluster) "cluster"] [(ml/t :groupings-create/stack) "stack"]] :default "cluster"}
       {:type :input :label (ml/t :groupings-create/name) :id "grouping-name" :key :name :validator #(not (empty? %))}))))
