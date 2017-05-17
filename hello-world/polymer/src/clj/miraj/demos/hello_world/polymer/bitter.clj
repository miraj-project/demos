(ns miraj.demos.hello-world.polymer.bitter
  (:require [miraj.core :as miraj :refer [normalize]]
            [miraj.html :refer :all :exclude [button]]
            [miraj.polymer.paper :as paper :refer [button]]
            [miraj.co-dom :as codom :refer [pprint serialize]]
            [clojure.java.io :as io]
            :reload))

;; we can use polymer components with plain html, no miraj sweetness
;; we have to spell everything out explicitly:
(def index
  (html
   (head
    (script {:src "/bower_components/webcomponentsjs/webcomponents-lite.min.js"})
    (link {:rel "import" :href "/bower_components/polymer/polymer.html"})
    (link {:rel "import" :href "/bower_components/paper-button/paper-button.html"})
    (link {:rel "import" :href "/bower_components/paper-card/paper-card.html"})
    (link {:rel "stylesheet" :href "/css/bitter.css"}))

   (body
    (h1 "Hello, Bitter Polymer World!")
    (div {:id "cards"}
         (paper/card {:heading "Hello, you ol' Card!"
                      :miraj.style/margin "12px"}
                     (div {:class "card-content"} "Some content")
                     (div {:class "card-actions"}
                          (paper/button {:raised nil
                                         :onclick "handle_click('bitter paper');"}
                                        "Some action"))))
    (script {:src "/js/custom-elements.min.js"})
    (script {:src "/js/bitter.js"}))))

;; in a repl:
;; (serialize index)
;; (pprint index)

;; this page is not compilable since it does not use defpage; we must do it by hand:
(let [filename "target/miraj/demos/hello_world/polymer/bitter/index.html"]
  (io/make-parents filename)
  (spit filename (with-out-str (-> index pprint print))))
