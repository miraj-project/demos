(ns work.pages.hello
  ;; (:import [hello.styles world])
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.polymer.paper :as paper :refer [button card]]
            [miraj.polymer.paper.styles :as pstyles :refer [color]]
            [miraj.demo.greeter :as hi] ; :refer :all] ; [hello]]
            :reload))

;; (println "loading work.pages.hello")

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
   (h/div ::cards
          (paper/card {:heading "This is a paper-card."}
                      (h/div {:class "card-content"} "Unstyled demo of paper-card and paper-button.")
                      (h/div {:class "card-actions"}
                             (paper/button ::.indigo {:raised nil} "This is a paper-button"))))))

(miraj/defpage howdy
  "Test - howdy page"
  {:title "miraj howdy-page demo"
   :description "This is the second of two pages specified in one source file."}
  (:body
   (h/h1 "Howdy Miraj!")
   (h/h3 "testing")
   (miraj.demo.greeter/hello)
   (h/div ::cards
          (paper/card {:heading "Howdy, you ol' Card!"}
                      (h/div {:class "card-content"} "Second of two pages from one source file.")
                      (h/div {:class "card-actions"}
                             (paper/button {:raised nil} "Action Button"))))))


;;(println "loaded work.pages.hello\n")
