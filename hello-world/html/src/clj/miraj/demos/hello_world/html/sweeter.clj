(ns miraj.demos.hello-world.html.sweeter
  (:require [miraj.co-dom :as codom :refer [pprint serialize]]
            [miraj.html :as h :refer :all]
            [miraj.style :as s]
            [miraj.core :as miraj :refer [mcc defpage normalize optimize]]
            [clojure.java.io :as io]
            :reload))

(println "loading sweeter")

;; this will produce <ns>.html, i.e. miraj/demos/hello_world/html/sweeter.html
(defpage
  "sweeter html demo"
  (:require [miraj.html :as h :refer :all])

  #::h{:title "Hello, Sweeter HTML World!"
       :description "A page demonstrating miraj html programming"}

  ;; externel deps - see miraj demos for more syntax
  (:css ["/css/sweeter.css"])
  (:js ["/js/sweeter.js"])

  (:body
   (miraj.html/h1 :!centered
         (h/span :.greeting "Hello,")
         (h/span :#foo1 {::s/color "blue"
                         ::s/before {:content "foo"}
                         ::s/after {:content "bar"}
                         ::s/hover {:color "green!important"
                                    :background-color "red"}}
                 " Sweeter HMTL ")
         (h/span "World!"))

   (h/div :#main!centered
          (h/span :!centered
                  (h/button :#btn.foo
                            {:onclick "handle_click('sweeter')"}
                            "click me")))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; repl:

;; (def pg (-> *ns* clojure.core/meta :miraj/miraj :miraj/codom))

;; (-> *ns* normalize)

;; (-> pg serialize)
;; (-> pg pprint)

;; (def pgstr  (-> *ns* normalize serialize))
;; (print pgstr)
;; (def pgpretty (pprint pg))
;; (print pgpretty)

;; (let [filename "target/miraj/demos/hello_world/html/sweeter-x.html"]
;;   (io/make-parents filename)
;;   (spit filename (with-out-str (-> *ns*
;;                                    normalize
;;                                    optimize
;;                                    pprint
;;                                    print))))

;; (binding [miraj/*debug* true
;;           miraj/*verbose* true
;;           miraj/*keep* true
;;           codom/*pprint* true
;;           *compile-path* "target"]
;;   (miraj/mcc :page *ns* ;; on the cli, use the ns sym: 'miraj.demos.hello-world.html.sweeter
;;              :polyfill :lite
;;              :debug true))

(println "loaded sweeter")
