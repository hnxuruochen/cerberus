(ns cerberus.networks.api
  (:refer-clojure :exclude [get list])
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [cerberus.api :as api]
   [cerberus.alert :refer [alerts]]
   [cerberus.state :refer [set-state!]]
   [cerberus.multi-lang.entry :as ml]))

(def root :networks)

(def list-fields
  "uuid,name,ipranges")

(defn list [data]
  (api/list data root list-fields))

(def get (partial api/get root))

(defn a-get [uuid success error]
  (assoc (alerts success error) :always #(get uuid)))

(defn delete [data uuid]
  (api/delete data root [uuid] (alerts (ml/t :networks-api/network-del-succ) (ml/t :networks-api/network-del-fail))))

(defn add-iprange [uuid iprange]
  (api/put root [uuid :ipranges iprange] {}
           (a-get uuid (ml/t :networks-api/iprange-add-succ) (ml/t :networks-api/iprange-add-fail))))

(defn remove-iprange [uuid iprange]
  (api/delete root [uuid :ipranges iprange]
              (a-get uuid (ml/t :networks-api/iprange-del-succ) (ml/t :networks-api/iprange-del-fail))))
