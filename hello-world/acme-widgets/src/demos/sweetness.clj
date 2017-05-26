(ns sweetness
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [acme.widgets :as acme :refer [sweet sweeter]] ;; sweetest]]
            ;; for testing only:
            [miraj.core :as wc]
            [miraj.co-dom :as x]
            :reload))

(println "loading sweetness demo page")

(miraj/defpage
  "Hello World Demo Page."

  (:js ["main.js"])

  (:body
   (h/h1 "Sweetness Test Page")

   (h/div
    (h/h2 "Here's a sweet hello:")
    ;; click the component to toggle this class:
    (h/style ".foobar {border: thick red solid;}")
    (acme/sweet {:greeting "Howdy"
                 :miraj.style/text-align "center"} "world!"))

   (h/div
    (h/h2 "Here's sweeter:")
    (acme/sweeter "hello"))

   ;; (h/div
   ;;  (h/h2 "Here's sweetest:")
   ;;  (acme/sweetest))
   ))

(println "loaded sweetness demo page")

