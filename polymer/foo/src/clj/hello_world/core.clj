(ns hello-world.core
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.polymer.paper :as paper :refer [button card]]
            [miraj.polymer.paper.styles :as pstyles :refer [color]]
            [miraj.polymer.iron :as iron :refer [icons icon]]
            ;; [miraj.demo.hello-world-html :as greeter] ; :refer :all] ; [hello]]
            [miraj.demo.basic :as greeter] ; :refer :all] ; [hello]]

            ;; for testing only:
            [miraj.compiler :as wc]
            [miraj.co-dom :as x]
            :reload))

(miraj/defpage index
  "Hello World Demo Page."

  ;; html metadata first
  {:html/title "Miraj demo: hello-world"
   :html/description "this page demonstrates of a simple custom component."}

  (:css "hello-world, goodbye-world {
    margin-left: auto;
    margin-right: auto;
   }
   h1 {text-align:center;}")

  (:body
   (h/h1 "Hello from index page!")

   (h/h2 "Here's a greeting component:")
   (greeter/hello {:foo "hi there!!"})
   ;; (h/h2 "Here's a farewell component:")
;;   (greeter/goodbye)
   ))

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
