(ns miraj.html.demos.goodbye
  (:require [miraj.core :as miraj]
            [miraj.co-dom :as codom]
            [miraj.html :as h]
            [miraj.polymer.paper :as paper :refer [button card]]
            [miraj.polymer.iron :as iron :refer [icon icons]]))

;; (println "loading miraj.html.demos.goodbye")

(def home-meta
  {:title "miraj goodbye demo"
   :description "this page demonstrates usage of miraj.html and polymer"
   :platform {:apple {:mobile-web-app {:capable true
                                       :status-bar-style :black
                                       :title :goodbye-str} ;; "Goodbye"
                      :touch {:icon {:uri "/images/touch/chrome-touch-icon-192x192.png"}}}
              :ms {:navbutton-color "#FFCCAA"
                   :tile-color "#3372DF"
                   :tile-image "images/ms-touch-icon-144x144-precomposed.png"}
              :mobile {:agent {:format :html5
                               :url "http://example.org/"}
                       :web-app-capable true}}})

(miraj/defpage sayonara
  home-meta
  (:body
   (h/h1 "GOODBYE MIRAJ!")
   (h/div (miraj.polymer.iron/icon {:icon "menu"}))
   (h/div ::cards
          (paper/card {:heading "Goodbye, you ol' Card!"}
                      (h/div {:class "card-content"} "Some content")
                      (h/div {:class "card-actions"}
                             (paper/button {:raised nil} "Some action"))))))

;;(-> goodbye-page var miraj/normalize miraj/optimize codom/pprint)

#_(defn homepage
  []
  (do
    ;; (println "HTML: ")
    ;; (println (h/serialize home-html))

    (-> home-html
        (with-meta home-meta)
        h/normalize
        h/optimize
        h/serialize)))

;; (println "loaded miraj.html.demos.goodbye")
