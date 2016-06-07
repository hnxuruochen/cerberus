(ns cerberus.users.api
  (:refer-clojure :exclude [get list])
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [clojure.string :refer [join]]
   [cerberus.api :as api]
   [cerberus.http :as http]
   [cerberus.alert :refer [alerts]]
   [cerberus.utils :refer [initial-state make-event]]
   [cerberus.state :refer [set-state! delete-state!]]
   [cerberus.multi-lang.entry :as ml]))

(def root :users)

(def list-fields
  "uuid,name,org")

(defn list [data]
  (api/list data root list-fields))

(defn get [uuid]
  (api/get root uuid))

(defn a-get [uuid success error]
  (assoc (alerts success error) :always #(get uuid)))

(defn delete [data uuid]
  (api/delete data root [uuid]
              (alerts (ml/t :users-api/delete-user-succeed) (ml/t :users-api/delete-user-failed))))

(defn changepass [uuid newpass]
  (api/put root [uuid] {:password newpass}
           (alerts (ml/t :users-api/change-password-succeed) (ml/t :users-api/change-password-failed))))

(defn grant [uuid perm]
  (api/put root (concat [uuid :permissions] perm) {}
           (a-get uuid (ml/t :users-api/permission-granted) (ml/t :users-api/grant-permission-failed))))

(defn revoke [uuid perm]
  (api/delete root (concat [uuid :permissions] perm)
              (a-get uuid (ml/t :users-api/revoke-permission-succeed) (ml/t :users-api/revoke-permission-failed))))

(defn revoke-token [uuid token]
  (api/delete root [uuid :tokens token]
              (a-get uuid (ml/t :users-api/revoke-token-succeed) (ml/t :users-api/revoke-token-failed))))

(defn add-sshkey [uuid keyname keydata]
  (api/put root [uuid :keys] {keyname keydata}
           (a-get uuid (ml/t :users-api/add-ssh-key-succeed) (ml/t :users-api/add-ssh-key-failed))))

(defn add-yubikey [uuid keyid]
  (api/put root [uuid :yubikeys] {:otp keyid}
           (a-get uuid (ml/t :users-api/add-yubi-key-succeed) (ml/t :users-api/add-yubi-key-failed))))

(defn delete-sshkey [uuid key-name]
  (api/delete root (concat [uuid :keys key-name])
              (a-get uuid (ml/t :users-api/removed-ssh-key-succeed) (ml/t :users-api/removed-ssh-key-failed))))

(defn delete-yubikey [uuid key-id]
  (api/delete root (concat [uuid :yubikeys key-id])
              (a-get uuid (ml/t :users-api/emoved-yubi-key-succeed) (ml/t :users-api/emoved-yubi-key-failed))))

(defn add-role [uuid role]
  (api/put root [uuid :roles role] {}
           (a-get uuid (ml/t :users-api/add-role-succeed) (ml/t :users-api/add-role-failed))))

(defn remove-role [uuid role]
  (api/delete root [uuid :roles role]
              (a-get uuid (ml/t :users-api/remove-role-succeed) (ml/t :users-api/remove-role-failed))))

(defn add-org [uuid org]
  (api/put root [uuid :orgs org] {}
           (a-get uuid (ml/t :users-api/organisation-joined) (ml/t :users-api/organisation-join-failed))))

(defn active-org [uuid role]
  (api/put root [uuid :orgs role] {:active true}
           (a-get uuid (ml/t :users-api/active-organisation-succeed) (ml/t :users-api/active-organisation-failed))))

(defn remove-org [uuid role]
  (api/delete root [uuid :orgs role]
              (a-get uuid (ml/t :users-api/remove-org-succeed) (ml/t :users-api/remove-org-failed))))


(defn set-metadata [uuid path value]
  (api/update-metadata root uuid path value))
