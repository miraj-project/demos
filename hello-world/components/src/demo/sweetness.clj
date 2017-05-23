(ns sweetness
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.demos.hello-world.widgets :as widgets :refer [sweet sweeter sweetest]]
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
    (widgets/sweet))

   (h/div
    (h/h2 "Here's sweeter:")
    (widgets/sweeter))

   (h/div
    (h/h2 "Here's sweetest:")
    (widgets/sweetest))
   ))

(println "loaded sweetness demo page")

