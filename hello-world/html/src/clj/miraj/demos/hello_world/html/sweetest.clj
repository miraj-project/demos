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
  #::h{:title "Hello World (sweetest html)"
       :description "A page demonstrating miraj html programming"}
  ;; (:import [[imports sweetest]])
  (:body
   (h/h1 :!centered
         (h/span :.greeting "Hello")
         (h/span {:$color "green"} " World")
         (h/span " (sweetest html)!"))
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
             :linkage "imports.html"
             ))

;; cli:
;; $ boot miraj/mcc -p miraj.demos.hello-world.html.sweetest/index --polyfill :lite --linkage "imports.html"
