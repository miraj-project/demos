(println "loading build.boot")

(def +project+ 'tmp.components/basic)
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
 :source-paths #{"src/clj"}
 ;;:resource-paths #{"src/cljs"}
  ;; bower must be on classpath for http to pick it up
  ;; :resource-paths #{"resources/public"}
  :asset-paths #{"resources/public"}

  ;; :resource-paths #{"resources"}
  ;; :target-path "resources/public"

  :checkouts '[[miraj/core "0.1.0-SNAPSHOT"]
               [miraj/co-dom "0.1.0-SNAPSHOT"]
               ;; testing
               [adzerk/boot-cljs "2.0.0-OUTPUTFIX" :scope "test"]
               ;; [tmp.components/greeter "0.1.0-SNAPSHOT"]
               #_[miraj/boot-miraj "0.1.0-SNAPSHOT"]]

  :dependencies '[[org.clojure/clojure RELEASE]
                  [org.clojure/tools.namespace "0.2.11"]

                  [boot/core "2.6.0" :scope "provided"]

                  [org.clojure/clojurescript "1.9.473"]
                  [hipo "0.5.2"]
                  [adzerk/boot-cljs "2.0.0-OUTPUTFIX" :scope "test"]
                  [adzerk/boot-cljs-repl   "0.3.3"] ;; latest release
                  [com.cemerick/piggieback "0.2.1"  :scope "test"]
                  [weasel                  "0.7.0"  :scope "test"]
                  [org.clojure/tools.nrepl "0.2.12" :scope "test"]

                  [adzerk/boot-reload "0.5.1" :scope "test"] ;; cljs

                  ;;[adzerk/boot-cljs "1.7.228-2" :scope "test"]
                  ;; [adzerk/boot-cljs-repl "0.3.0" :scope "test"]

                  ;; [me.raynes/conch "0.8.0"]

                  [miraj/core "0.1.0-SNAPSHOT" :scope "test"]
                  [miraj/co-dom "0.1.0-SNAPSHOT" :scope "test"]
                  [miraj/boot-miraj "0.1.0-SNAPSHOT" :scope "test"]
                  [miraj/html "5.1.0-SNAPSHOT"]
                  [miraj.polymer/paper "1.2.3-SNAPSHOT"]
                  [miraj.polymer/iron "1.2.3-SNAPSHOT"]
                  ;; [miraj/dom "1.2.3-SNAPSHOT"]
                  ;; [miraj/platinum "1.2.3-SNAPSHOT"]

                  [tmp.components/greeter "0.1.0-SNAPSHOT"]

                  [pandeiro/boot-http "0.7.0"           :scope "test"]
                  ;; [crisptrutski/boot-cljs-test "0.2.2-SNAPSHOT"  :scope "test"]
                  ;; [com.cemerick/piggieback "0.2.1"  :scope "test"]
                  ;; [mount "0.1.10" :scope "test"]

                  [samestep/boot-refresh "0.1.0" :scope "test"] ;; clj reloading

                  [org.clojure/tools.nrepl "0.2.12" :scope "test"]])

                  ;; [weasel "0.7.0"  :scope "test"]

(require '[adzerk.boot-cljs      :refer [cljs]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
         '[boot.core :as boot]
         '[clojure.tools.namespace.repl :as tns]
         '[miraj.boot-miraj] ; :as mrj]
         '[boot.task.built-in]
         '[pandeiro.boot-http :refer :all]
         '[samestep.boot-refresh :refer [refresh]]
         '[adzerk.boot-reload :refer [reload]]
         )

(task-options!
 cljs-repl {:port 9090}
 reload {:port 9001}
 pom {:project +project+
      :version +version+}
 jar {:manifest {"root" "miraj"}}
 repl {:port 8081})

#_(deftask auto-test []
  (set-env! :source-paths #{"src" "test"})
  (comp (watch)
        (speak)
        (test-cljs)))

(boot/deftask xrefresh
  "Reload all changed namespaces on the classpath.
  Throws an exception in the case of failure."
  []
  (boot/with-pass-thru _
    (apply tns/set-refresh-dirs (boot/get-env :directories))
    (doseq [dir (boot/get-env :directories)] (println "REFRESH" dir))
    (println "NS" *ns*)
    (with-bindings {#'*ns* *ns*}
      (tns/refresh)
      (let [result (try (tns/refresh)
                        (catch Exception e (println "exc")
                               (throw e)))]
        ;;result
        (when (instance? Throwable result)
          (throw result))))))


(deftask build []
  ;;(set-env! :source-paths #{"src"})
  (comp #_(miraj.boot-miraj/compile :component #{'miraj.demo.greeter}
                         :keep true :pprint true :verbose true)
        (miraj.boot-miraj/compile :keep true :debug true :components true :test true)
        (miraj.boot-miraj/link    :debug true :components true :test true)
        (cljs)
        (target)
        (pom)
        (jar)
        (install)))

(deftask dev []
  (set-env! :source-paths #{"src"})
  (comp ;; (serve :dir "target/")
        ;; (target :dir #{"target/"})
        ;; (watch)
        ;; (speak)
        ;; (reload) ;; :on-jsload 'app.core/main)
        (cider)
        (repl)))
        ;; (cljs-repl)
        ;; (cljs :source-map true :optimizations :none)))

(deftask monitor
  "watch notivy compile, pom, jar, install"
  []
  (comp
   (watch)
   (notify :audible true)
   (refresh)
   (miraj.boot-miraj/compile :keep true :debug true :components true :test true)
   (miraj.boot-miraj/link    :debug true :components true :test true)
   ;; (miraj.boot-miraj/compile :debug true :components true)
   ;; (miraj.boot-miraj/link    :debug true :components true)
   (target)
;;   (target :dir #{"../../pages/hello-world/target"} :no-clean true))) ;;"target"
   (pom)
   (jar)
   (install)))

(deftask run
  "compile, serve"
  []
  (comp
   (serve :dir "target") ;; since bower_components is in :asset-paths, it's not on classpath
   (watch) ;; :verbose true)
   ;; (cljs-repl)
   (notify :audible true)
   (refresh)
   (miraj.boot-miraj/compile :keep true :debug true :components true :test true)
   (miraj.boot-miraj/link    :debug true :components true :test true)
   ;; (miraj.boot-miraj/compile :debug true)
   ;; (miraj.boot-miraj/link    :debug true)
   (reload)
   (target) ;; :no-clean true)
   (cljs)
   (target :no-clean true)
   #_(wait)))

