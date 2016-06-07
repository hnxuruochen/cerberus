(ns cerberus.packages.view
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
   [cerberus.packages.api :refer [root] :as packages]
   [cerberus.view :as view]
   [cerberus.services :as services]
   [cerberus.metadata :as metadata]
   [cerberus.state :refer [set-state!]]
   [cerberus.fields :refer [fmt-bytes fmt-percent]]
   [cerberus.multi-lang.entry :as ml]))


(defn or-auto [v]
  (or v (d/strong "auto")))

(defn render-home [data owner opts]
  (reify
    om/IRenderState
    (render-state [_ _]
      (r/well
       {}
       (g/row
        {}
        (g/col
         {:sm 4}
         (p/panel
          {:header (d/h3 (ml/t :packages-view/gen-general))
           :list-group
           (lg
            (ml/t :packages-view/gen-uuid)            (:uuid data)
            (ml/t :packages-view/gen-req)            (count (:requirements data)))}))

        (g/col
         {:sm 4}
         (p/panel
          {:header (d/h3 (ml/t :packages-view/gen-cpu-mem))
           :list-group
           (lg
            (ml/t :packages-view/gen-ram)            (:ram data)
            (ml/t :packages-view/gen-cpu-cap)            (:cpu_cap data)
            (ml/t :packages-view/gen-cpu-shares)            (or-auto (:cpu_shares data)))}))
        (g/col
         {:sm 4}
         (p/panel
          {:header (d/h3 (ml/t :packages-view/gen-disk))
           :list-group
           (lg
            (ml/t :packages-view/gen-disk-quota)            (:quota data)
            (ml/t :packages-view/gen-disk-compr)            (:compression data)
            (ml/t :packages-view/gen-disk-io-prio)            (or-auto (:io_priority data))
            (ml/t :packages-view/gen-disk-blk-size)            (or-auto (:block_size data)))}))
        (g/col
         {:sm 4}
         (p/panel
          {:header (d/h3 (ml/t :packages-view/gen-org-res))
           :list-group
           (apply lg (flatten (map (fn [[r v]] [(name r) v]) (:org_resources data))))})))))))

(defn render-requirement [{:keys [attribute condition value
                                  weight low high]}]
  (condp = weight
    (ml/t :packages-view/req-scale)    [(d/dt weight) (d/dd (d/strong attribute) (ml/t :packages-view/req-between) (d/strong  low) (ml/t :packages-view/req-and) (d/strong high))]
    (ml/t :pacakges-view/req-rand)    [(d/dt weight) (d/dd (ml/t :packages-view/req-between) (d/strong low) (ml/t :packages-view/req-and) (d/strong high))]
    [(d/dt weight) (d/dd (d/strong attribute) " " condition " " (d/strong value))]))

(defn build-reqs [reqs]
  (d/dl
   {}
   (map render-requirement reqs)))
(defn render-reqs [app owner opts]
  (reify
    om/IRenderState
    (render-state [_ _]
      (r/well
       {}
       (build-reqs app)))))

(def sections
  {""             {:key  1 :fn #(om/build render-home %2)      :title (ml/t :packages-view/sections-general)}
   "requirements" {:key  2 :fn #(om/build render-reqs (:requirements %2))  :title (ml/t :packages-view/sections-req)}
   "metadata"     {:key  3 :fn #(om/build metadata/render %2)  :title (ml/t :packages-view/sections-metadata)}})

(def render (view/make root sections packages/get :name-fn :name))
