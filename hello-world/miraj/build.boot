(def +project+ 'miraj.demos.hello-world/miraj)
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
 :asset-paths #{"resources/public"}
 :source-paths   #{"src/clj"}

 :checkouts '[[miraj/co-dom "1.0.0-SNAPSHOT"]
              [miraj/core "1.0.0-SNAPSHOT"]]
 ;;              [miraj/html "5.1.0-SNAPSHOT"]]

 :dependencies   '[[org.clojure/clojure "1.9.0-alpha17"]
                   [org.clojure/clojurescript "RELEASE"]
                   [miraj/co-dom "1.0.0-SNAPSHOT"]
                   [miraj/core "1.0.0-SNAPSHOT"]
                   [miraj/html "5.1.0-SNAPSHOT"]

                   [adzerk/boot-cljs "2.0.0" :scope "test"]
                   [com.cemerick/piggieback     "0.2.1"          :scope "test"]
                   [weasel                      "0.7.0"          :scope "test"]
                   [org.clojure/tools.nrepl "0.2.12" :scope "test"]
                   [adzerk/boot-cljs-repl       "0.3.0"          :scope "test"]
                   [adzerk/boot-reload          "0.5.1"          :scope "test"]

                   ;; we need this for polyfills:
                   [miraj.polymer/assets "1.8.1-SNAPSHOT"]

                   [pandeiro/boot-http "0.7.3" :scope "test"]
                   [miraj/boot-miraj "0.1.0-SNAPSHOT" :scope "test"]
                   ])

(require '[boot.task.built-in]
         '[miraj.boot-miraj :as miraj]
         '[adzerk.boot-reload    :refer [reload]]
         '[adzerk.boot-cljs :refer [cljs]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
         '[pandeiro.boot-http :refer :all])

(task-options!
 pom  {:project     +project+
       :version     +version+
       :description "miraj/defweb-page example code"
       :license     {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}}
 repl {:port 8080})

(deftask monitor
  "repl development."
  []
  (comp (serve :dir "target")
        (cider)
        ;; (repl :server true)
        (cljs-repl)  ;; run (start-repl) to get the cljs repl
        (watch)
        (notify :audible true)))

(deftask bitter
  "repl development."
  []
  (let [pg 'miraj.demos.hello-world.miraj.bitter]
    (comp (miraj/compile :pages #{pg}
                         :polyfill :lite
                         :keep true
                         :debug true)
          (miraj/link    :pages #{pg}
                         ;; :assets :polymer
                         :debug true))))

(deftask sweet
  "compile page sweet"
  []
  (let [pg 'miraj.demos.hello-world.miraj.sweet]
    (comp (miraj/compile :pages #{pg}
                         :polyfill :lite
                         :keep true
                         :debug true)
          (miraj/link    :pages #{pg}
                         ;; :assets :polymer
                         :debug true))))

(deftask sweeter
  ""
  []
  (let [pg 'miraj.demos.hello-world.miraj.sweeter]
    (comp (miraj/compile :pages #{pg}
                         :polyfill :lite
                         :keep true
                         :debug true)
          (miraj/link    :pages #{pg}
                         ;; :assets :polymer
                         :debug true))))

(deftask sweetest
  ""
  []
  (let [pg 'miraj.demos.hello-world.miraj.sweetest]
    (comp
          (miraj/compile :pages #{pg}
                         :polyfill :lite
                         :keep true
                         :debug true)
          (miraj/link    :pages #{pg}
                         :debug true)
          (reload) ;; live reload browser assets
          (cljs :source-map true
                ;; :optimizations :none
                )))) ;; always run this AFTER cljs-repl

(deftask pages
  ""
  []
  (comp (bitter)
        (sweet)
        (sweeter)
        (sweetest)))



