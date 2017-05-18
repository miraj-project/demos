(ns miraj.demos.hello-world.html.sweetest
  (:refer-clojure :exclude [map meta time])
  (:require [miraj.co-dom :refer [pprint serialize]]
            [miraj.html :as h :refer :all]
            [miraj.style :as s]
            [miraj.core :as miraj :refer [mcc defpage]] ;; normalize]]
            [clojure.java.io :as io]))

;; sweetest: use miraj.html functions, plus miraj.core defpage etc.,
;; and remove references to external dependencies from program text.

(defpage
  "sweetest html demo"
  ;; html metadata  (NB: #::h == #:miraj.html)
  #::h{:title "Hello Sweetest HTML World"
       :description "A page demonstrating miraj html programming"}

  (:body
   (h/h1 :!centered
         (h/span :.greeting "Hello,")
         (h/span :#foo1 {::s/color "blue"
                         ::s/before {:content "foo"}
                         ::s/after {:content "bar"}
                         ::s/hover {:color "green!important"
                                    :background-color "red"}}
                 " Sweetest HMTL ")
         (h/span " World!"))

   (h/div :#main!centered
          (h/span :!centered
                  (h/button :#btn.foo "click me")))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; repl:

;; (serialize index)
;; (pprint index)

#_(binding [miraj/*debug* true
          miraj/*verbose* true
          miraj/*keep* true
          miraj/*pprint* true
          *compile-path* "target"]
  (miraj/mcc :page (var index)
             :polyfill :lite ;; :full, :imports, :components, :shadow
             ))

;; cli:
;; $ boot miraj/mcc -p miraj.demos.hello-world.html.sweetest/index --polyfill :lite
