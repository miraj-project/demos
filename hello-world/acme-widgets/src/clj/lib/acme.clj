(ns acme
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.polymer.protocols :as poly]
            [miraj.html.protocols :as hp]
            [miraj.polymer.paper :as paper]
             :reload))

;; (println "loading acme")

;; in the lib, this will be exported as acme.widgets/simple
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
  "Miraj component library: acme.widgets"
  #:miraj{:require '[[acme :export :all]
                     [acme.bitterness :export :all]
                     [acme.sweetness :export :all]]
          :defelements true})

;; (println "loaded acme")
