(def +project+ 'tmp/components)
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
 :source-paths #{"src/clj"}
 ;;:resource-paths #{"src/cljs"}
 ;; bower must be on classpath for http to pick it up
 ;; :resource-paths #{"resources/public"}
 :asset-paths #{"resources/public"}

 ;; :resource-paths #{"resources"}
 ;; :target-path "resources/public"

 ;; :repositories #(conj % ["clojars" {:url "https://clojars.org/repo/"}])

 :checkouts '[[miraj/core "0.1.0-SNAPSHOT"]
              [miraj/co-dom "1.0.0-SNAPSHOT"]
              ;; testing
              [adzerk/boot-cljs "2.0.0-OUTPUTFIX" :scope "test"]
              ;; [tmp.components/greeter "0.1.0-SNAPSHOT"]
              [miraj/boot-miraj "0.1.0-SNAPSHOT"]]

 :dependencies '[[org.clojure/clojure "1.9.0-alpha16"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [miraj/co-dom "1.0.0-SNAPSHOT"]
                 [miraj/core "0.1.0-SNAPSHOT"]

                 ;; [boot/core "2.6.0" :scope "provided"]

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

                 [miraj/boot-miraj "0.1.0-SNAPSHOT" :scope "test"]
                 [miraj/html "5.1.0-SNAPSHOT"]
                 [miraj.polymer/protocols "1.0.0-SNAPSHOT"]
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
         ;; '[boot.core :as boot]
         '[clojure.tools.namespace.repl :as tns]
         '[miraj.boot-miraj :as miraj]
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

(deftask monitor
  "repl development."
  []
  (comp (serve :dir "target" :port 3000)
        (cider)
        (cljs-repl)  ;; run (start-repl) to get the cljs repl
        (watch)
        (notify :audible true)))

(deftask bitterness []
  (comp (miraj/compile :components #{ 'miraj.demos.hello-world.bitterness}
                       :verbose true
                       :pprint true
                       ;;:debug true
                       )
        (miraj/link :libraries #{}
                    :verbose true
                    :pprint true)
        ;; :debug true)
        (miraj/compile :pages #{'bitterness}
                       :source-paths #{"src/demo"}
                       ;; instead we could use (sift :add-source #{"src/test/clj"})
                       :polyfill :lite
                       :verbose true
                       :pprint true)
        ;; :debug true)
        (cljs)))

(deftask hello []
  (comp (miraj/compile :components #{ 'miraj.demos.hello-world}
                       :verbose true
                       :pprint true
                       ;;:debug true
                       )
        (miraj/link :libraries true
                    :verbose true
                    :pprint true)
        ;; :debug true)
        (miraj/compile :pages #{'hello}
                       :source-paths #{"src/demo"}
                       :polyfill :lite
                       :verbose true
                       :pprint true)
        ;; :debug true)
        (cljs)))

(deftask sweetness []
  (comp (miraj/compile :components #{'miraj.demos.hello-world.sweetness}
                       :verbose true
                       :pprint true
                       ;;:debug true
                       )
        (miraj/link :libraries #{}
                    :verbose true
                    :pprint true)
        ;; :debug true)
        (miraj/compile :pages #{'sweetness}
                       :source-paths #{"src/demo"}
                       :polyfill :lite
                       :verbose true
                       :pprint true)
        ;; :debug true)
        (cljs)))

(deftask components []
  (comp (miraj/compile :components #{}
                       ;; :keep true
                       ;; :pprint true
                       :debug true)
        (miraj/link    :libraries #{}
                       :debug true)))

(deftask index []
  (let [pg 'index]
    (comp
     (components)
     (miraj/link :libraries #{}
                 :debug true)
     (miraj/compile :pages #{'index}
                    :source-paths #{"src/demo"}
                    :polyfill :lite
                    :debug true)
     (cljs))))

     #_(miraj/link    :pages #{pg}
                    :debug true)

;; (deftask dev []
;;   (comp
;;    (watch)
;;    (notify :audible true)
;;    ;; (refresh)
;;    (miraj/compile :components #{}) ;; :debug true); :test true)
;;    (miraj/link    :debug true :libraries true) ;; :test true)
;;    ;; now add test page to classpath:
;;    (sift :add-source #{"src/test/clj"})
;;    ;; compile the test page
;;    (miraj/compile :pages true :debug true)
;;    (miraj/link    :pages true :debug true)
;;    (target)))

#_(deftask page []
    (let [foo (set-env! :source-paths #(conj % "src/test/clj"))]
      (miraj/compile :pages true :debug true)))

(deftask run
  "compile, serve"
  []
  (comp
   (serve :dir "target") ;; since bower_components is in :asset-paths, it's not on classpath
   (watch) ;; :verbose true)
   ;; (cljs-repl)
   (notify :audible true)
   ;;   (refresh)
   (miraj/compile :keep true :debug true :components true); :test true)
   (miraj/link    :debug true :libraries true) ;; :test true)
   (target)
   ;; now add test page to classpath:
   (sift :add-source #{"src/test/clj"})
   ;; compile the test page
   (miraj/compile :pages true :debug true)
   (miraj/link    :pages true :debug true) ;; FIXME: this logic goes in compile -p
   ;;   (reload)
   (target :no-clean true)
   (cljs)
   (target :no-clean true)
   #_(wait)))
