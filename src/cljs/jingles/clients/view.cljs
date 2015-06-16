(ns jingles.clients.view
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
   [jingles.utils :refer [goto grid-row display]]
   [jingles.http :as http]
   [jingles.api :as api]
   [jingles.clients.api :as clients]
   [jingles.clients.api :refer [root]]
   [jingles.permissions :as permissions]
   [jingles.metadata :as metadata]
   [jingles.state :refer [set-state!]]
   [jingles.fields :refer [fmt-bytes fmt-percent]]))



(defn render-home [app element]
  (pr-str element))

(def sections {""            {:key  1 :fn render-home      :title "General"}
               "permissions" {:key  2 :fn #(om/build permissions/render %2) :title "Permissions"}
               "metadata"    {:key  3 :fn #(om/build metadata/render %2)  :title "Metadata"}})

(defn render [data owner opts]
  (reify
    om/IDisplayName
    (display-name [_]
      "clientdetailc")
    om/IWillMount
    (will-mount [_]
      (clients/get (get-in data [root :selected]))
      )
    om/IInitState
    (init-state [_]
      {:edit-alias false}
      )
    om/IRenderState
    (render-state [_ state]
      (let [uuid (get-in data [root :selected])
            element (get-in data [root :elements uuid])
            section (get-in data [root :section])
            key (get-in sections [section :key] 1)]
        (pr section)
        (d/div
         {}
         (apply n/nav {:bs-style "tabs" :active-key key}
                (map
                 (fn [[section data]]
                   (n/nav-item {:key (:key data)
                                :href (str "#/clients/" uuid (if (empty? section) "" (str "/" section)))}
                               (:title data)))
                 (sort-by (fn [[section data]] (:key data)) (seq sections))))
         (if-let [f (get-in sections [section :fn] )]
           (do
             (pr element)
             (f data element))
           (goto (str "/clients/" uuid))))))))
