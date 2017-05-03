(def +project+ 'miraj.demos.hello-world/html)
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
 :asset-paths #{"resources/public"}
 :source-paths   #{"src/clj"}

 :checkouts '[[miraj/co-dom "1.0.0-SNAPSHOT"]
              [miraj/core "0.1.0-SNAPSHOT"]]

 :dependencies   '[[org.clojure/clojure "RELEASE"]
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

(deftask build
  "build"
  []
  ;; compilation is only for defpage, which these three do not use:
  (require '[miraj.demos.hello-world.html.bitterest]
           '[miraj.demos.hello-world.html.bitter]
           '[miraj.demos.hello-world.html.sweet])
  (comp
   (miraj/compile :page 'miraj.demos.hello-world.html.sweeter
                  :polyfill :lite
                  :debug true)
   (miraj/compile :page 'miraj.demos.hello-world.html.sweetest
                  :polyfill :lite
                  :imports ["sweetest/imports.html"]
                  :debug true)
   (target :no-clean true)))

(deftask dev
  "repl development."
  []
  (comp (cider)
        (repl :server true :port 8080)
        (serve :dir "target" :port 3000)
        (watch)
        (notify :audible true)
        (build)))

