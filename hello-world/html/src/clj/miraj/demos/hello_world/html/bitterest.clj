(ns miraj.demos.hello-world.html.bitterest
  (:require [miraj.co-dom :refer [element pprint serialize]]
            [clojure.java.io :as io]
            :reload))

;; bitterest: use only co-dom primitives, no sugar

(def index
  (element :html {}
           (element :head {}
                    (element :title "Hello World (bitterest)")
                    (element :link {:rel "stylesheet" :href "/css/html.css"}))
           (element :body
                    (element :h1 {:centered "centered"}
                             (element :span {:class "greeting"}
                                      "Hello")
                             (element :span {:style "color:green;"}
                                      " World")
                             (element :span " (bitterest)!"))
                    (element :div {:id "main" :centered ""}
                             (element :span {:centered ""}
                                      (element :button {:class "foo"
                                                        :onclick "handle_click('bitterest')"}
                                               "click me"))
                             )
                    (element :script {:src "/js/html.js"}))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; repl:

;; (serialize index)
;; (pprint index)

(let [filename "target/miraj/demos/hello_world/html/bitterest.html"]
  (io/make-parents filename)
  (spit filename (with-out-str (-> index pprint print))))


