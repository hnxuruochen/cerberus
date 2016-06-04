(ns cerberus.clients.create
  (:require
   [om.core :as om :include-macros true]
   [cerberus.create :as create]
   [cerberus.multi-lang.entry :as ml]))

(defn render [data]
  (reify
    om/IDisplayName
    (display-name [_]
      "addclientc")
    om/IRenderState
    (render-state [_ _]
      (create/render
       data
       {:type :input :label (ml/t :clients-create/name) :id "client-name" :key :client}
       {:type :input :label (ml/t :clients-create/secret) :id "client-pass" :key :secret}))))
