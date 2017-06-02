(def +project+ 'tmp/components)
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
 :source-paths #{"src/clj/lib"}
 :asset-paths #{"resources/public" "src/clj/app"}

 ;; :resource-paths #{"resources"}
 ;; :target-path "resources/public"

 ;; :repositories #(conj % ["clojars" {:url "https://clojars.org/repo/"}])

 :checkouts '[[miraj/core "1.0.0-SNAPSHOT"]
              [miraj/polymer "1.0.0-SNAPSHOT"]
              ;; testing
              [miraj/co-dom "1.0.0-SNAPSHOT"] ;; test?
              ;; [adzerk/boot-cljs "2.0.0-OUTPUTFIX" :scope "test"]
              ;; [tmp.components/greeter "0.1.0-SNAPSHOT"]
              [miraj/boot-miraj "0.1.0-SNAPSHOT"]]

 :dependencies '[[org.clojure/clojure "1.9.0-alpha17"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [miraj/co-dom "1.0.0-SNAPSHOT"]
                 [miraj/core "1.0.0-SNAPSHOT"]
                 [miraj/html "5.1.0-SNAPSHOT"]
                 [miraj/polymer "1.0.0-SNAPSHOT"]
                 [miraj.polymer/paper "1.2.3-SNAPSHOT"]
                 [miraj.polymer/iron "1.2.3-SNAPSHOT"]
                 ;; [miraj/dom "1.2.3-SNAPSHOT"]
                 ;; [miraj/platinum "1.2.3-SNAPSHOT"]

                 [miraj/boot-miraj "0.1.0-SNAPSHOT" :scope "test"]

                 ;; [boot/core "2.6.0" :scope "provided"]

                 [org.clojure/clojurescript "1.9.473"]
                 [hipo "0.5.2"]
                 ;; [adzerk/boot-cljs "2.0.0-OUTPUTFIX" :scope "test"]
                 [adzerk/boot-cljs "2.0.0" :scope "test"]
                 [adzerk/boot-cljs-repl   "0.3.3"] ;; latest release

                 [com.cemerick/piggieback "0.2.1"  :scope "test"]
                 [weasel                  "0.7.0"  :scope "test"]
                 [org.clojure/tools.nrepl "0.2.12" :scope "test"]

                 [adzerk/boot-reload "0.5.1" :scope "test"] ;; cljs

                 ;; [me.raynes/conch "0.8.0"]

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
         '[boot.task.built-in :as boot]
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

(deftask bitter []
  (comp (miraj/compile :components #{ 'acme.bitterness/bitter}
                       ;; :verbose true
                       ;; :pprint true
                       :debug true
                       )))

(deftask bitterer []
  (comp (miraj/compile :components #{ 'acme.bitterness/bitterer}
                       ;; :verbose true
                       ;; :pprint true
                       :debug true
                       )))

(deftask bitterest []
  (comp (miraj/compile :components #{ 'acme.bitterness/bitterest}
                       ;; :verbose true
                       ;; :pprint true
                       :debug true
                       )))

(deftask bitterness []
  (comp (miraj/compile :components #{ 'acme.bitterness}
                       ;; :verbose true
                       ;; :pprint true
                       :debug true
                       )))

(deftask hello []
  (comp (miraj/compile :components #{ 'acme}
                       :verbose true
                       :pprint true
                       ;;:debug true
                       )
        (miraj/link :libraries true
                    :verbose true
                    :pprint true)
        ;; :debug true)
        (miraj/compile :pages #{'hello}
                       :source-paths #{"src/demos"}
                       :polyfill :lite
                       :verbose true
                       :pprint true)
        ;; :debug true)
        (cljs)))

(deftask sweet []
  (comp (miraj/compile :components #{'acme.sweetness/sweet}
                       ;; :verbose true
                       ;; :pprint true
                       :debug true
                       )))

(deftask sweeter []
  (comp (miraj/compile :components #{'acme.sweetness/sweeter}
                       ;; :verbose true
                       ;; :pprint true
                       :debug true
                       )))

(deftask sweetest []
  (comp (miraj/compile :components #{'acme.sweetness/sweetest}
                       ;; :verbose true
                       ;; :pprint true
                       :debug true
                       )))

(deftask sweetness []
  (comp (miraj/compile :components #{'acme.sweetness/sweet 'acme.sweetness/sweeter}
                       ;; :verbose true
                       ;; :pprint true
                       :debug true
                       )))
        ;; (miraj/link :libraries #{'acme/widgets}
        ;;             ;; :verbose true
        ;;             ;; :pprint true)
        ;;             :debug true)
        ;; (miraj/compile :pages #{'sweetness}
        ;;                :source-paths #{"src/demos"}
        ;;                :polyfill :lite
        ;;                ;; :verbose true
        ;;                ;; :pprint true)
        ;;                :debug true)
        ;; (cljs)))

(deftask lib []
  (comp
   (miraj/compile :components #{}
                  ;; :keep true
                  ;; :pprint true
                  :debug true)
   (miraj/link :libraries #{'acme/widgets}
               ;; :verbose true
               ;; :pprint true)
               :debug true)))

(deftask index []
  (let [pg 'index]
    (comp
     (lib)
     (miraj/compile :pages #{'index}
                    :source-paths #{"src/demos"}
                    :polyfill :lite
                    :debug true)
     (cljs))))

(deftask demo
  []
  (comp
   (cljs)
   (miraj/compile :demo 'acme.widgets
                  :components #{} ;; all
                  ;; :components #{'acme.sweetness/sweetest} ;; just this one
                  ;; :components #{'acme.bitterness} ;; all components in this ns
                  ;; :components #{'acme.bitterness/bitter 'acme.sweetness/sweet} ;; just these 2
                  ;; :components #{'acme.bitterness 'acme.sweetness/sweet} ;; all bitter, one sweet
                  ;; :source-paths #{"src/demos"}
                  :polyfill :lite
                  ;; :verbose true
                  ;; :pprint true)
                  :debug true)))

(deftask app
  [p page PAGE sym   "App page to compile."]
  (comp
   (boot/sift     :to-source #{#".*clj"})
   ;; optional: keep the lib source:
   (boot/sift     :to-resource #{#"widgets.clj"})
   (miraj/compile :pages #{page}
                  :polyfill :lite
                  :debug true)))
