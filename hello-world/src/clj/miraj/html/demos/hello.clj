(ns miraj.html.demos.hello
  ;; (:import [hello.styles world])
  (:require [miraj.core :as miraj]
            [miraj.html :as h]))

;; (println "loading miraj.html.demos.hello")

(def home-meta
  {:title "Miraj hello demo"
   :description "this page demonstrates usage of miraj.html and polymer"
   :platform {:apple {:mobile-web-app {:capable true
                                       :status-bar-style :black
                                       :title :hello-str} ;; "Hello"
                      :touch {:icon {:uri "/images/touch/chrome-touch-icon-192x192.png"}}}
              :ms {:navbutton-color "#FFCCAA"
                   :tile-color "#3372DF"
                   :tile-image "images/ms-touch-icon-144x144-precomposed.png"}
              :mobile {:agent {:format :html5
                               :url "http://example.org/"}
                       :web-app-capable true}}})

(miraj/defpage hi-there
  "clojure docstring for webpage here (use metadata map for HTML, e.g. description)."
  home-meta  ;; metadata map
  #_(:require ;;[miraj.polymer.paper :as paper :refer [button card textarea]]
            [miraj.polymer.iron  :as iron :refer [list]]


            ;; third party
            ;; [vaadin :as vaadin :refer [combo-box date-picker grid icons upload]]
            ;; [bosonic :as b :refer [accordion draggable dropdown]]
            ;; (foo bar baz)
            ;; :cdn
            ;; :reload
            ;; :verbose
            )
  (:body
   (h/h1 "Hello Miraj!")
   (h/div :#cards
          (h/h2 "This is a div.")
          (h/div :.card-content "Unstyled demo of div and button.")
          (h/div :.card-actions (h/button :.indigo!raised "This is a button")))))

(miraj/defpage howdy
  "Test - howdy page"
  {:title "miraj howdy-page demo"
   :description "This is the second of two pages specified in one source file."}
  (:body
   (h/h1 "Howdy Miraj!")
   (h/h3 "testing")
   (h/div :#cards
          (h/h3 "Howdy, you ol' div!")
          (h/div :.card-content "Second of two pages from one source file.")
          (h/div :.card-actions (h/button :!raised "Action Button")))))


;;(println "loaded miraj.html.demos.hello\n")
