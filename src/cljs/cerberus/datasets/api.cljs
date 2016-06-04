(ns cerberus.datasets.api
  (:refer-clojure :exclude [get list])
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [om.core :as om :include-macros true]
   [cerberus.api :as api]
   [cerberus.global :as global]
   [cerberus.alert :refer [alerts]]
   [cerberus.state :refer [set-state!]]
   [cerberus.multi-lang.entry :as ml]))

(def root :datasets)

(def server (global/get "datasets" "http://datasets.at/images"))

(defn list [data]
  (api/list data root))

(def get (partial api/get root))

(defn a-get [uuid success error]
  (assoc (alerts success error) :always #(get uuid)))

(defn delete [data uuid]
  (api/delete data root [uuid]
              (alerts (ml/t :datasets-api/dataset-del-succ) (ml/t :datasets-api/dataset-del-fail))))

(defn import [uuid]
  (api/post root [] {:url (str server "/" uuid)}
            (alerts (ml/t :datasets-api/dataset-import-start) (ml/t :datasets-api/dataset-import-fail))))

(defn from-vm [vm snapshot name version os desc]
  (let [payload {:vm vm
                 :snapshot snapshot
                 :config {:name name
                          :version version
                          :os os
                          :description desc}}]
    (api/post root [] payload
              (alerts (ml/t :datasets-api/dataset-import-start) (ml/t :datasets-api/datset-import-fail)))))

(defn add-nic [uuid nic desc]
  (api/put root [uuid :networks nic] {:description desc}
           (a-get uuid (ml/t :datasets-api/nic-add-succ) (ml/t :datasets-api/nic-add-fail))))

(defn delete-nic [uuid nic]
  (api/delete root [uuid :networks nic]
              (a-get uuid (ml/t :datasets-api/nic-del-succ) (ml/t :datasets-api/nic-del-fail))))
