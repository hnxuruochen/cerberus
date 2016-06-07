(ns cerberus.hypervisors.api
  (:refer-clojure :exclude [get list])
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [cerberus.api :as api]
   [cerberus.http :as http]
   [cerberus.alert :refer [alerts]]
   [cerberus.state :refer [set-state!]]
   [cerberus.multi-lang.entry :as ml]))

(def root :hypervisors)

(def list-fields
  "uuid,version,alias,resources,sysinfo,last_seen,host")

(defn list [data]
  (api/list data root list-fields))

(def get (partial api/get root))

(defn a-get [uuid success error]
  (assoc (alerts success error) :always #(get uuid)))

(defn delete [data uuid]
  (api/delete data root [uuid] (alerts (ml/t :hypervisors-api/hv-del-succ) (ml/t :hypervisors-api/hv-del-fail))))

(defn rename [uuid name]
  (api/put root [uuid :config] {:alias name} (a-get uuid (ml/t :hypervisors-api/hv-rename-succ) (ml/t :hypervisors-api/hv-rename-fail))))

(defn service-action [uuid service action]
  (api/put root [uuid :services] {:service service :action action}
           (a-get uuid (ml/t :hypervisors-api/svc-change-succ) (ml/t :hypervisors-api/svc-change-fail))))

(defn metrics [uuid]
  (go
    (let [resp (<! (http/get [root uuid :metrics]))]
      (condp = (:status resp)
        401 (api/check-login)
        503 (set-state! [root :elements uuid :metrics] :no-metrics)
        500 (set-state! [root :elements uuid :metrics] :no-metrics)
        200 (set-state! [root :elements uuid :metrics] (js->clj (:body resp)))
        nil))))

(defn set-config [uuid config]
  (api/put root [uuid :config] config
           (a-get uuid (ml/t :hypervisors-api/config-set-succ) (ml/t :hypervisors-api/config-set-fail))))

(defn set-characteristic [uuid char val]
  (api/put root [uuid :characteristics] {char val}
           (a-get uuid (ml/t :hypervisors-api/char-set-succ) (ml/t :hypervisors-api/char-set-fail))))

(defn delete-characteristic [uuid char]
  (api/delete root [uuid :characteristics char]
              (a-get uuid (ml/t :hypervisors-api/char-del-succ) (ml/t :hypervisors-api/char-del-fail))))
