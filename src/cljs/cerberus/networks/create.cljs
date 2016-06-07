(ns cerberus.networks.create
  (:require
   [om.core :as om :include-macros true]
   [cerberus.create :as create]
   [cerberus.multi-lang.entry :as ml]))

(defn render [app]
  (reify
    om/IDisplayName
    (display-name [_]
      "addnetworkc")
    om/IRenderState
    (render-state [_ _]
      (create/render
       app
       {:type :input :label (ml/t :networks-create/name) :id "network-name" :key :name :validator #(not (empty? %))}))))
