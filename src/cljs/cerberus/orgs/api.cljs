(ns cerberus.orgs.api
  (:refer-clojure :exclude [get list])
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [cerberus.api :as api]
   [cerberus.http :as http]
   [cerberus.alert :refer [alerts]]
   [cerberus.state :refer [set-state!]]
   [cerberus.multi-lang.entry :as ml]))

(def root :orgs)

(def list-fields
  "name,uuid")

(defn list [data]
  (api/list data root list-fields))

(def get (partial api/get root))

(defn a-get [uuid success error]
  (assoc (alerts success error) :always #(get uuid)))

(defn delete [data uuid]
  (api/delete data root [uuid] (alerts (ml/t :orgs-api/orgs-del-succ) (ml/t :orgs-api/orgs-del-fail))))

(defn delete-trigger [uuid trigger]
  (api/delete
   root [uuid :triggers trigger]
   (a-get uuid (ml/t :orgs-api/trigger-del-succ) (ml/t :orgs-api/trigger-del-fail))))

(defn add-trigger [uuid trigger payload]
  (api/post
   root [uuid :triggers trigger] payload
   (a-get uuid (ml/t :orgs-api/trigger-add-succ) (ml/t :orgs-api/trigger-add-fail))))

(defn dec-resource [uuid res val]
  (api/put
   root [uuid :resources res] {:dec val}
   (a-get uuid (ml/t :orgs-api/res-dec-succ) (ml/t :orgs-api/res-dec-fail))))

(defn set-net [uuid scope net]
  (api/put
   root [uuid :docker :networks scope] {:network net}
   (a-get uuid (ml/t :orgs-api/net-set-succ) (ml/t :orgs-api/net-set-fail))))

(defn inc-resource [uuid res val]
  (api/put
   root [uuid :resources res] {:inc val}
   (a-get uuid (ml/t :orgs-api/res-inc-succ) (ml/t :orgs-api/res-inc-fail))))

(defn delete-resource [uuid res]
  (api/delete
   root [uuid :resources res]
   (a-get uuid (ml/t :orgs-api/res-del-succ) (ml/t :orgs-api/res-del-fail))))
