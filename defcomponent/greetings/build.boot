(def +project+ 'tmp.components/greetings)
(def +version+ "0.1.0-SNAPSHOT")

(set-env!
  :source-paths #{"src/cljs"}
  :resource-paths #{"src/clj"}
  ;; :resource-paths #{"resources"}
  ;; :target-path "resources/public"
  ;; :asset-paths #{"resources/public"}
  :dependencies '[[org.clojure/clojure "1.8.0"]
                  [org.clojure/clojurescript "1.7.228"]
                  [me.raynes/conch "0.8.0"]
                  [miraj/miraj "0.1.0-SNAPSHOT"]
                  [miraj/markup "0.1.0-SNAPSHOT"]
                  [miraj/html "5.1.0-SNAPSHOT"]
                  ;; [miraj/dom "1.2.3-SNAPSHOT"]
                  ;; [miraj/platinum "1.2.3-SNAPSHOT"]
                  [miraj/boot-miraj "0.1.0-SNAPSHOT"]
                  [adzerk/boot-cljs "1.7.228-1" :scope "test"]
                  [adzerk/boot-cljs-repl "0.3.0" :scope "test"]
                  [adzerk/boot-reload "0.4.11" :scope "test"]
                  [pandeiro/boot-http "0.7.0"           :scope "test"]
                  [crisptrutski/boot-cljs-test "0.2.2-SNAPSHOT"  :scope "test"]
                  [com.cemerick/piggieback "0.2.1"  :scope "test"]
                  [mount "0.1.10" :scope "test"]
                  [weasel "0.7.0"  :scope "test"]
                  [org.clojure/tools.nrepl "0.2.12" :scope "test"]])

(task-options!
  pom {:project +project+
       :version +version+}
  jar {:manifest {"root" "miraj"}}
  repl {:port 8080})

(require '[adzerk.boot-cljs      :refer [cljs]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
         '[adzerk.boot-reload    :refer [reload]]
         '[crisptrutski.boot-cljs-test  :refer [test-cljs]]
         '[pandeiro.boot-http    :refer [serve]]
         '[boot-miraj :as mrj])

(deftask auto-test []
  (set-env! :source-paths #{"src" "test"})
  (comp (watch)
        (speak)
        (test-cljs)))

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

#_(deftask build []
  (set-env! :source-paths #{"src"})
  (comp (cljs :optimizations :advanced)))

;; (deftask cocompile []
;;   (fn middleware [next-handler]
;;     (fn handler [fileset]
;;       (let [fileset' (... fileset)
;;             fileset' (commit! fileset')
;;             result   (next-handler fileset')]
;;         result)))))
