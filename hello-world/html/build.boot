(def +project+ 'miraj.demos.hello-world/html)
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
 :asset-paths #{"resources/public"}
 :source-paths   #{"src/clj"}

 :checkouts '[[miraj/co-dom "1.0.0-SNAPSHOT"]
              [miraj/core "0.1.0-SNAPSHOT"]]

 :dependencies   '[[org.clojure/clojure "RELEASE"]
                   ;; [org.clojure/spec.alpha "0.1.108"]
                   [miraj/co-dom "1.0.0-SNAPSHOT"]
                   [miraj/html "5.1.0-SNAPSHOT"]
                   [miraj/core "0.1.0-SNAPSHOT"]

                   [miraj.polymer/paper "1.2.3-SNAPSHOT"]

                   [pandeiro/boot-http "0.7.3" :scope "test"]

                   [miraj/boot-miraj "0.1.0-SNAPSHOT" :scope "test"]
                   [samestep/boot-refresh "0.1.0"]
                   ])

(require '[boot.task.built-in]
         '[miraj.boot-miraj :as miraj]
         '[samestep.boot-refresh :refer [refresh]]
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
  (comp (serve :dir "target" :port 3000)
        (cider)
        (repl :server true :port 8080)
        (watch)
        (notify :audible true)))

(deftask bitter
  "build"
  []
  (require '[miraj.demos.hello-world.html.bitter]
             '[miraj.demos.hello-world.html.bitterer]
             '[miraj.demos.hello-world.html.bitterest]
             ;; NB: spec.alpha breaks :relaod-all at the moment
             :reload ;; macros involved, reload is required for interactive dev
             ))

(deftask sweet
  "build sweet html demo"
  []
  (let [pg 'miraj.demos.hello-world.html.sweet/index]
    (comp
     (miraj/compile :pages #{pg}
                    :polyfill :lite
                    :debug true)
     (miraj/link    :pages #{pg}
                    :debug true))))

(deftask sweeter
  "build sweeter html demo"
  []
  (let [pg 'miraj.demos.hello-world.html.sweeter]
    (comp
     (miraj/compile :pages #{pg}
                    :polyfill :lite
                    :debug true)
     (miraj/link    :pages #{pg}
                    :debug true))))

(deftask sweetest
  "build sweetest html demo"
  []
  (let [pg 'miraj.demos.hello-world.html.sweetest]
    (comp
     (miraj/compile :pages #{pg}
                    :polyfill :lite
                    :debug true)
     (miraj/link    :pages #{pg}
                    :debug true))))

(deftask pages
  "compile and link all pages"
  []
  (comp #_(bitter)
        (sweet)
        (sweeter)
        (sweetest)))

