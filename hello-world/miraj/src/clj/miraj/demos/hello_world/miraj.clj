(ns miraj.demos.hello-world.miraj
  (:require [miraj.core :as miraj]
            [miraj.html :refer :all]
            [miraj.co-dom :as codom :refer [pprint serialize]]))

(miraj/defpage index
  "Hello World (miraj) Demo Page"

  ;; html metadata
  {:html/title "Miraj demo: hello-world"
   :html/description "This page demonstrates of a simple miraj webpage."}

  ;; import style element in html fragment
  ;; (:import [[styles.hello miraj]]) ;; /styles/hello/miraj.html

  ;; import raw css
  (:css [{:href "css/miraj.css"}])

  (:body
   (h1 "Hello World!")
   (h2 "(from miraj.core)")
   (div :#main
        (span :!centered
              (button {:onclick "handle_click()"} "click me"))
        )
   (script {:src "js/miraj.js"})))

;; (serialize index)
;; (spit "target/index.html" (serialize index))

;; (pprint index)
(spit "target/index.html" (with-out-str (pprint index)))

