(ns cerberus.ipranges.view
  (:require
   [om.core :as om :include-macros true]
   [om-tools.dom :as d :include-macros true]
   [om-bootstrap.table :refer [table]]
   [om-bootstrap.panel :as p]
   [om-bootstrap.grid :as g]
   [om-bootstrap.random :as r]
   [om-bootstrap.nav :as n]
   [om-bootstrap.input :as i]
   [cerberus.view :as view]
   [cerberus.utils :refer [lg]]

   [cerberus.metadata :as metadata]
   [cerberus.ipranges.api :refer [root] :as ipranges]
   [cerberus.multi-lang.entry :as ml]))

(defn render-home [data owner opts]
  (reify
    om/IRenderState
    (render-state [_ _]
      (r/well
       {}
       (g/row
        {}
        (g/col
         {:xs 12 :sm 6}
         (p/panel
          {:header (d/h3 (ml/t :ipranges-view/gen-general-general))
           :list-group
           (lg
            (ml/t :ipranges-view/gen-general-uuid)            (:uuid data)
            (ml/t :ipranges-view/gen-general-network)            (:network data)
            (ml/t :ipranges-view/gen-general-gateway)            (:gateway data)
            (ml/t :ipranges-view/gen-general-netmask)            (:netmask data)
            (ml/t :ipranges-view/gen-general-vlan)            (:vlan data)
            (ml/t :ipranges-view/gen-general-tag)            (:tag data)
            )})
         )
        (g/col
         {:xs 12 :sm 6}
         (p/panel
          {:header (d/h3 (ml/t :ipranges-view/gen-ips-ips))
           :list-group
           (lg
            (ml/t :ipranges-view/gen-ips-free)            (count (:free data))
            (ml/t :ipranges-view/gen-ips-used)            (count (:used data)))})))))))

(defn render-ips [data owner opts]
  (reify
    om/IRenderState
    (render-state [_ _]
      (r/well
       {}
       (g/row
        {}
        (g/col
         {:xs 12 :sm 6}
         (p/panel
          {:header (ml/t :ipranges-view/ips-free)}
          (d/ul
           (map d/li (:free data)))))
        (g/col
         {:xs 12 :sm 6}
         (p/panel
          {:header (ml/t :ipranges-view/ips-used)}
          (d/ul
           (map d/li (:used data))))))))))

(def sections
  {""          {:key  1 :fn #(om/build render-home %2)     :title (ml/t :ipranges-view/sections-general)}
   "ips"       {:key  2 :fn #(om/build render-ips %2)      :title (ml/t :ipranges-view/sections-ips)}
   "metadata"  {:key  3 :fn #(om/build metadata/render %2) :title (ml/t :ipranges-view/sections-metadata)}})

(def render (view/make root sections ipranges/get :name-fn :name))
