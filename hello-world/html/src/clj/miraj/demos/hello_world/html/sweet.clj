(ns miraj.demos.hello-world.html.sweet
  (:require [miraj.co-dom :refer [pprint serialize]]
            [miraj.html :refer :all]
            [clojure.java.io :as io]
            :reload))

;; sweet: use miraj.html functions, plus special attribute keywords

(def index
  (html {}
        (head {}
              (title "Hello World (sweet)")
              (link {:rel "stylesheet" :href "/css/html.css"}))
        (body
         (h1 :!centered
             (span :.greeting "Hello")
             (span {:miraj.style/color "green"} " World")
             (span " (sweet)!"))
         (div :#main!centered
              (span :!centered
                    (button :.foo
                            {:onclick "handle_click('sweet')"}
                            "click me")))
         (script {:type "text/javascript" :src "/js/html.js"}))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; repl:

;; (serialize index)
;; (pprint index)

(let [filename "target/miraj/demos/hello_world/html/sweet.html"]
  (io/make-parents filename)
  (spit filename (with-out-str (-> index pprint print))))
;; (spit filename (serialize index)))

