(ns bitterness
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [acme.widgets :as acme :refer [bitter bitterer bitterest]]
            ;; for testing only:
            [miraj.core :as wc]
            [miraj.co-dom :as x]
            :reload))

(println "loading bitterness demo page")

(miraj/defpage
  "Bitterness Demo Page."

  (:js ["main.js"])

  (:body
   (h/h1 "Bitterness Test Page")

   (h/div
    (h/h2 "Here's a bitter hello:")
    (acme/bitter))

   (h/div
    (h/h2 "Here's bitterer:")
    (acme/bitterer))

   (h/div
    (h/h2 "Here's bitterest:")
    (acme/bitterest))
   ))

(println "loaded bitterness demo page")

