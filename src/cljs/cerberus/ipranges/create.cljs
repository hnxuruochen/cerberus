(ns cerberus.ipranges.create
  (:require
   [om.core :as om :include-macros true]
                                        ;[cerberus.ipranges.api :refer [root]]
   [cerberus.utils :refer [ip->int]]
   [cerberus.create :as create]
   [cerberus.multi-lang.entry :as ml]))

(defn network [data]
  (or (get-in data [:network]) "0.0.0.0"))

(defn netmask [data]
  (or (get-in data [:netmask]) "255.255.255.255"))

(defn gateway [data]
  (or (get-in data [:gateway]) "0.0.0.0"))

(defn first-ip [data]
  (or (get-in data [:first]) "0.0.0.0"))

(defn last-ip [data]
  (or (get-in data [:last]) "0.0.0.0"))

(def ip-re #"^([01]?\d\d?|2[0-4]\d|25[0-5])\.([01]?\d\d?|2[0-4]\d|25[0-5])\.([01]?\d\d?|2[0-4]\d|25[0-5])\.([01]?\d\d?|2[0-4]\d|25[0-5])$")


(defn valid-network [data]
  (let [network (ip->int (network data))
        netmask (ip->int (netmask data))
        net-last (+ network (bit-not netmask))
        gateway (ip->int (gateway data))
        first (ip->int (first-ip data))
        last (ip->int (last-ip data))]
    (and
     (<= network first last net-last)
     (< network gateway net-last)
     (= network (bit-and network netmask)))))

(defn valid-ip [data ip]
  (and (re-matches ip-re (str ip))
       (valid-network (:data  data))))

(defn render [data]
  (reify
    om/IDisplayName
    (display-name [_]
      "addnetworkc")
    om/IRenderState
    (render-state [_ _]
      (if (nil? (get-in data [:data :vlan]))
        (om/update! data [:data :vlan] 0))
      (if (nil? (get-in data [:view :vlan]))
        (om/update! data [:view :vlan] "0"))
      (create/render
       data
       {:type :input :label (ml/t :ipranges-create/name) :id "ipr-name" :key :name}
       {:type :input :label (ml/t :ipranges-create/nic-tag) :id "ipr-tag" :key :tag}
       {:type :input :label (ml/t :ipranges-create/vlan) :id "ipr-vlan" :key :vlan :data-type :integer
        :validator #(and
                     (not= %3 "")
                     (not (nil? %2))
                     (not (js/isNaN  %2))
                     (<= 0 %2 4096))}
       {:type :input :label (ml/t :ipranges-create/subset-ip) :id "ipr-network" :key :network :validator valid-ip}
       {:type :input :label (ml/t :ipranges-create/netmask) :id "ipr-netmask" :key :netmask :validator valid-ip}
       {:type :input :label (ml/t :ipranges-create/gateway) :id "ipr-gateway" :key :gateway :validator valid-ip}
       {:type :input :label (ml/t :ipranges-create/first) :id "ipr-first" :key :first :validator valid-ip}
       {:type :input :label (ml/t :ipranges-create/last) :id "ipr-last" :key :last :validator valid-ip}))))
