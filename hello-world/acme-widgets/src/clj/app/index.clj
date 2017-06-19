(ns index
  (:require [miraj.core :as miraj]
            [miraj.html :as h]))

(println "loading index")

(miraj/defpage
  "Hello World Demo App."
  (:require [miraj.polymer.paper :as paper :refer [card]]
            [acme.widgets :as acme :refer :all])

  ;; html metadata first
  {::h/title "Miraj Demo App: Hello World"
   ::h/description "this page demonstrates of a simple Miraj app."}

  ;; (:css "hello-world, goodbye-world {
  ;;   margin-left: auto;
  ;;   margin-right: auto;
  ;;  }
  ;;  h1 {text-align:center;}")

  (:body
   (h/h1 "Hello World Demo App")

   (h/div
    (h/h2 "Here's a Polymer paper-card:")
    (paper/card (h/div "Hi there")))

   (h/div
    (h/h2 "Here's a bitter component:")
    (acme/bitter))

   (h/div
    (h/h2 "Here's a bitterer component:")
    (h/div {:miraj.style/border "thick black solid;"}
           (acme/bitterer)))

   (h/div
    (h/h2 "Here's a bitterest component:")
    (h/div {:miraj.style/border "thick black solid;"}
           (acme/bitterest)))

   (h/div
    (h/h2 "Here's a sweet hello:")
    (acme/sweet))

   (h/div
    (h/h2 "Here's sweeter:")
    (acme/sweeter))

   (h/div
    (h/h2 "Here's sweetest:")
    (acme/sweetest))
   ))

(println "loaded index")
