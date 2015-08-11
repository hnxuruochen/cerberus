(defproject cerberus "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/clj"]
  :repl-options {:timeout 200000} ;; Defaults to 30000 (30 seconds)

  :test-paths ["spec/clj"]

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [compojure "1.4.0"]
                 [enlive "1.1.6"]
                 [tailrecursion/ring-proxy "2.0.0-SNAPSHOT"]
                 [environ "1.0.0"]
                 [cconf "1.1.0"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.clojure/core.match "0.2.2"]
                 ;; ClojureScript related dependencies
                 [org.clojure/clojurescript "1.7.58"]
                 [org.omcljs/om "0.9.0"]
                 [com.lucasbradstreet/instaparse-cljs "1.4.1.0"]
                 ;;[cljsjs/react-with-addons "0.12.2-4"]
                 [racehub/om-bootstrap "0.5.3"]
                 [secretary "1.2.3"]
                 [cljs-http "0.1.36"]]

  :plugins [[lein-cljsbuild "1.0.5"]
            [lein-environ "1.0.0"]
            [lein-less "1.7.3"]]

  :min-lein-version "2.5.1"

  :uberjar-name "cerberus.jar"

  :cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                             :compiler {:output-to     "resources/public/js/app.js"
                                        :output-dir    "resources/public/js/out"
                                        :source-map    "resources/public/js/out.js.map"
                                        :preamble      ["react/react.min.js"]
                                        :optimizations :none
                                        :pretty-print  true}}}}

  :less {:source-paths ["src/less"]
         :target-path "resources/public/css"}

  :profiles {:dev {:source-paths ["env/dev/clj"]
                   :test-paths ["test/clj"]

                   :dependencies [[figwheel "0.2.5"]
                                  [figwheel-sidecar "0.2.5"]
                                  [com.cemerick/piggieback "0.1.5"]
                                  [weasel "0.6.0"]
                                  [leiningen "2.5.1"]]

                   :repl-options {:init-ns cerberus.server
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

                   :plugins [[lein-figwheel "0.2.1-SNAPSHOT"]]

                   :figwheel {:http-server-root "public"
                              :server-port 3449
                              :css-dirs ["resources/public/css"]}

                   :env {:is-dev true}

                   :cljsbuild {:test-commands { "test" ["phantomjs" "env/test/js/unit-test.js" "env/test/unit-test.html"] }
                               :builds {:app {:source-paths ["env/dev/cljs"]}
                                        :test {:source-paths ["src/cljs" "test/cljs"]
                                               :compiler {:output-to     "resources/public/js/app_test.js"
                                                          :output-dir    "resources/public/js/test"
                                                          :source-map    "resources/public/js/test.js.map"
                                                          :preamble      ["react/react.min.js"]
                                                          :optimizations :whitespace
                                                          :pretty-print  false}}}}}
             :rel {:source-paths ["env/prod/clj"]
                   :hooks [leiningen.cljsbuild leiningen.less]
                   :env {:production true}
                   :omit-source true
                   :aot :all
                   :cljsbuild {:builds {:app
                                        {:source-paths ["env/prod/cljs"]
                                         :compiler
                                         {:optimizations :advanced
                                          :pretty-print false}}}}}})
