(ns cerberus.clients.api
  (:refer-clojure :exclude [get list])
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [cerberus.api :as api]
   [cerberus.alert :refer [alerts]]
   [cerberus.state :refer [set-state!]]
   [cerberus.multi-lang.entry :as ml]))

(def root :clients)

(def list-fields
  "name,uuid")

(defn list [data]
  (api/list data root list-fields))

(def get (partial api/get root))

(defn a-get [uuid success error]
  (assoc (alerts success error) :always #(get uuid)))

(defn delete [data uuid]
  (api/delete data root [uuid] (alerts (ml/t :clients-api/client-delete-succ) (ml/t :clients-api/client-delete-fail))))

(defn grant [uuid perm]
  (api/put root (concat [uuid :permissions] perm) {}
           (a-get uuid (ml/t :clients-api/grant-succ) (ml/t :clients-api/grant-fail))))

(defn revoke [uuid perm]
  (api/delete root (concat [uuid :permissions] perm)
              (a-get uuid (ml/t :clients-api/revoke-succ) (ml/t :clients-api/revoke-fail))))

(defn change-secret [uuid secret]
  (api/put root [uuid] {:secret secret}
           (alerts (ml/t :clients-api/secret-succ) (ml/t :clients-api/secret-fail))))

(defn add-uri [uuid uri]
  (api/post root [uuid :uris] {:uri uri}
            (a-get uuid (ml/t :clients-api/uri-add-succ) (ml/t :clients-api/uri-add-fail))))

(defn delete-uri [uuid uri-id]
  (api/delete root [uuid :uris uri-id]
              (a-get uuid (ml/t :clients-api/uri-del-succ) (ml/t :clients-api/uri-del-fail))))
