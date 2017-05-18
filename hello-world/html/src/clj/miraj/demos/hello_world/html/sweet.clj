(ns miraj.demos.hello-world.html.sweet
  (:refer-clojure :exclude [map meta time])
  (:require [miraj.co-dom :as codom :refer [pprint serialize]]
            [miraj.html :as h :refer :all]
            [miraj.style :as s]
            [miraj.core :as miraj :refer [mcc defpage normalize optimize]]
            [clojure.java.io :as io]))
 
;; this will produce <ns>/index.html, i.e. miraj/demos/hello_world/html/sweet/index.html
(defpage index
  "sweet html demo"

  #::h{:title "Hello, Sweet HTML world!"
       :description "A page demonstrating miraj html programming"}

  ;; embedded css and js - still bitter!
  (:css "h1 {color:blue;}
.greeting {color:red;}
#main {background-color: #A5D6A7;}
*[centered] {text-align: center;}
")

(:js "handle_click = function(msg) {
    console.log(msg + \" click\");
    alert(\"Hello, Sweet HTML World!\");
}")

  (:body
   (h/h1 :!centered
         (h/span :.greeting "Hello,")
         (h/span {::s/color "blue"} " Sweet HTML ")
         (h/span " World!"))

   (h/div :#main!centered
          (h/span :!centered
                  (h/button :.foo
                            {:onclick "handle_click('sweet')"}
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

;; (let [filename "target/miraj/demos/hello_world/html/sweet-x.html"]
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
;;   (miraj/mcc :page *ns* ;; on the cli, use the ns sym: 'miraj.demos.hello-world.html.sweet
;;              :polyfill :lite
;;              :debug true))

;; (println "loaded sweet")
