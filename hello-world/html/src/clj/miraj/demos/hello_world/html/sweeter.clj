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
  (:require [miraj.polymer.paper :as paper :refer [card]]
            [miraj.html :as h :refer :all])

  #::h{:title "Hello world (sweeter)"
       :description "A page demonstrating miraj html programming"}
  (:css "/css/html.css")
  (:body
   (miraj.html/h1 :!centered
         (h/span :.greeting "Hello")
         (h/span :#foo1 {::s/color "blue"
                         ::s/before {:content "foo"}
                         ::s/after {:content "bar"}
                         ::s/hover {:color "green!important"
                                    :background-color "red"}}
                 " World ")
         (h/span " (sweeter)!"))

   (h/div :#main!centered
          (h/span :!centered
                  (h/button :.foo
                            {:onclick "handle_click('sweeter')"}
                            "click me")))
   (h/script {:type "text/javascript" :src "/js/html.js"})))


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
