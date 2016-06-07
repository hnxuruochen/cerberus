(ns cerberus.users.create
  (:require
   [om.core :as om :include-macros true]
   [cerberus.create :as create]
   [cerberus.multi-lang.entry :as ml]))

(defn render [data]
  (reify
    om/IDisplayName
    (display-name [_]
      "adduserc")
    om/IRenderState
    (render-state [_ _]
      (create/render
       data
       {:type :input :label (ml/t :users-create/name) :id "user-name" :key :user}
       {:type :input :label (ml/t :users-create/password) :id "user-pass" :key :password}))))
