(ns jingles.hypervisors.api
  (:refer-clojure :exclude [get list])
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [jingles.api :as api]
   [jingles.http :as http]
   [jingles.state :refer [set-state!]]))

(def root :hypervisors)

(def list-fields
  "uuid,alias")

(def list (partial api/list root list-fields))

(def get (partial api/get root))
