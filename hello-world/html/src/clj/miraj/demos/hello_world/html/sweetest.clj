(ns miraj.demos.hello-world.html.sweetest
  (:require [miraj.co-dom :refer [pprint serialize]]
            [miraj.html :as h :refer :all]
            [miraj.core :as miraj :refer [mcc defpage]] ;; normalize]]
            [clojure.java.io :as io]
            :reload))

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
         (h/span {:miraj.style/color "green"} " Sweetest HTML")
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
