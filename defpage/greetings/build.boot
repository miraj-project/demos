(def +project+ 'tmp.miraj.defpage/greetings)
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
 ;; :asset-paths #{"resources/public"}
 ;; :resource-paths #{"bower_components"}
 :source-paths   #{"src/clj/webpages"}
 :resource-paths #{"src/clj/main"}
 :repositories {"clojars" "https://clojars.org/repo"
                "maven-central" "http://mvnrepository.com"
                "central" "http://repo1.maven.org/maven2/"}
 :checkouts      '[[tmp.components/greetings "0.1.0-SNAPSHOT"]]
 :dependencies   '[[org.clojure/clojure "RELEASE" :scope "runtime"]

                   [miraj/miraj "0.1.0-SNAPSHOT"]
                   [miraj/html "5.1.0-SNAPSHOT"]
                   [miraj/markup "0.1.0-SNAPSHOT"]
                   [polymer/paper "1.2.3-SNAPSHOT"]
                   [polymer/iron "1.2.3-SNAPSHOT"]

                   ;; local component
                   [tmp.components/greetings "0.1.0-SNAPSHOT"]
                   ;; [adzerk/boot-test "1.0.7" :scope "test"]

                   ;; [javax.servlet/servlet-api "2.5"]

                   [pandeiro/boot-http "0.7.3"]
                   [adzerk/boot-reload "0.4.12"]
                   [org.clojure/math.numeric-tower "0.0.4"]
                   [compojure/compojure "1.4.0"]
                   [ring/ring-core "1.4.0"]
                   [ring/ring-devel "1.4.0"]
                   [ring/ring-servlet "1.4.0"]
                   [ring/ring-defaults "0.1.5"]
                   [ns-tracker/ns-tracker "0.3.0"]

                   [boot/core "RELEASE" :scope "provided"]
                   [mobileink/boot-bowdlerize "0.1.0-SNAPSHOT" :scope "test"]
                   [miraj/boot-miraj "0.1.0-SNAPSHOT" :scope "test"]
                   ])

;; to run the webserver:
;;  $ boot serve wait
;; or:  $ boot serve -d target wait   # serve target dir
;; or:  $ boot serve -H myapp.server/app -R wait   # myapp.server.app is your ring handler

(require '[boot-bowdlerize :as b]
         '[boot-miraj :as mrj]
         '[boot.task.built-in]
         '[pandeiro.boot-http :refer :all]
         '[adzerk.boot-reload :refer [reload]])

(def configs #{'resources/styles 'bower/config-map 'polymer/config-map})

;;(def build #{"build"})

(task-options!
 b/config {:nss configs}
 b/install {:nss configs}
 pom  {:project     +project+
       :version     +version+
       :description "miraj/defweb-page example code"
       :license     {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}}
 repl {:port 8080})

