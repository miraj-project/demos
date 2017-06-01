(ns sweetness
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [acme.widgets :as acme :refer [sweet]] ;; sweeter sweetest]]
            ;; for testing only:
            [miraj.core :as wc]
            [miraj.co-dom :as x]
            :reload))

(println "loading sweetness demo page")

(miraj/defpage
  "Hello World Demo Page."

  (:js ["main.js"])

  ;; NOTE: v1 of Polymer uses "shady dom" be default, even in
  ;; browswers that support shadow dom.  Shady DOM does not prevent
  ;; leakage of document level styling into components.  To turn on
  ;; Shadow DOM (e.g. on Chrome) add "?dom=shadow" to the URL.
  ;; v2 of Polymer uses Shadow DOM by default.
  (:css ".bar {border: thick red solid;}
         .demo {text-align: center;margin:0 auto;padding:10px;width:50%;border:thin blue dotted;}")

  (:body
   (h/h1 "Sweetness Test Page")

   (h/div
    (h/h2 "Here's a sweet hello:")
    ;; click the component to toggle this class:
    (h/div :.demo.bar
    (acme/sweet {:greeting "Howdy"
                 :miraj.style/background-color "red"
                 :miraj.style/color "green"
                 ;; :miraj.style/text-align "center"
                 ;; :miraj.style/width "50%"
                 ;; :miraj.style/margin "0 auto"
                 } "world!")))

   ;; (h/div
   ;;  (h/h2 "Here's sweeter:")
   ;;  (h/div :.demo
   ;;         (acme/sweeter "hello")))

   ;; (h/div
   ;;  (h/h2 "Here's sweetest:")
   ;;  (h/div :.demo
   ;;         (acme/sweetest)))
   ))

(println "loaded sweetness demo page")
