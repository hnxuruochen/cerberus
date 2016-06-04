(ns cerberus.roles.create
  (:require
   [om.core :as om :include-macros true]
   [cerberus.create :as create]
   [cerberus.multi-lang.entry :as ml]))

(defn render [app]
  (reify
    om/IDisplayName
    (display-name [_]
      "addrolec")
    om/IRenderState
    (render-state [_ _]
      (create/render
       app
       {:type :input :label (ml/t :roles-create/name) :id "role-name" :key :name}))))
