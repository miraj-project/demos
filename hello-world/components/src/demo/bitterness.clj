(ns bitterness
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.demos.hello-world.widgets :as widgets :refer [bitter bitterer bitterest]]
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
    (widgets/bitter))

   (h/div
    (h/h2 "Here's bitterer:")
    (widgets/bitterer))

   (h/div
    (h/h2 "Here's bitterest:")
    (widgets/bitterest))
   ))

(println "loaded bitterness demo page")

