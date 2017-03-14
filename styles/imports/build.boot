(def +project+ 'tmp.miraj.demos.pages/hello-world)
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
 :resource-paths #{"resources/public"}
 :source-paths   #{"src/clj"}
;; :resource-paths #{"src/clj/main"}

 ;; :repositories {"clojars" "https://clojars.org/repo"
 ;;                "maven-central" "http://mvnrepository.com"
 ;;                "central" "http://repo1.maven.org/maven2/"}

 :checkouts '[[miraj/core "0.1.0-SNAPSHOT"]
             [miraj/co-dom "0.1.0-SNAPSHOT"]]
             ;; [tmp.components/basic "0.1.0-SNAPSHOT"]
             ;; [tmp.components/greeter "0.1.0-SNAPSHOT"]
             ;; [tmp.components/salutations "0.1.0-SNAPSHOT"]]

 :dependencies   '[[org.clojure/clojure "RELEASE" :scope "runtime"]
                   [org.clojure/math.numeric-tower "0.0.4"]

                   [org.clojure/clojurescript "1.9.473"]
                   [hipo "0.5.2"]
                   [adzerk/boot-cljs "2.0.0-OUTPUTFIX" :scope "test"]
                   [adzerk/boot-cljs-repl   "0.3.3"] ;; latest release
                   [com.cemerick/piggieback "0.2.1"  :scope "test"]
                   [weasel                  "0.7.0"  :scope "test"]
                   [org.clojure/tools.nrepl "0.2.12" :scope "test"]

                   [miraj/core "0.1.0-SNAPSHOT"]
                   [miraj/co-dom "0.1.0-SNAPSHOT"]
                   [miraj/html "5.1.0-SNAPSHOT"]
                   [miraj.polymer/paper "1.2.3-SNAPSHOT"]
                   [miraj.polymer/iron "1.2.3-SNAPSHOT"]

                   ;; local components
                   ;; [tmp.components/basic "0.1.0-SNAPSHOT"]
                   ;; [tmp.components/greeter "0.1.0-SNAPSHOT"]
                   ;; [tmp.components/salutations "0.1.0-SNAPSHOT"]
                   ;; [adzerk/boot-test "1.0.7" :scope "test"]

                   ;; [javax.servlet/servlet-api "2.5"]

                   [garden "1.3.2"]

                   [pandeiro/boot-http "0.7.3" :scope "test"]
                   [compojure/compojure "1.4.0"]
                   [ring/ring-core "1.4.0"]
                   [ring/ring-devel "1.4.0"]
                   [ring/ring-servlet "1.4.0"]
                   [ring/ring-defaults "0.1.5"]
                   [ns-tracker/ns-tracker "0.3.0" :scope "test"]


                   [boot/core "RELEASE" :scope "test"]
                   [adzerk/boot-reload "0.5.1" :scope "test"] ;; cljs
                   [samestep/boot-refresh "0.1.0" :scope "test"] ;; clj reloading
                   [mobileink/boot-bowdlerize "0.1.0-SNAPSHOT" :scope "test"]
                   [miraj/boot-miraj "0.1.0-SNAPSHOT" :scope "test"]
                   ])

;; for local testing with boot-http:
;;  $ boot serve wait
;; or:  $ boot serve -d target wait   # serve target dir
;; or:  $ boot serve -H myapp.server/app -R wait   # myapp.server.app is your ring handler

(require '[boot-bowdlerize :as b]
         '[adzerk.boot-cljs      :refer [cljs]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
         '[miraj.boot-miraj :as miraj]
         '[boot.task.built-in]
         '[pandeiro.boot-http :refer :all]
         '[samestep.boot-refresh :refer [refresh]]
         '[adzerk.boot-reload :refer [reload]])

(def configs #{'resources/styles 'bower/config-map 'polymer/config-map})

;;(def build #{"build"})

(task-options!
 repl {:eval (set-env! #(conj % "test/clj"))}
 serve {:dir "target"}
 b/config {:nss configs}
 b/install {:nss configs}
 pom  {:project     +project+
       :version     +version+
       :description "miraj/defweb-page example code"
       :license     {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}}
 repl {:port 8899})

(deftask build
  "compile, pom, jar, install"
  []
  (comp
   (miraj/compile :debug true)
   (miraj/link    :debug true)
   (cljs)
   (target)))
   ;; (pom)
   ;; (jar)
   ;; (install)))

(deftask monitor
  "watch notivy compile, pom, jar, install"
  []
  (comp
   (watch) ;; :verbose false)
   (notify :audible true)
   ;; (refresh)
   (miraj/compile :all true :pprint true :verbose true)
   #_(miraj/compile :webpage-var #{'work.pages.styled.externals/hello})
   #_(miraj/compile :webpage #{'work.pages.styled.imported/index}
                  :pprint true
                  :verbose true)
   #_(miraj/compile :webpage-var #{'work.pages.styled.style-modules/hello}
                  :pprint true
                  :verbose true)
   (miraj/link :verbose true)
   (cljs)
   (target :no-clean true)
   ;; (pom)
   ;; (jar)
   #_(install)))

(deftask run
  "compile, serve"
  []
  (comp
   (serve) ;; :dir "target")
   (watch) ;; :verbose true)
   (notify :audible true)
   (cljs-repl)
   (refresh)
   (miraj.boot-miraj/compile :keep true :debug true :pages true)
   (miraj.boot-miraj/link    :debug true :pages true)
   (reload) ;; :port 9001)
   (target)
   (cljs)
   (target :no-clean true)))


