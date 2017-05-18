(ns miraj.demos.hello-world.html.bitter
  (:refer-clojure :exclude [map meta time])
  (:require [miraj.co-dom :refer [pprint serialize]]
            [miraj.html :refer :all]
            [clojure.java.io :as io]))

;; bitter: use miraj.html functions, plus special attribute keywords

(def index
  (html {}
        (head {}
              (title "Hello, Bitter HTML World")
              (link {:rel "stylesheet" :href "/css/bitter.css"}))
        (body
         (h1 :!centered
             (span :.greeting "Hello,")
             (span {:miraj.style/color "green"} " Bitter HTML")
             (span " World!"))
         (div :#main!centered
              (span :!centered
                    (button :.foo
                            {:onclick "handle_click('bitter')"}
                            "click me")))
         (script {:type "text/javascript" :src "/js/bitter.js"}))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; repl:

;; (serialize index)
;; (pprint index)

(let [filename "target/miraj/demos/hello_world/html/bitter.html"]
  (io/make-parents filename)
  (spit filename (with-out-str (-> index pprint print))))
;; (spit filename (serialize index)))

