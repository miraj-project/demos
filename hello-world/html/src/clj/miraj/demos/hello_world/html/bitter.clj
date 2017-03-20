(ns miraj.demos.hello-world.html.bitter
  (:require [miraj.co-dom :refer [element pprint serialize]]
            [clojure.java.io :as io]
            :reload))

;; bitter: use only co-dom primitives, plus special attribute
;; keywords for id (:#foo), class (:.bar.baz), boolean
;; attributes (:!centered), and styles (:$color)

(def index
  (element :html {}
           (element :head {}
                    (element :title "Hello World (bitter)")
                    (element :link {:rel "stylesheet" :href "/css/html.css"}))
           (element :body
                    (element :h1 :!centered
                             (element :span :.greeting
                                      "Hello")
                             (element :span {:$color "green"}
                                      " World")
                             (element :span " (bitter)!"))
                    (element :div :#main!centered
                             (element :span :!centered
                                      (element :button :.foo
                                               {:onclick "handle_click('bitter')"}
                                               "click me")))
                    (element :script {:src "/js/html.js"}))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; repl:

;; (serialize index)
;; (pprint index)

(let [filename "target/miraj/demos/hello_world/html/bitter.html"]
  (io/make-parents filename)
  (spit filename (with-out-str (pprint index))))
  ;; (spit filename (serialize index)))


