(ns cerberus.vms.view
  (:require-macros [cljs.core.match.macros :refer [match]]
                   [cljs.core.async.macros :refer [go]])
  (:require
   [cljs.core.async :refer [<!]]
   [clojure.string :as cstr]
   [om.core :as om :include-macros true]
   [om-tools.dom :as d :include-macros true]
   [cerberus.del :as del]
   [om-bootstrap.table :refer [table]]
   [om-bootstrap.panel :as p]
   [om-bootstrap.grid :as g]
   [om-bootstrap.random :as r]
   [om-bootstrap.nav :as n]
   [om-bootstrap.input :as i]
   [om-bootstrap.button :as b]
   [cerberus.utils :refer [lg goto grid-row row ->state val-by-id str->int]]
   [cerberus.http :as http]
   [cerberus.api :as api]
   [cerberus.orgs.api :as orgs]
   [cerberus.hypervisors.api :as hypervisors]
   [cerberus.datasets.api :as datasets]
   [cerberus.services :as services]
   [cerberus.metadata :as metadata]
   [cerberus.vms.api :refer [root] :as vms]
   [cerberus.networks.api :as networks]
   [cerberus.ipranges.api :as ipranges]
   [cerberus.view :as view]
   [cerberus.packages.api :as packages]
   [cerberus.state :refer [app-state set-state!]]
   [cerberus.fields :as fields]
   [cerberus.metrics :as metrics]
   [cerberus.utils :refer [make-event menu-items]]
   [cerberus.fields :refer [fmt-bytes fmt-percent]]
   [cerberus.multi-lang.entry :as ml]))

(def token-path "sessions/one_time_token")

(defn open-with-ott [path]
  (go
    (let [response (<! (http/get token-path))]
      (if (= 200 (:status response))
        (let [ott (get-in response [:body :token])]
          (.open js/window (str path "&ott=" ott)))))))

(def sub-element (partial api/get-sub-element))

(defn get-package [element]
  (sub-element :packages :package [:name] element))

(defn get-dataset [element]
  (sub-element :datasets :dataset [:name] element))

(defn render-home [app owner opts]
  (reify
    om/IInitState
    (init-state [_]
      (let [uuid (get-in app [root :selected])]
        {:org (or (get-in app [root :elements uuid :owner])
                  "")
         :alias (get-in app [root :elements uuid :config :alias])}))
    om/IRenderState
    (render-state [_ state]
      (let [uuid (get-in app [root :selected])
            element (get-in app [root :elements uuid])
            conf (:config element)
            current-owner (:owner element)
            invalid-owner #{"" nil current-owner}
            orgs (get-in app [:orgs :elements])
            org (api/get-sub-element :orgs :owner identity element)
            package (api/get-sub-element :packages :package identity element)
            dataset (api/get-sub-element :datasets :dataset identity element)
            hypervisor (api/get-sub-element :hypervisors :hypervisor identity element)
            services (:services element)]
        (r/well
         {}
         (row
          (g/col
           {:md 8}
           (i/input
            {:type "text"
             :value (:alias state)
             :on-change (->state owner :alias)}))
          (g/col
           {:md :4}
           (b/button
            {:bs-style "primary"
             :className "pull-right fbutown"
             :on-click #(vms/change-alias uuid (:alias state))
             :disabled? (empty? (:alias state))}
            (ml/t :vms-view/change-alias))))
         (row
          (g/col
           {:md 8}
           (i/input
            {:type "select"
             :value (:org state)
             :on-change (->state owner :org)}
            (map (fn [[uuid e]]
                   (let [opts {:value uuid}
                         opts (if (= (:org state) uuid) (assoc opts :selected true) opts)]
                     (d/option opts (:name e))))
                 (sort-by #(cstr/lower-case (:name (second %))) (cons ["" {:name ""}] orgs)))))
          (g/col
           {:md :4}
           (b/button
            {:bs-style "primary"
             :className "pull-right fbutown"
             :on-click #(vms/set-owner uuid (:org state))
             :disabled? (boolean  (invalid-owner (:org state)))}
            (ml/t :vms-view/set-owner))))
         (row
          (g/col
           {:sm 6 :md 4}
           (p/panel
            {:header (d/h3 (ml/t :vms-view/general))
             :list-group
             (lg
              (ml/t :vms-view/uuid)       uuid
              (ml/t :vms-view/type)       (:type conf)
              (ml/t :vms-view/alias)      (:alias conf)
              (ml/t :vms-view/state)      (:state element)
              (ml/t :vms-view/created)    (:created_at conf)
              (ml/t :vms-view/hypervisor) (d/a {:href (str "#/hypervisors/" (:uuid hypervisor))} (:alias hypervisor))
              (ml/t :vms-view/owner)      (d/a {:href (str "#/orgs/" (:uuid org))} (:name org))
              (ml/t :vms-view/autoboot)   (:autoboot conf)
              (ml/t :vms-view/dataset)    (d/a {:href (str "#/datasets/" (:uuid dataset))} (:name dataset))
              (ml/t :vms-view/package)    (d/a {:href (str "#/packages/" (:uuid package))} (:name package))
              (ml/t :vms-view/services) (d/span (count (filter (fn [[_ state]] (= state "maintenance")) services)) "/"
                                 (count (filter (fn [[_ state]] (= state "online")) services)) "/"
                                 (count (filter (fn [[_ state]] (= state "disabled")) services))))}))
          (g/col
           {:sm 6 :md 4}
           (p/panel
            {:header (d/h3 (ml/t :vms-view/cpu-memory))
             :list-group
             (lg
              (ml/t :vms-view/cpu-shares) (:cpu_shares conf)
              (ml/t :vms-view/cpu-cap)    (-> (:cpu_cap conf) fmt-percent)
              (ml/t :vms-view/max-swap)   (->> (:max_swap conf) (fmt-bytes :b))
              (ml/t :vms-view/memory)     (->> (:ram conf) (fmt-bytes :mb)))}))
          (g/col
           {:sm 6 :md 4}
           (p/panel
            {:header (d/h3 (ml/t :vms-view/disk))
             :list-group
             (lg
              (ml/t :vms-view/quota)         (->> (:quota conf) (fmt-bytes :gb))
              (ml/t :vms-view/io-priority)  (:zfs_io_priority conf)
              (ml/t :vms-view/backups)       (count (:backups element))
              (ml/t :vms-view/snapshots)     (count (:snapshots element)))}))
          (g/col
           {:sm 6 :md 4}
           (p/panel
            {:header (d/h3 (ml/t :vms-view/networking))
             :list-group
             (lg
              (ml/t :vms-view/hostname)       (:hostname conf)
              (ml/t :vms-view/dns-domain)     (:dns_domain conf)
              (ml/t :vms-view/resolvers)      (cstr/join ", " (:resolvers conf))
              (ml/t :vms-view/firewall-rules) (count (:fw_rules conf))
              (ml/t :vms-view/ips) (cstr/join ", " (map :ip (:networks conf))))}))))))))

(defn render-imaging [data owner opts]
  (reify
    om/IInitState
    (init-state [_]
      {})
    om/IRenderState
    (render-state [_ state]
      (let [invalid (or
                     (empty? (:name state))
                     (empty? (:version state))
                     (empty? (:os state))
                     (empty? (:desc state)))]
        (r/well
         {}
         (g/row
          {}
          (g/col
           {}
           (d/p
            (ml/t :vms-view/image-info-0)
            (d/ol
             (d/li (ml/t :vms-view/image-info-1))
             (d/li (ml/t :vms-view/image-info-2))
             (d/li (ml/t :vms-view/image-info-3))
             (d/li (ml/t :vms-view/image-info-4))
             (d/li (ml/t :vms-view/image-info-5)))
             (ml/t :vms-view/image-info-6) (d/a {:href "#"} (ml/t :vms-view/image-info-7)) (ml/t :vms-view/image-info-8))))
         (g/row
          {}
          (g/col
           {:xs 8}
           (i/input {:type "text" :placeholder (ml/t :vms-view/name)
                     :value (:name state) :on-change (->state owner :name)}))
          (g/col
           {:xs 2}
           (i/input {:type "text" :placeholder (ml/t :vms-view/version)
                     :value (:version state) :on-change (->state owner :version)}))
          (g/col
           {:xs 2}
           (i/input {:type "text" :placeholder (ml/t :vms-view/os)
                     :value (:os state) :on-change (->state owner :os)}))
          (g/col
           {:xs 12}
           (i/input {:type "text" :placeholder (ml/t :vms-view/description)
                     :value (:desc state) :on-change (->state owner :desc)})))
         (g/row
          {}
          (g/col
           {}
           (table
            {}
            (d/thead
             (d/tr
              (d/th (ml/t :vms-view/name))
              (d/th (ml/t :vms-view/date))
              (d/th (ml/t :vms-view/size))
              (d/th "")))
            (d/tbody
             (map (fn [[uuid {comment :comment timestamp :timestamp size :size}]]
                    (d/tr
                     (d/td comment)
                     (d/td (str (js/Date. (/ timestamp 1000))))
                     (d/td (fmt-bytes :b size))
                     (d/td (b/button
                            {:bs-style "primary"
                             :bs-size "small"
                             :className "pull-right fbutown"
                             :on-click #(datasets/from-vm (:uuid data) uuid (:name state) (:version state) (:os state) (:descs state))
                             :disabled? invalid}
                            (ml/t :vms-view/create-image)))))
                  (filter
                   #(= "completed" (:state (second %)))
                   (sort-by #(:timestamp (second %)) (:snapshots data)))))))))))))

(defn render-logs [data owner opts]
  (reify
    om/IRenderState
    (render-state [_ _]
      (let [logs (:log data)]
        (r/well
         {}
         (table
          {:striped? true :condensed? true :hover? true :responsive? true :class "logs-list"}
          (d/thead
           {:striped? false}
           (d/tr
            {}
            (d/td {} (ml/t :vms-view/date))
            (d/td {} (ml/t :vms-view/entry))))
          (d/tbody
           {}
           (map
            (fn [{date :date log :log}]
              (d/tr
               (d/td (str (js/Date. (/ date 1000))))
               (d/td log)))
            logs))))))))

(defn group-li [& args]
  (d/li {:class "list-group-item"} args))

(defn render-network
  [{interface :interface
    tag       :nic_tag
    ip        :ip
    netmask   :netmask
    gateway   :gateway
    mac       :mac
    primary   :primary}
   owner {:keys [uuid disabled iprs iprange-map full-nets network-map hostname-map]}]
  (reify
    om/IInitState
    (init-state [_]
      {:hostname (hostname-map ip)})
    om/IRenderState
    (render-state [_ state]
      (g/col
       {:md 4}
       (del/state-modal state owner #(get-in % [:delete :name]) #(vms/delete-network uuid %))
       (p/panel
        {:header
         [interface
          (if (not primary)
            (b/button
             {:bs-style "warning"
              :class "pull-right"
              :bs-size "small"
              :disabled? disabled
              :on-click
              #(vms/make-network-primary uuid mac)} (r/glyphicon {:glyph "check"})))
          (b/button
           {:bs-style "primary"
            :class "pull-right"
            :bs-size "small"
            :disabled? disabled
            :on-click
            #(do
               (om/set-state! owner [:delete :name] interface)
               (del/state-show owner mac))} (r/glyphicon {:glyph "trash"}))]
         :list-group
         (d/ul {:class "list-group"}
               (group-li (ml/t :vms-view/hostname-) (g/row
                                       {}
                                       (g/col {:md 10}
                                              (i/input {:type "text"
                                                        :on-change (->state owner :hostname)
                                                        :value (:hostname state)}))
                                       (g/col {:md 2}
                                              (b/button
                                               {:bs-style "primary"
                                                :class "pull-right"
                                                :bs-size "small"
                                                :on-click
                                                #(vms/set-hostname uuid interface (:hostname state))} (r/glyphicon {:glyph "pencil"})))))
               (group-li (ml/t :vms-view/network-)  (if-let [net (network-map ip)]
                                       (d/a {:href (str  "#/networks/" net)} (get-in full-nets [net :name]))))
               (group-li (ml/t :vms-view/ip-range-)
                         (if-let [ipr (iprange-map ip)]
                           (d/a {:href (str  "#/ipranges/" ipr)} (get-in iprs [ipr :name])) ""))
               (group-li (ml/t :vms-view/tag-)      tag)
               (group-li (ml/t :vms-view/ip-)       ip)
               (group-li (ml/t :vms-view/netmask-)  netmask)
               (group-li (ml/t :vms-view/gateway-)  gateway)
               (group-li (ml/t :vms-view/mac-)      mac)
               )})))))


(defn render-networks [app owner {uuid :uuid}]
  (reify
    om/IRenderState
    (render-state [state _]
      (let [data (get-in app [root :elements uuid])
            hostname-map (:hostname_mappings data)
            hostname-map (map (fn [[k v]] [(name k) v]) hostname-map)
            hostname-map (into {} hostname-map)
            iprange-map  (:iprange_mappings data)
            iprange-map  (map (fn [[k v]] [(name k) v]) iprange-map)
            iprange-map  (into {} iprange-map)
            network-map  (:network_mappings data)
            network-map  (map (fn [[k v]] [(name k) v]) network-map)
            network-map  (into {} network-map)
            full-nets (get-in app [:networks :elements])
            nets (sort-by :name (vals full-nets))
            iprs (get-in app [:ipranges :elements])
            disabled (not  (#{"stopped" "installed"} (:state data)))
            networks (get-in data [:config :networks])
            rows (partition 4 4 nil networks)]
        (r/well
         {}
         (row
          (g/col
           {:xs 4}
           (i/input
            {:type "select" :include-empty true :id "net-add"}
            (d/option)
            (map #(d/option {:value (:uuid %)} (:name %)) nets)))
          (g/col
           {:xs 2}
           (b/button {:bs-style "primary"
                      :disabled? disabled
                      :on-click #(vms/add-network uuid (val-by-id "net-add"))} (ml/t :vms-view/add))))
         (map (fn [row]
                (g/row nil
                       (om/build-all
                        render-network
                        row
                        {:opts
                         {:uuid  uuid
                          :parent owner
                          :render-network render-network
                          :disabled disabled
                          :iprs iprs
                          :iprange-map iprange-map
                          :full-nets full-nets
                          :network-map network-map
                          :hostname-map hostname-map}}))) rows))))))

(defn cmp-vals [package cmp-package val fmt]
  (if-let [cmp-vap (cmp-package val)]
    (let [val (if package (package val) 0)
          diff (- cmp-vap val)]
      (cond
        (> diff 0) [(fmt val) " (+" (fmt diff) ")"]
        (< diff 0) [(fmt val) " (" (fmt diff) ")"]
        :else [(fmt  val)]))
    [(if package (package val) 0)]))

(defn apply-fmt [fmt rest-fn]
  (rest fmt))

(defn render-package [app element]
  (let [current-package (:package element)
        vm (:uuid element)
        packages (get-in app [:packages :elements])
        package (get-in packages [current-package])
        cmp-pkg (get-in app [:tmp :pkg] {})
        cmp-vals (partial cmp-vals package cmp-pkg)]
    (r/well
     {}
     (row
      (g/col
       {:md 4}
       (p/panel
        {:header (if package (:name package) (ml/t :vms-view/custom))
         :list-group
         (d/ul {:class "list-group"}
               (group-li (ml/t :vms-view/cpu-)    (cmp-vals :cpu_cap fmt-percent))
               (group-li (ml/t :vms-view/memory-) (cmp-vals :ram (partial fmt-bytes :mb)))
               (group-li (ml/t :vms-view/quota-)  (partial cmp-vals :quota (partial fmt-bytes :gb))))}))
      (g/col
       {:md 8}
       (table
        {:class "pkg-list"}
        (d/thead
         {}
         (map d/td
              [(ml/t :vms-view/name) (ml/t :vms-view/cpu) (ml/t :vms-view/memory) (ml/t :vms-view/quota) (d/span {:class "pull-right"} (ml/t :vms-view/change))]))

        (apply d/tbody
               {}
               (map
                (fn [[uuid {name :name :as pkg}]]
                  (let [cmp #(let [v (if package (package %1) 0)]
                               (cond
                                 (> %2 v) (r/glyphicon {:glyph "chevron-up"})
                                 (< %2 v) (r/glyphicon {:glyph "chevron-down"})
                                 :else ""))
                        td (fn [v f] (d/td (f (pkg v)) (cmp v (pkg v))))
                        current (= uuid current-package)]
                    (d/tr
                     {:class (if current "current" "")
                      :on-mouse-over (fn [e] (set-state! [:tmp :pkg] pkg))
                      :on-mouse-leave (fn [e] (set-state! [:tmp :pkg] {}))}
                     (d/td name)
                     (td :cpu_cap fmt-percent)
                     (td :ram     #(fmt-bytes :mb %))
                     (td :quota   #(fmt-bytes :gb %))
                     (d/td (if (not current)
                             (r/glyphicon {:glyph "transfer"
                                           :class "pull-right"
                                           :alt "change package"
                                           :on-click #(vms/change-package vm uuid)}))))))
                packages))))))))

(defn snapshot-row  [owner vm [uuid {comment :comment timestamp :timestamp
                                     state :state size :size}]]
  (d/tr
   (d/td (name uuid))
   (d/td comment)
   (d/td (str (js/Date. (/ timestamp 1000))))
   (d/td (ml/t (keyword "vms-view" state)))
   (d/td (fmt-bytes :b size))
   (d/td {:class "actions no-carret"}
         (b/dropdown {:bs-size "xsmall" :title (r/glyphicon {:glyph "option-vertical"})
                      :on-click (make-event identity)}
                     (menu-items
                      [(ml/t :vms-view/roll-back) #(vms/restore-snapshot vm uuid)]
                      [(ml/t :vms-view/delete)    #(do
                                      (om/set-state! owner [:delete :name] comment)
                                      (del/state-show owner (name uuid)))])))))

(defn snapshot-table [state owner vm snapshots]
  (g/col
   {:md 11}
   (del/state-modal state owner #(get-in % [:delete :name]) #(vms/delete-snapshot vm %))
   (table
    {:id "snapshot-table"}
    (d/thead
     {}
     (d/td (ml/t :vms-view/uuid))
     (d/td (ml/t :vms-view/comment))
     (d/td (ml/t :vms-view/timestamp))
     (d/td (ml/t :vms-view/state))
     (d/td (ml/t :vms-view/size))
     (d/td {:class "actions"}))
    (apply d/tbody
           {}
           (map
            (partial snapshot-row owner vm)
            (sort-by (fn [[_ {t :timestamp}]] t) snapshots))))))

(defn render-snapshots [data owner opts]
  (reify
    om/IInitState
    (init-state [_]
      {:name ""})
    om/IRenderState
    (render-state [_ state]
      (r/well
       {}
       (row
        (g/col
         {:md 12}
         (i/input
          {:label (ml/t :vms-view/new-snapshot)}
          (row
           (g/col
            {:xs 10}
            (i/input {:type :text
                      :value (:name state)
                      :placeholder (ml/t :vms-view/snapshot-comment)
                      :on-change (->state owner :name)}))
           (g/col
            {:xs 2}
            (b/button {:bs-style "primary"
                       :wrapper-classname "col-xs-2"
                       :disabled? (empty? (:name state))
                       :on-click #(vms/snapshot (:uuid data) (:name state))} (ml/t :vms-view/create))))))
        (snapshot-table state owner (:uuid data) (:snapshots data)))))))


(defn show-state [state]
  (condp = state
    "uploading" (r/label {:bs-style "warning"} (ml/t (keyword "vms-view" state)))
    "completed" (r/label {:bs-style "success"} (ml/t (keyword "vms-view" state)))
    "failed" (r/label {:bs-style "danger"} (ml/t (keyword "vms-view" state)))
    (r/label {:bs-style "default"} state)))

(defn backup-row  [owner vm hypervisor
                   [uuid {comment :comment timestamp :timestamp
                          state :state old-size :size files :files}]]
  (let [size (reduce + (map #(:size (second %)) files))
        size (if (= 0 size) old-size size)]
    (d/tr
     (d/td (name uuid))
     (d/td comment)
     (d/td (str (js/Date. (/ timestamp 1000))))
     (d/td (show-state state))
     (d/td (fmt-bytes :b size))
     (d/td {:class "actions no-carret"}
           (b/dropdown {:bs-size "xsmall" :title (r/glyphicon {:glyph "option-vertical"})
                        :on-click (make-event identity)}
                       (menu-items
                        [(ml/t :vms-view/incremental) #(vms/backup vm uuid (val-by-id "backup-comment"))]
                        (if (and hypervisor (not (empty? hypervisor)))
                          [(ml/t :vms-view/restore) #(vms/restore-backup vm hypervisor uuid)]
                          [(ml/t :vms-view/roll-back) #(vms/restore-backup vm uuid)])

                        [(ml/t :vms-view/delete)    #(do
                                        (om/set-state! owner [:delete :name] comment)
                                        (del/state-show owner (name uuid)))]))))))

(defn backup-table [state owner vm hypervisor backups]
  (g/col
   {:md 11}
   (del/state-modal state owner #(get-in % [:delete :name]) #(vms/delete-backup vm %))
   (table
    {:id "backup-table"}
    (d/thead
     {}
     (d/td (ml/t :vms-view/uuid))
     (d/td (ml/t :vms-view/comment))
     (d/td (ml/t :vms-view/timestamp))
     (d/td (ml/t :vms-view/state))
     (d/td (ml/t :vms-view/size))
     (d/td {:class "actions"}))
    (apply d/tbody
           {}
           (map
            (partial backup-row owner vm hypervisor)
            (sort-by (fn [[_ {t :timestamp}]] t) backups))))))

(defn render-backups [app owner {:keys [uuid]}]
  (reify
    om/IInitState
    (init-state [_]
      {:name ""})
    om/IRenderState
    (render-state [_ state]
      (let [data (get-in app [root :elements uuid])]
        (r/well
         {}
         (if (not (empty? (:hypervisor data)))
           (row
            (g/col
             {:xs 12}
             (d/p
              (ml/t :vms-view/backup-info)
              (b/button {:bs-style "danger"
                         :disabled? (empty? (:backups data))
                         :on-click #(vms/delete-hypervisor (:uuid data))

                         :class "pull-right"} (ml/t :vms-view/backup-delete)))))
           (row
            (g/col {}
                   (d/p
                   (ml/t :vms-view/deploy-info))
                   (i/input {:type "select"
                             :value (:target state)
                             :on-change (->state owner :target)}
                            (d/option "")
                            (map
                             (fn [[h-uidd {alias :alias}]]
                               (d/option {:value h-uidd} alias))
                             (get-in app [:hypervisors :elements])))
                   )))
         (row

          (g/col
           {:md 12}
           (i/input
            {:label (ml/t :vms-view/new-backup)}
            (row
             (g/col
              {:xs 10}
              (i/input {:type :text
                        :placeholder (ml/t :vms-view/backup-comment)
                        :on-change (->state owner :name)
                        :value (:name state)
                        :id "backup-comment"}))
             (g/col {:xs 2}
                    (b/button {:bs-style "primary"
                               :wrapper-classname "col-xs-2"
                               :disabled? (empty? (:name state))
                               :on-click #(vms/backup (:uuid data) (:name state))} (ml/t :vms-view/create))))))
          (backup-table state owner uuid (:target state) (:backups data))))))))


(defn o-state! [owner id]
  (om/set-state! owner id (val-by-id (name id))))


(def icmp
  {"0"  {:name "Echo Reply" :codes {"0" "No Code"}}
   "3"  {:name "Destination Unreachable"
         :codes {"0"  "Net Unreachable"
                 "1"  "Host Unreachable"
                 "2"  "Protocol Unreachable"
                 "3"  "Port Unreachable"
                 "4"  "Fragmentation Needed and Don't Fragment was Set"
                 "5"  "Source Route Failed"
                 "6"  "Destination Network Unknown"
                 "7"  "Destination Host Unknown"
                 "8"  "Source Host Isolated"
                 "9"  "Communication with Destination Network is Administratively Prohibited"
                 "10" "Communication with Destination Host is Administratively Prohibited"
                 "11" "Destination Network Unreachable for Type of Service"
                 "12" "Destination Host Unreachable for Type of Service"
                 "13" "Communication Administratively Prohibited"
                 "14" "Host Precedence Violation"
                 "15" "Precedence cutoff in effect"}}
   "4"  {:name "Source Quench" :codes {"0" "No Code"}}
   "5"  {:name "Redirect"
         :codes {"0" "Redirect Datagram for the Network (or subnet)"
                 "1" "Redirect Datagram for the Host"
                 "2" "Redirect Datagram for the Type of Service and Network"
                 "3" "Redirect Datagram for the Type of Service and Host"}}
   "6"  {:name "Alternate Host Address" :codes {0 "Alternate Address for Host"}}
   "8"  {:name "Echo" :codes {"0" "No Code"}}
   "9"  {:name "Router Advertisement" :codes {"0" "No Code"}}
   "10" {:name "Router Selection" :codes {"0" "No Code"}}
   "11" {:name "Time Exceeded"
         :codes {"0" "Time to Live exceeded in Transit"
                 "1" "Fragment Reassembly Time Exceeded"}}
   "12" {:name "Parameter Problem"
         :codes {"0" "Pointer indicates the error"
                 "1" "Missing a Required Option"
                 "2" "Bad Length"}}
   "13" {:name "Timestamp" :codes {"0" "No Code"}}
   "14" {:name "Timestamp Reply" :codes {"0" "No Code"}}
   "15" {:name "Information Request" :codes {"0" "No Code"}}
   "16" {:name "Information Reply" :codes {"0" "No Code"}}
   "17" {:name "Address Mask Request" :codes {"0" "No Code"}}
   "18" {:name "Address Mask Reply" :codes {"0" "No Code"}}
   "30" {:name "Traceroute" :codes {"0" "No Code"}}
   "31" {:name "Datagram Conversion Error" :codes {"0" "No Code"}}
   "32" {:name "Mobile Host Redirect" :codes {"0" "No Code"}}
   "33" {:name "IPv6 Where-Are-You" :codes {"0" "No Code"}}
   "34" {:name "IPv6 I-Am-Here" :codes {"0" "No Code"}}
   "35" {:name "Mobile Registration Request" :codes {"0" "No Code"}}
   "36" {:name "Mobile Registration Reply" :codes {"0" "No Code"}}
   "39" {:name "SKIP" :codes {"0" "No Code"}}
   "40" {:name "Photuris"
         :codes {"0" "Reserved"
                 "1" "unknown security parameters index"
                 "2" "valid security parameters, but authentication failed"
                 "3" "valid security parameters, but decryption failed"}}})

(def lc "col-xs-2  col-lg-1 col-md-1 col-sm-1")

(def wc "col-xs-10 col-lg-5 col-sm-5 col-md-5")

(defn select [id label owner state config & body]
  (let [merged-config (merge {:type "select" :id (name id) :label label
                              :value (id state) :class "input-sm"
                              :label-classname lc :wrapper-classname wc} config)
        final-config (if-let [change-fn (:on-change config)]
                       (assoc merged-config
                              :on-change #(do
                                            (o-state! owner id)
                                            (change-fn %)))
                       (assoc merged-config
                              :on-change #(o-state! owner id)))]
    (i/input final-config body)))

(defn direction-select [owner state]
  (select :direction (ml/t :vms-view/direction) owner state {}
          (d/option {:value "inbound"} (ml/t :vms-view/inbound))
          (d/option {:value "outbound"} (ml/t :vms-view/outbound))))

(defn protocol-select [owner state]
  (select :protocol (ml/t :vms-view/protocol) owner state
          {:on-change #(om/set-state! owner :icmp-type "0")}
          (d/option {:value "tcp"} "TCP")
          (d/option {:value "udp"} "UDP")
          (d/option {:value "icmp"} "ICMP")))

(defn target-select [owner state]
  (select :target
          (if (= (:direction state) "inbound")
            (ml/t :vms-view/source) (ml/t :vms-view/destination))
          owner state
          {:on-change #(om/set-state! owner :mask "24")}
          (d/option {:value "all"} (ml/t :vms-view/all))
          (d/option {:value "ip"} (ml/t :vms-view/ip))
          (d/option {:value "subnet"} (ml/t :vms-view/subnet))))

(defn target-data [owner state]
  (condp = (:target state)
    "ip" (i/input {:type "text"
                   :label (if (= (:direction state) "inbound")
                            (ml/t :vms-view/source-ip) (ml/t :vms-view/dest-ip))
                   :class "input-sm" :id "ip" :value (:ip state)
                   :label-classname lc :wrapper-classname wc
                   :on-change #(o-state! owner :ip)})
    "subnet" [(i/input {:type "text" :label (ml/t :vms-view/subnet) :class "input-sm"
                        :id "subnet" :value (:subnet state)
                        :label-classname lc :wrapper-classname wc
                        :on-change #(o-state! owner :subnet)})
              (select :mask (ml/t :vms-view/mask) owner state {}
                      (map #(d/option {:value %} %) (range 1 33)))]
    []))

(defn port-data [owner state]
  (if (or
       (= (:protocol state) "tcp")
       (= (:protocol state) "udp"))
    [(i/input {:type "checkbox" :label (ml/t :vms-view/all-ports)
               :id "all-ports"
               :checked (:all-ports state)
               :wrapper-classname (str "col-xs-offset-2 col-sm-offset-1 "
                                       "col-md-offset-1 col-lg-offset-1 "
                                       "col-xl-offset-1 " wc)
               :on-change #(om/set-state! owner :all-ports (.-checked (.-target %)))})
     (if (not (:all-ports state))
       (i/input {:type "text" :label (ml/t :vms-view/ports) :class "input-sm" :id "ports"
                 :value (:ports state)
                 :on-change #(o-state! owner :ports)
                 :label-classname lc :wrapper-classname wc}))]))

(defn icmp-type-select [data owner {parent :parent}]
  (reify
    om/IRenderState
    (render-state [_ _]
      (if (= (:protocol data) "icmp")
        (apply select :icmp-type "Type" parent data
               {:on-change #(om/set-state! parent :icmp-code "0")}
               (map
                (fn [[id obj]]
                  (d/option {:value id} (str (:name obj) " (" id ")")))
                (sort-by #(str->int (first %)) icmp)))))))

(defn icmp-codes [{id :id name :name} owner]
  (reify
    om/IRenderState
    (render-state [_ _]
      (d/option
       {:value id}
       (str name " (" id ")")))))

(defn icmp-code-select [data owner {parent :parent}]
  (reify
    om/IRenderState
    (render-state [_ _]
      (if  (= (:protocol data) "icmp")
        (if-let [codes (get-in icmp [(:icmp-type data) :codes])]
          (apply select :icmp-code "Code" parent data {}
                 (om/build-all
                  icmp-codes
                  (map (fn [[id name]] {:id id :name name})
                       (sort-by #(str->int (first %)) codes))
                  {:key :id})))))))

(defn action-select [owner state]
  (select :action (ml/t :vms-view/action) owner state {}
          (d/option {:value "allow"} (ml/t :vms-view/allow))
          (d/option {:value "block"} (ml/t :vms-view/block))))

(defn rule-target [state]
  (condp = (:target state)
    "all" "all"
    "ip" {:ip (:ip state)}
    "subnet" {:subnet (:subnet state) :mask (str->int (:mask state))}))

(defn rule-filter [state]
  (if (= (:protocol state) "icmp")
    [{:type (str->int (:icmp-type state))
      :code (str->int (:icmp-code state))}]
    (if (:all-ports state)
      "all"
      (map str->int (cstr/split (:ports state) #"[, ]+") ))))


;; TODO: make this properly check for va
(defn valid-rule [{action :action
                   direction :direction
                   target :target
                   protocol :protocol
                   filter :filter}]
  true)

(defn add-rule [state]
  (let [payload {:action (:action state)
                 :direction (:direction state)
                 :target (rule-target state)
                 :protocol (:protocol state)
                 :filters (rule-filter state)}]
    (if (valid-rule payload)
      (vms/add-fw-rule (:uuid state) payload))))


(defn render-target [target]
  (cond
    (= "all" target) "all"
    (:ip target)  (:ip target)
    :else (str (:subnet target) "/" (:mask target))))

(defn render-filter [filters]
  (cond
    (= "all" filters) "*"
    (:code (first  filters)) (str "ICMP("(:code (first  filters)) "/" (:type (first filters)) ")")
    :else  (cstr/join ", " filters)))

(defn render-rule [uuid
                   {id :id target :target protocol :protocol action :action
                    direction :direction filters :filters}]
  (let [target-str (str protocol "://" (render-target target))
        filters-str (render-filter filters)]
    (let [btn (b/button
               {:bs-style "warning"
                :bs-size "xsmall"
                :class "pull-right"
                :on-click #(vms/delete-fw-rule uuid id)}
               "x")
          action (if (= "allow" action)
                   (r/glyphicon {:glyph "ok"})
                   (r/glyphicon {:glyph "fire"}))]
      (if (= direction "inbound")
        (d/tr
         (d/td target-str)
         (d/td action)
         (d/td (r/glyphicon {:glyph "hdd"}) ":" filters-str)
         (d/td btn))
        (d/tr
         (d/td (r/glyphicon {:glyph "hdd"}))
         (d/td action)
         (d/td target-str ":" filters-str)
         (d/td btn))))))


(defn rule-table [render-rule title rules]
  (g/col
   {:xs 12 :md 6}
   (p/panel
    {:header title
     :class "fwrule"}
    (table
     {}
     (d/thead
      (d/tr
       (d/th (ml/t :vms-view/src))
       (d/th (ml/t :vms-view/action))
       (d/th (ml/t :vms-view/dst))
       (d/th)))
     (d/tbody
      (map render-rule rules))))))

(defn render-fw-rules [app owner opts]
  (reify
    om/IInitState
    (init-state [_]
      {:uuid (get-in app [root :selected])
       :action "block"
       :direction "inbound"
       :protocol "tcp"
       :all-ports false
       :target "all"})
    om/IRenderState
    (render-state [_ state]
      (r/well
       {}
       (row
        (g/col
         {}
         (direction-select owner state)
         (protocol-select owner state)
         (target-select owner state)
         (target-data owner state)
         (port-data owner state)
         (om/build icmp-type-select state
                   {:opts {:parent owner}
                    :react-key "icmp-type"})
         (om/build icmp-code-select state
                   {:opts {:parent owner}
                    :react-key "icmp-code"})
         (action-select owner state)))
       (row
        (g/col
         {:xs 12}
         (b/button
          {:bs-style "primary"
           :class "fwaddbtn"
           :on-click #(add-rule state)}
          (ml/t :vms-view/add-rule))))
       (row
        (g/col
         {:xs 10
          :class "fwlegend"}
         (d/p
          (d/br)
          (r/glyphicon {:glyph "fire"}) (ml/t :vms-view/block)
          (r/glyphicon {:glyph "ok"}) (ml/t :vms-view/allow)
          (r/glyphicon {:glyph "hdd"}) (ml/t :vms-view/this-zone))))
       (let [uuid (get-in app [root :selected])
             fw-rules (get-in app [root :elements uuid :fw_rules])
             rule-table (partial rule-table (partial render-rule uuid))]
         (row
          (rule-table (ml/t :vms-view/inbound-rules) (filter #(= (:direction %) "inbound") fw-rules))
          (rule-table (ml/t :vms-view/outbound-rules) (filter #(= (:direction %) "outbound") fw-rules))))))))

(defn build-metric [acc {name :name points :points}]
  (match
   [name]

   [["cpu" sub-metric]]
   (assoc-in acc ["CPU" sub-metric] points)

   [["memory" sub-metric]]
   (assoc-in acc ["Memory" sub-metric] points)

   [["swap" sub-metric]]
   (assoc-in acc ["Swap" sub-metric] points)

   [["net" direction "kb" nic]]
   (assoc-in acc [(str nic " throughput") direction] points)

   [["net" direction "ops" nic]]
   (assoc-in acc [(str nic " OPs") direction] points)

   [["zfs" direction "kb"]]
   (assoc-in acc ["ZFS throughput" direction] points)

   [["zfs" direction "ops"]]
   (assoc-in acc ["ZFS OPs" direction] points)

   [_] acc))


(defn b [f]
  #(om/build f %2))

(def sections
  {""          {:key  1 :fn #(om/build render-home %1)      :title (ml/t :vms-view/general)}
   "networks"  {:key  2 :fn #(om/build
                              render-networks %1
                              {:opts {:uuid (:uuid %2)}})  :title (ml/t :vms-view/networks)}
   "package"   {:key  3 :fn render-package   :title (ml/t :vms-view/package)}
   "snapshots" {:key  4 :fn (b render-snapshots) :title (ml/t :vms-view/snapshot)}
   "imaging"   {:key  5 :fn (b render-imaging) :title (ml/t :vms-view/imaging)}
   "backups"   {:key  6 :fn #(om/build render-backups %1 {:opts {:uuid (:uuid %2)}})   :title (ml/t :vms-view/backups)}
   "services"  {:key  7 :fn #(om/build services/render %2 {:opts {:action vms/service-action}})  :title (ml/t :vms-view/services)}
   "logs"      {:key  8 :fn (b render-logs)      :title (ml/t :vms-view/logs)}
   "fw-rules"  {:key  9 :fn #(om/build render-fw-rules %1) :title (ml/t :vms-view/firewall)}
   "metrics"   {:key 10 :fn #(om/build metrics/render (:metrics %2) {:opts {:translate build-metric}})   :title (ml/t :vms-view/metrics)}
   "metadata"  {:key 11 :fn (b metadata/render)  :title (ml/t :vms-view/metadata)}})



(defn tick [uuid local-timer]
  (let [app @app-state]
    (if (and
         (not= (get-in app [root :elements uuid :metrics]) :no-metrics)
         (= (get-in app [root :selected]) uuid)
         (= (:section app) :vms))
      (vms/metrics uuid)
      (metrics/stop-timer! local-timer))))

(def render
  (view/make
   root sections
   vms/get
   :mount-fn (fn [uuid {:type type :as  data}]
               (metrics/start-timer! (partial tick uuid))
               (orgs/list data)
               (hypervisors/list data)
               (networks/list data)
               (packages/list data)
               (ipranges/list data))
   :name-fn  (fn [{:keys [state uuid hypervisor] {alias :alias} :config} data]
               (d/div
                {}
                alias " "
                (b/button-group
                 {:class "fctabuttons"}
                 (b/button
                  {:bs-size "small"
                   :bs-style "primary"
                   :on-click #(open-with-ott (str "./" (if (= type "kvm") "vnc" "console")  ".html?uuid=" uuid))
                   :disabled? (not= state "running")}
                  (r/glyphicon {:glyph "modal-window"}))
                 (b/button
                  {:bs-size "small"
                   :bs-style "primary"
                   :on-click #(vms/start uuid)
                   :disabled? (= state "running")}
                  (r/glyphicon {:glyph "play"}))
                 (b/button
                  {:bs-size "small"
                   :bs-style "primary"
                   :on-click #(vms/stop uuid)
                   :disabled? (= state "stopped")}
                  (r/glyphicon {:glyph "stop"}))
                 (b/button
                  {:bs-size "small"
                   :bs-style "primary"
                   :on-click #(vms/reboot uuid)
                   :disabled? (= state "stopped")}
                  (r/glyphicon {:glyph "refresh"}))
                 (b/button
                  {:bs-size "small"
                   :bs-style "danger"
                   :on-click #(vms/delete state uuid)
                   :disabled? (= state "running")}
                  (r/glyphicon {:glyph "trash"})))))))
