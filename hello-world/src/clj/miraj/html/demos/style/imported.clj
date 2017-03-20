(ns miraj.html.demos.style.imported
  (:require [miraj.core :as miraj]
            [miraj.html :as h]

            ;; for testing only:
            [miraj.compiler :as wc]
            [miraj.co-dom :as x]))

(miraj/defpage index
  "Styled version of hello webpage."

  ;; html metadata first
  {::h/title  "Miraj demo: imported styles"
   ::h/description "this page demonstrates usage of imported styles."}

  ;; imports are html files that may contain any html. here we treat
  ;; the namespace symbol as the path of a directory containing files:

;  (:import [[styles.hello button card]]) ;; /styles/hello/button.html, /styles/hello/card.html

  ;; imported modules are different; they are <dom-module> elements in an HTML
  ;; file rather than files in a directory. see style_modules.clj for an example.

;;  (:css [[styles.css hello]])

  (:body
   ;; (h/link {:href "/styles/button.html" :rel "import"})
   (h/h1 "Hello from index page!")

   ;; (h/script {:src "/miraj/demo/salutations.js"})

   (h/div :#cards
          (h/h2 "Demo of index page.")
          (h/div :.card-content "This is a styled paper-card, containing a styled paper-button.")
          (h/div :.card-actions (h/button :.green!raised "Some action")))))

#_(miraj/defpage home
  "demo, second page in pagespace"

  ;; html metadata first
  {:html/title "Miraj demo: page 2"
   :html/description "this page demonstrates multiple pages in a pagespace."}
;;  (:import [[styles.hello button card]]) ;; /styles/hello/button.html, /styles/hello/card.html
  (:body
   ;; (h/link {:href "/styles/button.html" :rel "import"})
   (h/h1 "Hello from home page!")
   ;; (greetings/howdy)
   ;; (h/script {:src "/miraj/demo/salutations.js"})

   ;; (h/h3 "here's another component:")
   ;; (greetings/hello)

   (h/div :#cards
          (h/h2 "Demo of imported styles.")
          (h/div :.card-content "This is a styled div, containing a styled button.")
          (h/div :.card-actions (h/button :.green!raised "Some action")))))

