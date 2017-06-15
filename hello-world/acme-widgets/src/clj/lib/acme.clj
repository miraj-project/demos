(ns acme
  (:require [miraj.core :as miraj :refer [deflibrary]]
            [miraj.html :as h]
            [miraj.html.protocols :as hp]))

;; (println "loading acme")

(deflibrary widgets
  "Miraj component library: acme.widgets"
  #:miraj{:require '[[acme :export :all]
                     [acme.bitterness :export :all]
                     [acme.sweetness :export :all]]
          :defelements true})


;; It is possible, but not recommended, to define components in the
;; same namespace used to define libraries:
;; (miraj/defcomponent simple :html hello-simple
;;   "Hello component - simple"
;;   (:codom
;;    (h/h1 "Simple Hello!")
;;    (h/span "Hello, simple")))

;; (miraj/defcomponent simpler :html hello-simpler
;;   "Hello component - simpler"
;;   (:codom
;;    (h/h1 "Simpler Hello!")
;;    (h/span "Hello, simpler")))

;; (miraj/defcomponent simplest :html hello-simplest
;;   "Hello component - simplest"
;;   (:codom (h/span "I am a mere <span> - just about the simplest component you could make.")))

;; (println "loaded acme")
