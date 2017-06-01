(ns hello
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [acme.widgets :as acme :refer [simple simpler simplest]]
            ;; for testing only:
            [miraj.core :as wc]
            [miraj.co-dom :as x]
            :reload))

(println "loading hello demo page")

(miraj/defpage
  "Hello World Demo Page."

  (:js ["main.js"])

  (:body
   (h/h1 "Simpleness Test Page")

   (h/div
    (h/h2 "Here's a simple hello:")
    (acme/simple))

   (h/div
    (h/h2 "Here's simpler:")
    (acme/simpler))

   (h/div
    (h/h2 "Here's simplest:")
    (acme/simplest))
   ))

(println "loaded hello demo page")

