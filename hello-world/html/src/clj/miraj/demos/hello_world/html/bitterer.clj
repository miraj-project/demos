(ns miraj.demos.hello-world.html.bitterer
  (:refer-clojure :exclude [map meta time])
  (:require [miraj.co-dom :refer [element pprint serialize]]
            [clojure.java.io :as io]))

;; bitterer: use only co-dom primitives, plus special attribute
;; keywords for id (:#foo), class (:.bar.baz), boolean
;; attributes (:!centered), and styles (e.g. :miraj.style/color)

(def index
  (element :html {}
           (element :head {}
                    (element :title "Hello, Bitterer HTML World")
                    (element :link {:rel "stylesheet" :href "/css/bitterer.css"}))
           (element :body
                    (element :h1 :!centered
                             (element :span :.greeting
                                      "Hello,")
                             (element :span {:miraj.style/color "green"}
                                      " Bitterer HTML")
                             (element :span " World!"))
                    (element :div :#main!centered
                             (element :span :!centered
                                      (element :button :.foo
                                               {:onclick "handle_click('bitterer')"}
                                               "click me")))
                    (element :script {:src "/js/bitterer.js"}))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; repl:

;; (serialize index)
;; (pprint index)

(let [filename "target/miraj/demos/hello_world/html/bitterer.html"]
  (io/make-parents filename)
  (spit filename (with-out-str (-> index pprint print))))



