(ns miraj.demos.hello-world.html.sweeter
  (:require [miraj.co-dom :refer [pprint serialize]]
            [miraj.html :as h :refer :all]
            [miraj.core :as miraj :refer [mcc defpage]] ;; normalize]]
            [clojure.java.io :as io]
            :reload))

(defpage index
  "sweeter html demo"
  #::h{:title "Hello World (sweeter)"
       :description "A page demonstrating miraj html programming"}
  (:css "/css/html.css")
  (:body
   (h/h1 :!centered
         (h/span :.greeting "Hello")
         (h/span {:$color "green"} " World")
         (h/span " (sweeter)!"))
   (h/div :#main!centered
          (h/span :!centered
                  (h/button :.foo
                            {:onclick "handle_click('sweeter')"}
                            "click me")))
   (h/script {:type "text/javascript" :src "/js/html.js"})))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; repl:

;; (serialize index)
;; (pprint index)

;; (let [filename "target/miraj/demos/hello_world/html/sweeter.html"]
;;   (io/make-parents filename)
;;   (spit filename (with-out-str (pprint index))))
;; (spit filename (serialize index)))


(binding [miraj/*debug* true
          miraj/*verbose* true
          miraj/*keep* true
          miraj/*pprint* true
          *compile-path* "target"]
  (miraj/mcc ;;:namespaces #{*ns*}
   ;;:pages ['index]
   ;; :page can be a var, an ns, or a symbol
   ;; :page (var index)
   ;; or
   :page 'miraj.demos.hello-world.html.sweeter/index
   ;;:page *ns*
   ;; or
   ;; :page 'miraj.demos.hello-world.html.sweeter
   :polyfill :lite
   ))

;;(-> *ns* clojure.core/meta keys)
