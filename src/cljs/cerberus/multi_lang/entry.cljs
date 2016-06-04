(ns cerberus.multi-lang.entry
  (:require
   [cerberus.multi-lang.en :as en]
   [cerberus.multi-lang.zh-CN :as zh-CN]

   [net.unit8.tower :as tower]))

(defn- get-current-locale []
  (or (first (.-languages js/window.navigator)) (.-language js/window.navigator)))

(def my-tconfig
  {:dev-mode? true
   :fallback-locale :en
   :dictionary
   {:en en/tconfig-map
    :zh-CN zh-CN/tconfig-map}})

(defn t [keyword]
  (tower/t (get-current-locale) my-tconfig keyword))

(defn t1 [keyword & args]
  (tower/t (get-current-locale) my-tconfig keyword args))
