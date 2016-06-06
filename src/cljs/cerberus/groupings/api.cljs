(ns cerberus.groupings.api
  (:refer-clojure :exclude [get list])
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [cerberus.api :as api]
   [cerberus.http :as http]
   [cerberus.alert :refer [alerts]]
   [cerberus.state :refer [set-state!]]
   [cerberus.multi-lang.entry :as ml]))

(def root :groupings)

(def list-fields
  "uuid,name,groupings,elements,type")

(defn list [data]
  (api/list data root list-fields))

(def get (partial api/get root))

(defn a-get [uuid success error]
  (assoc (alerts success error) :always #(get uuid)))

(defn delete [data uuid]
  (api/delete data root [uuid] (alerts (ml/t :groupings-api/grouping-del-succ) (ml/t :groupings-api/grouping-del-fail))))

(defn set-config [uuid conf val]
  (api/put root [uuid :config] {conf val}
           (a-get uuid (ml/t :groupings-api/config-set-succ) (ml/t :groupings-api/config-set-fail))))

(defn delete-config [uuid conf]
  (api/delete root [uuid :config conf]
              (a-get uuid (ml/t :groupings-api/config-del-succ) (ml/t :groupings-api/config-del-fail))))

(defn add-element [uuid grouping]
  (api/put root [uuid :elements grouping] {}
           (a-get uuid (ml/t :groupings-api/element-add-succ) (ml/t :groupings-api/element-add-fail))))

(defn remove-element [uuid grouping]
  (api/delete root [uuid :elements grouping]
              (a-get uuid (ml/t :groupings-api/element-del-succ) (ml/t :groupings-api/element-del-fail))))
