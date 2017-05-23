(ns index
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.demos.hello-world.widgets :as widgets :refer :all]
            ;; for testing only:
            [miraj.core :as wc]
            [miraj.co-dom :as x]
            :reload))

(println "loading index")

(miraj/defpage
  "Hello World Demo Page."

  ;; html metadata first
  {::h/title "Miraj demo: hello-world"
   ::h/description "this page demonstrates of a simple custom component."}

  ;; (:css "hello-world, goodbye-world {
  ;;   margin-left: auto;
  ;;   margin-right: auto;
  ;;  }
  ;;  h1 {text-align:center;}")

  (:js ["main.js"])

  (:body
   (h/h1 "Hello-Widgets Test Page")

   (h/div
    (h/h2 "Here's a simple component:")
    (h/div {:miraj.style/border "thick black solid;"}
           (widgets/simple)))

   (h/div
    (h/h2 "Here's a simpler component:")
    (h/div {:miraj.style/border "thick black solid;"}
           (widgets/simpler)))

   (h/div
    (h/h2 "Here's a simplest component:")
    (h/div {:miraj.style/border "thick black solid;"}
           (widgets/simplest)))

   (h/div
    (h/h2 "Here's a bitter component:")
    (widgets/bitter))

   (h/div
    (h/h2 "Here's a bitterer component:")
    (h/div {:miraj.style/border "thick black solid;"}
           (widgets/bitterer)))

   (h/div
    (h/h2 "Here's a bitterest component:")
    (h/div {:miraj.style/border "thick black solid;"}
           (widgets/bitterest)))

   (h/div
    (h/h2 "Here's a sweet hello:")
    (widgets/sweet))

   (h/div
    (h/h2 "Here's sweeter:")
    (widgets/sweeter))

   (h/div
    (h/h2 "Here's sweetest:")
    (widgets/sweetest))
   ))

(println "loaded index")

;; (stencil.loader/set-cache (clojure.core.cache/ttl-cache-factory {} :ttl 0))

;; (binding [*compile-path* "tmp"]
;;   ;;miraj/*miraj-sym* (str (gensym "miraj"))]
;;   (wc/compile-page-nss #{(-> *ns* ns-name) 'work.pages.hello} true true))

;; (binding [*compile-path* "tmp"]
;;   (wc/link-pages #{(-> *ns* ns-name) 'work.pages.hello} true))

;; (-> 'miraj.demo.greeter find-ns meta)


;(-> index var meta)

;(-> *ns* ns-name)

;(remove-ns 'work.pages.styled.imported)
;(remove-ns 'miraj.demo.hello)


;; (remove #(or (nil? %) (= 'clojure.core (-> % ns-name)))
;;         (map (fn [[k v]] (-> v meta :ns)); :miraj/miraj)) ;(:ns (meta (var v))))
;;              (ns-refers *ns*)))

;; (remove nil? ;; #(or (nil? %) (= 'clojure.core (-> % ns-name)))
;;         (filter (fn [v] (-> v meta :miraj/miraj)) ;(:ns (meta (var v))))
;;              (all-ns)))
;; ;             (ns-refers *ns*)))

;; (all-ns)

;(ns-refers *ns*)

;;(ns-aliases *ns*)
