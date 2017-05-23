(ns test-page
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.demo.papyrus :as papyrus :refer [button card]]

            ;; for testing only:
            [miraj.compiler :as wc]
            [miraj.co-dom :as x]
            :reload))

(println "loading test-page")

(miraj/defpage ^{::h/base "/"} index
  "Hello World Demo Page."

  ;; html metadata first
  {::h/title "Miraj demo: hello-world"
   ::h/description "this page demonstrates of a simple custom component."}

  ;; (:css "hello-world, goodbye-world {
  ;;   margin-left: auto;
  ;;   margin-right: auto;
  ;;  }
  ;;  h1 {text-align:center;}")

  (:body
   (h/h1 "Papyrus Test Page")

   (h/div
    (h/h2 "Here's a papyrus button:")
    (papyrus/button))

   (h/div
    (h/h2 "Here's a papyrus card:")
    (papyrus/card))

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
