(ns cerberus.roles.api
  (:refer-clojure :exclude [get list])
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [cerberus.api :as api]
   [cerberus.http :as http]
   [cerberus.alert :refer [alerts]]
   [cerberus.state :refer [set-state!]]
   [cerberus.multi-lang.entry :as ml]))

(def root :roles)

(def list-fields
  "uuid,name")

(defn list [data]
  (api/list data root list-fields))

(def get (partial api/get root))

(defn a-get [uuid success error]
  (assoc (alerts success error) :always #(get uuid)))

(defn delete [data uuid]
  (api/delete data root [uuid] (alerts (ml/t :roles-api/delete-succ) (ml/t :roles-api/delete-fail))))


(defn grant [uuid perm]
  (api/put root (concat [uuid :permissions] perm) {}
           (a-get uuid (ml/t :roles-api/grant-succ) (ml/t :roles-api/grant-fail))))

(defn revoke [uuid perm]
  (api/delete root (concat [uuid :permissions] perm)
              (a-get uuid (ml/t :roles-api/revoke-succ) (ml/t :roles-api/revoke-fail))))
