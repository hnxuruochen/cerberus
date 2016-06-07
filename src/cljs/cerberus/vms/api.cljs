
(ns cerberus.vms.api
  (:refer-clojure :exclude [get list])
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require

   [cerberus.api :as api]
   [cerberus.http :as http]
   [cerberus.howl :as howl]
   [cerberus.alert :as alert :refer [alerts]]
   [cerberus.utils :refer [initial-state make-event]]
   [cerberus.state :refer [set-state! update-state! app-state delete-state!]]
   [cerberus.multi-lang.entry :as ml]))

(def root :vms)

(def list-fields
  "alias,uuid,config,state,dataset,package,metadata,dataset,hypervisor,owner,vm_type,created_by,created_at")

(defn list [data]
  (api/list data root list-fields))

(defn get [uuid]
  (howl/join uuid)
  (api/get root uuid))

(defn a-get [uuid success error]
  (assoc (alerts success error) :always #(api/get root uuid)))


(defn metrics [uuid]
  (go
    (let [resp (<! (http/get [root uuid :metrics]))]
      (condp = (:status resp)
        401 (api/check-login)
        200 (set-state! [root :elements uuid :metrics] (js->clj (:body resp)))
        (set-state! [root :elements uuid :metrics] :no-metrics)))))

(defn delete [data uuid]
  (api/delete
   data root [uuid]
   (alerts (ml/t :vms-api/vm-del-succ) (ml/t :vms-api/vm-del-fail))))

(defn delete-hypervisor [uuid]
  (api/delete
   root [uuid :hypervisor]
   (a-get uuid (ml/t :vms-api/vm-rm-succ)
          (ml/t :vms-api/vm-rm-fail))))

(defn start [uuid]
  (api/put root [uuid :state] {:action :start}
           (alerts (ml/t :vms-api/vm-start) (ml/t :vms-api/vm-start-fail))))

(defn stop [uuid & [force]]
  (api/put root [uuid :state]
           (if force
             {:action :stop :force true}
             {:action :stop})
           (alerts (ml/t :vms-api/vm-stop) (ml/t :vms-api/vm-stop-fail))))


(defn reboot [uuid & [force]]
  (api/put root [uuid :state]
           (if force
             {:action :reboot :force true}
             {:action :reboot})
           (alerts (ml/t :vms-api/vm-reboot) (ml/t :vms-api/vm-reboot-fail))))

(defn snapshot [uuid comment]
  (api/post
   root [uuid :snapshots]
   {:comment comment}
   (alerts (ml/t :vms-api/snapshot-create) (ml/t :vms-api/snapshot-create-fail))))

(defn delete-snapshot [uuid snapshot]
  (api/delete root [uuid :snapshots snapshot]
              (alerts (ml/t :vms-api/snapshot-delete) (ml/t :vms-api/snapshot-delete-fail))))

(defn restore-snapshot
  ([uuid snapshot]
   (api/put root [uuid :snapshots snapshot] {:action "rollback"}
            (a-get uuid (ml/t :vms-api/snapshot-restore) (ml/t :vms-api/snapshot-restore-fail)))))

(defn _backup [uuid opts]
  (api/post root [uuid :backups]
            opts
            (assoc (alerts (ml/t :vms-api/backup-create) (ml/t :vms-api/backup-create-fail))
                   :always
                   (fn [resp]
                     (if (:success resp)
                       (set-state! [root :elements uuid] (:body resp)))))))

(defn add-fw-rule [uuid rule]
  (api/post root [uuid :fw_rules] rule
            (a-get uuid (ml/t :vms-api/firewall-add) (ml/t :vms-api/firewall-add-fail))))

(defn delete-fw-rule [uuid rule]
  (api/delete
   root [uuid :fw_rules rule]
   (a-get uuid (ml/t :vms-api/firewall-delete) (ml/t :vms-api/firewall-delete-fail))))

(defn backup
  ([uuid comment]
   (_backup uuid {:comment comment}))
  ([uuid parent comment]
   (_backup uuid {:comment comment :parent parent})))

(defn delete-backup [uuid backup]
  (api/delete root [uuid :backups backup]
              (assoc (alerts (ml/t :vms-api/backup-delete) (ml/t :vms-api/backup-delete-fail))
                     :always
                     (fn [resp]
                       (if (:success resp)
                         (delete-state! [root :elements uuid :backups backup]))))))

(defn restore-backup
  ([uuid backup]
   (api/put root [uuid :backups backup] {:action "rollback"}
            (a-get uuid (ml/t :vms-api/backup-restore) (ml/t :vms-api/backup-restore-fail))))
  ([uuid hypervisor backup]
   (api/put root [uuid :backups backup] {:action "rollback" :hypervisor hypervisor}
            (a-get uuid (ml/t :vms-api/backup-restore) (ml/t :vms-api/backup-restore-fail)))))

(defn service-action [uuid service action]
  (api/put root [uuid :services] {:service service :action action}
           (a-get uuid (ml/t :vms-api/service-change) (ml/t :vms-api/service-change-fail))))

(defn change-package [uuid package]
  (api/put root [uuid :package] {:package package}
           (a-get uuid (ml/t :vms-api/vm-pkg-change) (ml/t :vms-api/vm-pkg-change-fail))))

(defn change-config [uuid config]
  (api/put root [uuid :config] config
           (a-get uuid (ml/t :vms-api/vm-conf-change) (ml/t :vms-api/vm-pkg-conf-fail))))

(defn change-alias [uuid alias]
  (change-config uuid {:alias alias}))

(defn add-network [uuid network]
  (api/post root [uuid :nics] {:network network}
            (a-get uuid (ml/t :vms-api/network-add) (ml/t :vms-api/network-add-fail))))

(defn set-hostname [uuid nic hostname]
  (api/put root [uuid :hostname nic] {:hostname hostname}
           (a-get uuid (ml/t :vms-api/hostname-set) (ml/t :vms-api/hostname-set-fail))))

(defn delete-network [uuid mac]
  (api/delete root [uuid :nics mac]
              (a-get uuid (ml/t :vms-api/network-delete) (ml/t :vms-api/network-delete-fail))))

(defn make-network-primary [uuid mac]
  (api/put root [uuid :nics mac] {:primary true}
           (a-get uuid (ml/t :vms-api/network-marking) (ml/t :vms-api/network-marking))))

(def update-metadata (partial api/update-metadata root))

(defn set-owner [uuid org]
  (api/put root [uuid :owner] {:org org}
           (a-get uuid (ml/t :vms-api/owner-change) (ml/t :vms-api/owner-change-fail))))
