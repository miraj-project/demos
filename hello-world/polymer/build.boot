(def +project+ 'miraj.demos.hello-world/polymer)
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
 :resource-paths #{"resources/public"}
 :source-paths   #{"src/clj"}

 :checkouts '[[miraj/co-dom "1.0.0-SNAPSHOT"]
              [miraj/core "0.1.0-SNAPSHOT"]]

 :dependencies   '[[org.clojure/clojure "RELEASE"]
                   [miraj/co-dom "1.0.0-SNAPSHOT"]
                   [miraj/html "5.1.0-SNAPSHOT"]
                   [miraj/core "0.1.0-SNAPSHOT"]
                   [miraj.polymer/paper "1.2.3-SNAPSHOT"]
                   [miraj.polymer/iron "1.2.3-SNAPSHOT"]
                   [pandeiro/boot-http "0.7.3" :scope "test"]
                   [miraj/boot-miraj "0.1.0-SNAPSHOT" :scope "test"]
                   ])

(require '[boot.task.built-in]
         '[miraj.boot-miraj :as miraj]
         '[pandeiro.boot-http :refer :all])

(task-options!
 pom  {:project     +project+
       :version     +version+
       :description "miraj/defweb-page example code"
       :license     {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask build
  "build"
  []
  (let [pg 'miraj.demos.hello-world.polymer.sweeter]
    (comp
     (miraj/compile :pages #{pg}
                    :polyfill :lite
                    :debug true)
     (miraj/link    :pages #{pg}
                    :debug true)
     #_(miraj/compile :pages #{} ;; all pages
                    :polyfill :lite
                    :debug true)
     #_(miraj/link    :pages #{} ;; all pages
                    ;; :assets :polymer
                    :debug true))))

(deftask dev
  "repl development."
  []
  (comp (cider)
        (repl :server true :port 8080)
        (serve :dir "target" :port 3000)
        (watch)
        (notify :audible true)
        (build)
        (target)))

