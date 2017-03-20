(ns miraj.demos.hello-world.polymer.bitter
  (:require [miraj.core :as miraj :refer [normalize]]
            [miraj.html :refer :all :exclude [button]]
            [miraj.polymer.paper :as paper :refer [button]]
            [miraj.co-dom :as codom :refer [pprint serialize]]
            :reload))

(def index
  (html
   (head
    (script {:src "bower_components/webcomponentsjs/webcomponents-lite.min.js"})
    (link {:rel "import" :href "bower_components/polymer/polymer.html"})
    (link {:rel "import" :href "bower_components/paper-button/paper-button.html"})
    (link {:rel "import" :href "bower_components/paper-card/paper-card.html"}))

   (body
    (h1 "Hello World!")
    (h2 "(from miraj.polymer.paper, bitter)")
    (div {:id "cards"}
         (paper/card {:heading "Hello, you ol' Card!"}
                     (div {:class "card-content"} "Some content")
                     (div {:class "card-actions"}
                          (paper/button {:raised nil
                                         :onclick "handle_click('bitter paper');"}
                                        "Some action"))))
    (script {:src "js/custom-elements.min.js"})
    (script {:src "js/miraj.js"}))))

;; (serialize index)
;; (pprint index)

;; (spit "target/bitter.html" (serialize index))
(spit "target/bitter.html"
      (with-out-str
        (codom/pprint index)
        ))
