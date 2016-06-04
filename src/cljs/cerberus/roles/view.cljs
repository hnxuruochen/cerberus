(ns cerberus.roles.view
  (:require
   [om.core :as om :include-macros true]
   [om.dom :as dom :include-macros true]
   [om-tools.dom :as d :include-macros true]
   [om-bootstrap.table :refer [table]]
   [om-bootstrap.panel :as p]
   [om-bootstrap.grid :as g]
   [om-bootstrap.random :as r]
   [om-bootstrap.nav :as n]
   [om-bootstrap.input :as i]
   [cerberus.utils :refer [lg]]
   [cerberus.http :as http]
   [cerberus.api :as api]
   [cerberus.roles.api :refer [root] :as roles]
   [cerberus.view :as view]
   [cerberus.metadata :as metadata]
   [cerberus.permissions :as permissions]
   [cerberus.state :refer [set-state!]]
   [cerberus.fields :refer [fmt-bytes fmt-percent]]
   [cerberus.multi-lang.entry :as ml]))


(defn- k [local-keyword]
  (ml/t (keyword "roles-view" (name local-keyword))))

(defn render-home [data owner opts]
  (reify
    om/IRenderState
    (render-state [_ _]
      (r/well
       {}
       (g/row
        {}
        (g/col
         {:sm 6}
         (p/panel
          {:header (d/h3 (k :general))
           :list-group
           (lg
            "UUID"        (:uuid data)
            (k :permissions) (count (:permissions data)))})))))))

(def sections
  {""             {:key  1 :fn #(om/build render-home %2)                       :title (k :general)}
   "permissions"  {:key  2 :fn #(om/build permissions/render %1
                                          {:key (str (:uuid %2) "-permissions")
                                           :opts {:element %2 :grant roles/grant :revoke roles/revoke}}) :title (k :permissions)}
   "metadata"     {:key  3 :fn #(om/build metadata/render %2)    :title (k :metadata)}})

(def render
  (view/make root sections roles/get :name-fn :name))
