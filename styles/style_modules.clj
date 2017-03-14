(ns miraj.demos.html.style.style-modules
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.polymer.paper :as paper :refer [button card]]
            [miraj.polymer.paper.styles :as pstyles :refer [color]]
            [miraj.polymer.iron :as iron :refer [icons icon]]
            :reload))

(miraj/defpage hello
  "Hello webpage using style modules."
  {:title "Miraj demo: Polymer style modules"
   :description "This page demonstrates usage of Polymer style modules."}

  ;; in this example, the dom-modules in galapagos.html have the same
  ;; content as the separate html files button.html and card.html
  (:import [[styles.themes.galapagos button card]] ;; = /styles/themes/galapagos.html
           :modules ;; treat button and card as dom-modules in galapagos.html
           :custom) ;; add is="custom-style" to <style> elements for button and card

  ;;better: import and include in one swoop:
  (:styles [[styles.themes.galapagos button card]])


  (:body
   (h/h1 "Hello Miraj!")
   (h/div ::cards
          (paper/card {:heading "Demo using style modules."}
                      (h/div (iron/icon {:icon "settings-bluetooth"}))
                      (h/div {:class "card-content"} "This page is styled by " (h/i "style modules") " styles.")
                      (h/div {:class "card-actions"}
                             (paper/button ::.green {:raised nil} "Some action"))))))

