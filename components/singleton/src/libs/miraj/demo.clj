(ns miraj.demo
  (:require [miraj.core :as miraj]
            [miraj.compiler :as wc]
             :reload))

;; (ns-unmap *ns* 'greeting)

(miraj/deflibrary greeter
  "docstring for component lib miraj.demo.greeter"
  #:miraj{:require '[[miraj.demo.greeter :export :all]
                     ]
          :defelements true})

;; (def nss (-> *ns* ns-name))

;; (do  ;; miraj/compile :component  #{'miraj.demo.greeting 'miraj.demo.adieu}
;;   (require '[miraj.compiler :as wc] :reload)
;;   (binding [*compile-path* "tmp"]
;;     (wc/compile-webcomponent-nss :html #{'miraj.demo.greeting 'miraj.demo.adieu} true true)
;;     (wc/compile-webcomponent-nss :cljs #{'miraj.demo.greeting 'miraj.demo.adieu} true true)))

;; ;; for dev, disable stencil cache:
;; (stencil.loader/set-cache (clojure.core.cache/ttl-cache-factory {} :ttl 0))

;; (do
;;   (require '[miraj.compiler :as wc] :reload)
;;   (binding [*compile-path* "tmp"]
;;     (wc/link-component-libs #{nss} true)))

