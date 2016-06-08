(ns cerberus.core
  (:require-macros
   [cljs.core.async.macros :refer [go]]
   [cljs.core.match.macros :refer [match]])
  (:require
   [om.core :as om :include-macros true]
   [cljs-http.client :as httpc]
   [cljs.core.match]
   [om-tools.dom :as d :include-macros true]
   [om-bootstrap.random :as r]
   [om-bootstrap.button :as b]
   [om-bootstrap.grid :as g]
   [om-bootstrap.input :as i]
   [om-bootstrap.nav :as n]
   [om-bootstrap.progress-bar :as pb]
   [cerberus.routing]
   [cerberus.http :as http]
   [cerberus.alert :as alert]

   [cerberus.hypervisors :as hypervisors]
   [cerberus.datasets :as datasets]
   [cerberus.vms :as vms]
   [cerberus.packages :as packages]
   [cerberus.networks :as networks]
   [cerberus.ipranges :as ipranges]
   [cerberus.dtrace :as dtrace]
   [cerberus.users :as users]
   [cerberus.roles :as roles]
   [cerberus.orgs :as orgs]
   [cerberus.clients :as clients]
   [cerberus.groupings :as groupings]
   [cerberus.config :as conf]
   [cerberus.add :as add]
   [cerberus.howl :as howl]

   [cerberus.timers]
   [cerberus.utils :refer [goto val-by-id by-id a menu-items]]
   [cerberus.list.utils :refer [large small]]
   [cerberus.state :refer [app-state set-state!]]
   [cerberus.multi-lang.entry :as ml]))

(enable-console-print!)

(def login-path "/api/2/oauth/token")

(defn login-fn []
  (go
    (let [req-body {:grant_type "password"
                    :username (val-by-id "login")
                    :password (val-by-id "password")}
          req-body (if-let [otp (val-by-id "yubikey")]
                     (if (not (empty? otp)) (assoc req-body :fifo_otp otp) req-body) req-body)
          response (<! (httpc/post login-path {:form-params req-body
                                               :accept "application/json"}))]
      (if (= 200 (:status response))
        (let [e (:body response)
              token (e :access_token)
              expires-in (e :expires_in)]
          (set-state! :valid-login true)
          (conf/login token expires-in)
          (howl/init))
        (set-state! :valid-login false)))))

(defn login [app]
  (r/well
   {:id "login-box"}
   (d/img {:className "loginlogo" :src "imgs/fifo-logo.png" :alt "FiFo"})
   (d/form
    nil
    (i/input {:type "text" :placeholder (ml/t :core/login) :id "login" :bs-style (if (:valid-login app) "" "error")})
    (i/input {:type "password" :placeholder (ml/t :core/password) :id "password"
              :on-key-up #(if (= (.-keyCode  %) 13) (login-fn))
              :bs-style (if (:valid-login app) "" "error")})
    (i/input {:type "text" :placeholder (ml/t :core/yubikey) :id "yubikey"
              :on-key-up #(if (= (.-keyCode  %) 13) (login-fn))
              :bs-style (if (:valid-login app) "" "error")})
    (b/button {:bs-style "primary"
               :on-click login-fn} (ml/t :core/login)))))

(defn nav-style [app section view]
  (if (and (= section (:section app)) (= view (:view app)))
    {:className "active"}
    {}))


(defn max-level [alerts]
  (fn [last next]
    (match
     [last next]
     [:danger _]  :danger
     [_ :danger]  :danger
     [:warning _] :warning
     [_ :warning] :warning
     [:info _]    :info
     [_ :info]    :info
     [:success _] :success
     [_ :success] :success
     [:primary _] :primary
     [_ :primary] :primary
     [_ _]        :default))
  :default
  (map :type (vals alerts)))

(defn nav-bar [data owner opts]
  (reify
    om/IDisplayName
    (display-name [_]
      "navbarc")
    om/IRenderState
    (render-state [_ _]
      (n/navbar
       {:brand (d/a {:href (str "#/")} (d/img {:src "imgs/fifo-logo.png" :alt "FiFo"}))}
       (n/nav
        {:collapsible? true}
        (n/nav-item {:key 1 :href "#/vms"} (ml/t :core/machines))
        (n/nav-item {:key 2 :href "#/datasets"} (ml/t :core/datasets))
        (n/nav-item {:key 3 :href "#/hypervisors"} (ml/t :core/hypervisors))
        (b/dropdown
         {:key 4 :title (ml/t :core/configuration)}
         (menu-items
          [(ml/t :core/users) "#/users"]
          [(ml/t :core/roles) "#/roles"]
          [(ml/t :core/orgs)  "#/orgs"]
          :divider
          [(ml/t :core/clients) "#/clients"]
          :divider
          [(ml/t :core/packages) "#/packages"]
          :divider
          [(ml/t :core/networks) "#/networks"]
          [(ml/t :core/ip-ranges) "#/ipranges"]
          #_["DTrace" "#/dtrace"]
          [(ml/t :core/groupings) "#/groupings"]
          :divider
          [(ml/t :core/logout) #(conf/logout)]
          ;;["Logout & Reset UI" #(conf/clear)]
          ))
        (let [alerts (:alerts data)]
          (b/dropdown
           {:key 5
            :bs-style (max-level alerts)
            :title (d/span (ml/t :core/notifications)
                           (r/badge {} (count alerts)))}
           (apply
            menu-items
            (map
             (fn [[id alert]]
               [(r/label {:bs-style (name (:type alert))} (:text alert)) #(alert/clear id)])
             alerts))))

        ;;Removed this for now
        #_(n/nav-item {:key 5 :style {:height 20 :width 200} :class "navbar-right hidden-xs hidden-sm"}
                      (pb/progress-bar {:min 0
                                        :max (get-in data [:total-memory] 0)
                                        :now (get-in data [:provisioned-memory] 0) :label "RAM"}))
        ;; Removed this for now
        #_(n/nav-item {:key 6 :style {:height 20 :width 200} :class "navbar-right hidden-xs hidden-sm"}
                      (pb/progress-bar {:min 0
                                        :max (get-in data [:disk-size] 0)
                                        :now (get-in data [:disk-used] 0) :label "Disk"})))
       ))))

(defn main-view [data]
  (condp = (:section data)
    :vms         (om/build vms/render data)
    :datasets    (om/build datasets/render data)
    :hypervisors (om/build hypervisors/render data)
    :networks    (om/build networks/render data)
    :packages    (om/build packages/render data)
    :ipranges    (om/build ipranges/render data)
    :dtrace      (om/build dtrace/render data)
    :users       (om/build users/render data)
    :roles       (om/build roles/render data)
    :orgs        (om/build orgs/render data)
    :clients     (om/build clients/render data)
    :groupings   (om/build groupings/render data)
    (goto "/vms")))


(defn render-alerts [[id alert] owner opts]
  (reify
    om/IRenderState
    (render-state [_ _]
      (if
          (:overlay alert)
          (r/alert
           {:bs-style (name (:type alert))}
           (:text alert) (d/span {:on-click #(alert/clear id) :class "pull-right"} "x"))))))

(defn main []
  (om/root
   (fn [app owner]
     (reify
       om/IDisplayName
       (display-name [_]
         "Cerberus")
       om/IRenderState
       (render-state [_ state]
         (if (:token app)
           (d/div
            {:class (str "app " (if (get-in app [:add :maximized])  "add-open" "add-closed"))}
            (om/build nav-bar app)
            (d/div
             {:class large
              :style {:position "absolute" :z-index 10
                      :right 20 :width 300}}
             (om/build-all render-alerts (:alerts app)))
            (g/grid
             {}
             (g/row
              {:class small}
              (g/col
               {:xs 12}
               (om/build-all render-alerts (:alerts app))))
             (g/row
              {}
              (g/col
               {:xs 12}
               (main-view app))))
            (om/build add/render (get-in app [:add])))
           (do (goto)
               (login app))))))
   app-state
   {:target (by-id "app")}))
