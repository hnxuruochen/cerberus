(ns cerberus.hypervisors
  (:refer-clojure :exclude [get list])
  (:require
   [om.core :as om :include-macros true]
   [om-tools.dom :as d :include-macros true]
   [cerberus.list :as jlist]
   [cerberus.hypervisors.api :refer [root] :as hypervisors]
   [om-bootstrap.random :as r]
   [cerberus.del :as del]
   [om-bootstrap.button :as b]
   [cerberus.hypervisors.view :as view]
   [cerberus.fields :refer [mk-config]]
   [cerberus.utils :refer [initial-state make-event str->int]]
   [cerberus.state :refer [set-state!]]
   [cerberus.multi-lang.entry :as ml]))

(defn actions [{uuid :uuid}]
  [(del/menue-item uuid)])


(def config
  (mk-config
   root (ml/t :hypervisors/hypervisors) actions
   :name {:title (ml/t :hypervisors/name) :key :alias :order -20}
   :version {:title (ml/t :hypervisors/version) :key :version :order 1}
   :os-version {:title (ml/t :hypervisors/os-ver) :key [:sysinfo (keyword "Live Image")] :order 2}
   :host {:title (ml/t :hypervisors/host) :key :host :order 3 :show false}
   :last-seen {:title (ml/t :hypervisors/last-seen) :key :last_seen
               :sort-key (fn [h] (* -1  (:last_seen h)))
               :type [:ago :s] :order 4}
   :uptime {:title (ml/t :hypervisors/uptime) :key (fn [h] (str->int (get-in h [:sysinfo (keyword "Boot Time")])))
            :sort-key (fn [h] (* -1 (str->int (get-in h [:sysinfo (keyword "Boot Time")]))))
            :type [:ago :s] :order 5}
   :used-men {:title (ml/t :hypervisors/used-mem) :key [:resources :provisioned-memory]
              :type [:bytes :mb] :order 6}
   :reserved-men {:title (ml/t :hypervisors/reserved-mem) :key [:resources :reserved-memory]
                  :type [:bytes :mb] :order 7}
   :free-men {:title (ml/t :hypervisors/free-mem) :key [:resources :free-memory]
              :type [:bytes :mb]  :order 8}))

(set-state! [root :fields] (initial-state config))

(defn render [data owner opts]
  (reify
    om/IDisplayName
    (display-name [_]
      "hypervisorlistc")
    om/IWillMount
    (will-mount [this]
      (om/update! data [root :filter] "")
      (om/update! data [root :filted] [])
      (om/update! data [root :sort] {})
      (hypervisors/list data))
    om/IRenderState
    (render-state [_ _]
      (condp = (:view data)
        :list (del/with-delete
                data root :alias hypervisors/delete
                (om/build jlist/view data {:opts {:config config}}))
        :show (om/build view/render data {})))))
