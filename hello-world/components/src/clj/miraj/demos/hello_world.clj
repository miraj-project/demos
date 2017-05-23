(ns miraj.demos.hello-world
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.polymer.protocols :as poly]
            [miraj.html.protocols :as hp]
            [miraj.polymer.paper :as paper]
             :reload))

(println "loading miraj.demos.hello-world")

;; in the lib, this will be exported as miraj.demos.hello-world.widgets/simple
(miraj/defcomponent simple :html hello-simple
  "Hello component - simple"
  (:codom
   (h/h1 "Simple Hello!")
   (h/span "Hello, simple")))

(miraj/defcomponent simpler :html hello-simpler
  "Hello component - simpler"
  (:codom
   (h/h1 "Simpler Hello!")
   (h/span "Hello, simpler")))

(miraj/defcomponent simplest :html hello-simplest
  "Hello component - simplest"
  (:codom (h/span "I am a mere <span> - just about the simplest component you could make.")))

(miraj/deflibrary widgets
  "Miraj component library: miraj.demos.hello-world.widgets"
  #:miraj{:require '[[miraj.demos.hello-world :export :all]
                     [miraj.demos.hello-world.bitterness :export :all]
                     [miraj.demos.hello-world.sweetness :export :all]]
          :defelements true})

(println "loaded miraj.demos.hello-world")

;; for testing:

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

