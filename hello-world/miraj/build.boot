(def +project+ 'miraj.demos.hello-world/miraj)
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
 :asset-paths #{"resources/public"}
 :source-paths   #{"src/clj"}

 :checkouts '[[miraj/co-dom "0.1.0-SNAPSHOT"]]

 :dependencies   '[[org.clojure/clojure "RELEASE"]
                   [miraj/co-dom "0.1.0-SNAPSHOT"]
                   [miraj/html "5.1.0-SNAPSHOT"]
                   [miraj/core "0.1.0-SNAPSHOT"]
                   [pandeiro/boot-http "0.7.3" :scope "test"]
                   ])

(require '[boot.task.built-in]
         '[pandeiro.boot-http :refer :all])

(task-options!
 pom  {:project     +project+
       :version     +version+
       :description "miraj/defweb-page example code"
       :license     {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}}
 repl {:port 8080})

(deftask dev
  "repl development."
  []
  (comp (cider)
        (repl :server true)
        (serve :dir "target")
        (watch)
        (notify :audible true)
        (target)))

