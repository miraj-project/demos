(def +project+ 'tmp.components/greeter)
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
  :source-paths #{"src/clj" "src/libs"}

  ;; :resource-paths #{"resources"}
  ;; :target-path "resources/public"
  ;; :asset-paths #{"resources/public"}

  :checkouts '[[miraj/core "0.1.0-SNAPSHOT"]
               [miraj/co-dom "0.1.0-SNAPSHOT"]]
  ;;              [miraj/boot-miraj "0.1.0-SNAPSHOT"]]

  :dependencies '[[org.clojure/clojure RELEASE]
                  [org.clojure/clojurescript "1.9.473"]
                  ;; [me.raynes/conch "0.8.0"]
                  [miraj/html "5.1.0-SNAPSHOT"]
                  [miraj.polymer/paper "1.2.3-SNAPSHOT"]
                  [miraj.polymer/iron "1.2.3-SNAPSHOT"]
                  ;; [miraj/dom "1.2.3-SNAPSHOT"]
                  ;; [miraj/platinum "1.2.3-SNAPSHOT"]

                  [miraj/core "0.1.0-SNAPSHOT" :scope "test"]
                  [miraj/co-dom "0.1.0-SNAPSHOT" :scope "test"]
                  [miraj/boot-miraj "0.1.0-SNAPSHOT" :scope "test"]
                  [adzerk/boot-cljs "2.0.0-OUTPUTFIX" :scope "test"]
                  ;;[adzerk/boot-cljs "1.7.228-2" :scope "test"]
                  ;; [adzerk/boot-cljs-repl "0.3.0" :scope "test"]
                  ;; [adzerk/boot-reload "0.4.11" :scope "test"]
                  ;; [pandeiro/boot-http "0.7.0"           :scope "test"]
                  ;; [crisptrutski/boot-cljs-test "0.2.2-SNAPSHOT"  :scope "test"]
                  ;; [com.cemerick/piggieback "0.2.1"  :scope "test"]
                  ;; [mount "0.1.10" :scope "test"]
                  [org.clojure/tools.nrepl "0.2.12" :scope "test"]])

                  ;; [weasel "0.7.0"  :scope "test"]


(task-options!
  pom {:project +project+
       :version +version+}
  jar {:manifest {"root" "miraj"}}
  repl {:port 8080})

(require '[adzerk.boot-cljs      :refer [cljs]]
         ;; '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
         ;; '[adzerk.boot-reload    :refer [reload]]
         ;; '[crisptrutski.boot-cljs-test  :refer [test-cljs]]
         ;; '[pandeiro.boot-http    :refer [serve]]
         '[miraj.boot-miraj :as miraj])

#_(deftask auto-test []
  (set-env! :source-paths #{"src" "test"})
  (comp (watch)
        (speak)
        (test-cljs)))

(deftask build []
  ;;(set-env! :source-paths #{"src"})
  (comp #_(miraj/compile :component #{'miraj.demo.greeter}
                         :keep true :pprint true :verbose true)
        (miraj/compile :debug true)
        (miraj/link :verbose true)
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
   (miraj/compile :component #{'miraj.demo.greeter}
                  :keep true :pprint true :verbose true)
   (miraj/link :verbose true)
   (target)
   (pom)
   (jar)
   (install)))


;; (deftask cocompile []
;;   (fn middleware [next-handler]
;;     (fn handler [fileset]
;;       (let [fileset' (... fileset)
;;             fileset' (commit! fileset')
;;             result   (next-handler fileset')]
;;         result)))))
