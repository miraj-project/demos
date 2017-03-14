(ns hello.miraj.page.hello
  (:require [miraj.html :as h]
            [polymer.paper :as paper]
            [polymer.iron :as iron]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(def home-meta
  {:title "miraj hello demo"
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

(def home-html
    (h/html
     (h/require '[polymer.paper :as paper :refer [button card]]
                '[polymer.iron :as iron :refer [icon icons]])
     (h/import '(hello.styles hello world))
               ;;'(hello.scripts polyfill-lite-min))
     (h/body
      (h/h1 "HELLO MIRAJ!")
      (h/div (iron/icon {:icon "menu"}))
      (h/div ::cards
             (polymer.paper/card {:heading "Hello, you ol' Card!"}
                         (h/div {:class "card-content"} "Some content")
                         (h/div {:class "card-actions"}
                                (polymer.paper/button {:raised nil} "Some action")))))))

(defn homepage
  []
  (do
    ;; (println "HTML: ")
    ;; (println (h/serialize home-html))

    (-> home-html
        (with-meta home-meta)
        h/normalize
        h/optimize
        h/serialize)))


