(def +project+ 'miraj.demos.hello-world/polymer)
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
 :resource-paths #{"resources/public"}
 :source-paths   #{"src/clj"}

 ;; :checkouts '[[miraj/co-dom "1.0.0-SNAPSHOT"]
 ;;              [miraj/core "1.0.0-SNAPSHOT"]]

 :dependencies   '[[org.clojure/clojure "RELEASE"]
                   [org.clojure/clojurescript "RELEASE"]
                   [miraj/co-dom "1.0.0-SNAPSHOT"]
                   [miraj/core "1.0.0-SNAPSHOT"]
                   [miraj/html "5.1.0-SNAPSHOT"]
                   [miraj.polymer/paper "1.2.3-SNAPSHOT"]
                   [miraj.polymer/iron "1.2.3-SNAPSHOT"]

                   [adzerk/boot-cljs "2.0.0"            :scope "test"]
                   [adzerk/boot-cljs-repl       "0.3.0" :scope "test"]
                   [com.cemerick/piggieback     "0.2.1" :scope "test"]
                   [weasel                      "0.7.0" :scope "test"]
                   [org.clojure/tools.nrepl "0.2.12"    :scope "test"]
                   [adzerk/boot-reload          "0.5.1" :scope "test"]
                   [pandeiro/boot-http "0.7.3"          :scope "test"]
                   [miraj/boot-miraj "0.1.0-SNAPSHOT"   :scope "test"]
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
       :license     {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask monitor
  "repl development."
  []
  (comp (serve :dir "target" :port 3000)
        (cider)
        ;; (repl :server true :port 8080)
        (cljs-repl)  ;; run (start-repl) to get the cljs repl
        (watch)
        (notify :audible true)))

(deftask bitter
  "build bitter polymer demo"
  []
  (require '[miraj.demos.hello-world.polymer.bitter] :reload))

(deftask sweet
  "build sweet polymer demo"
  []
  (let [pg 'miraj.demos.hello-world.polymer.sweet/index]
    (comp
     (miraj/compile :pages #{pg}
                    :polyfill :lite
                    :debug true)
     (miraj/link    :pages #{pg}
                    :debug true))))

(deftask sweeter
  "build sweeter polymer demo"
  []
  (let [pg 'miraj.demos.hello-world.polymer.sweeter]
    (comp
     (miraj/compile :pages #{pg}
                    :polyfill :lite
                    :debug true)
     (miraj/link    :pages #{pg}
                    :debug true))))

(deftask sweetest
  "build sweetest polymer demo"
  []
  (let [pg 'miraj.demos.hello-world.polymer.sweetest]
    (comp
     (miraj/compile :pages #{pg}
                    :polyfill :lite
                    :debug true)
     (miraj/link    :pages #{pg}
                    :debug true)
     (reload) ;; live reload browser assets
     (cljs :source-map true :optimizations :none)))) ;; always run this AFTER cljs-repl

(deftask pages
  "compile and link all pages"
  []
  (comp ;;(bitter)
        (sweet)
        (sweeter)
        (sweetest)))
