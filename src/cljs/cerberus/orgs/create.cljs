(ns cerberus.orgs.create
  (:require
   [om.core :as om :include-macros true]
   [cerberus.create :as create]
   [cerberus.multi-lang.entry :as ml]))

(defn render [data]
  (reify
    om/IDisplayName
    (display-name [_]
      "addorgc")
    om/IRenderState
    (render-state [_ _]
      (create/render
       data
       {:type :input :label (ml/t :orgs-create/name) :id "org-name" :key :name :validator #(not (empty? %2))}))))
