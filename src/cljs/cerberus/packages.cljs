(ns cerberus.packages
  (:refer-clojure :exclude [get list])
  (:require
   [om.core :as om :include-macros true]
   [om-bootstrap.random :as r]
   [cerberus.del :as del]
   [om-tools.dom :as d :include-macros true]
   [cerberus.list :as jlist]
   [cerberus.packages.view :as view]
   [cerberus.packages.api :refer [root] :as packages]
   [cerberus.utils :refer [initial-state]]
   [cerberus.state :refer [set-state!]]
   [cerberus.fields :refer [mk-config]]))

(defn clone-pkg [{raw :raw}]
  (let [package (-> raw
                    (assoc :org (into {}  (map (fn [[k v]] [(name k) v]) (:org_resources raw))))
                    (dissoc :org_resources))]
    (set-state!
     [:add] {:view-section :packages,
             :content {:data package
                       :view package},
             :section :packages,
             :maximized true})))

  (defn actions [{uuid :uuid :as pkg}]
    [(del/menue-item uuid)
     ["Clone" #(clone-pkg pkg)]])

  (def config (mk-config root "Packages" actions
                         :cpu_cap {:title "CPU" :key :cpu_cap :type :percent}
                         :quota {:title "Quota" :key :quota :type [:bytes :gb]}
                         :ram {:title "RAM" :key :ram :type [:bytes :mb]}))

  (set-state! [root :fields] (initial-state config))

  (defn render [data owner opts]
    (reify
      om/IDisplayName
      (display-name [_]
        "datasetviewc")
      om/IWillMount
      (will-mount [_]
        (om/update! data [root :filter] "")
        (om/update! data [root :filted] [])
        (om/update! data [root :sort] {})
        (packages/list data))
      om/IRenderState
      (render-state [_ _]
        (condp = (:view data)
          :list (del/with-delete
                  data root :name packages/delete
                  (om/build jlist/view data {:opts {:config config}}))
          :show (om/build view/render data {})))))
