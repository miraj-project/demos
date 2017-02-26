(ns work.pages.garden
  ;; (:import [hello.styles world])
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.polymer.paper :as paper :refer [button card]]
            [miraj.polymer.paper.styles :as pstyles :refer [color]]
            [miraj.polymer.iron :as iron :refer [icons icon]]

            [garden.core :refer [css]]

            [clojure.pprint :as pp]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            :reload))

;; (println "loading work.pages.inline")

(def home-meta
  {:title "Miraj demo: inline hello"
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

(miraj/defpage styles
  "Inline version of hello webpage."
  home-meta  ;; metadata map

  ;; you can have as many :css directives as you want.
  ;; to style custom elements, pass :custom as first arg.
  (:css :custom " #cards {
    @apply(--layout-vertical);
    @apply(--center-justified);
    max-width: 800px;
    margin-left: auto;
    margin-right: auto;
   }")

  (:css :custom "paper-card {
                 --paper-card-header-color: var(--paper-indigo-500);
                 --paper-card-background-color: var(--paper-indigo-100);
                 color:blue;
                 width: 100%;
                 margin-bottom: 16px;}
            .card-actions {text-align:center;margin-left:auto;margin-right:auto;}")

   (:css :custom "paper-button {
    background-color: var(--paper-indigo-600);
    color: var(--paper-orange-300) !important;}
  #container {
    display: flex;
  }")

  ;; if you do not use :custom, then you cannot use custom style vars like paper-styles/colors
  (:css "h1 {
    background-color: #009688; /* --paper-teal-500 */
    color: #E65100;}") ;; --paper-orange-900

  (:body
   ;; (h/link {:href "/styles/button.html" :rel "import"})
   (h/h1 "Hello Miraj!")
   ;; (h/div (hi/hello-world))
   ;; (h/div (bye/goodbye-world))

   (h/div ::cards
          (paper/card {:heading "Work inline demo."}
                      (h/div (iron/icon {:icon "settings-bluetooth"}))
                      (h/div {:class "card-content"} "This example demonstrates use of inline CSS with custom color vars.")
                      (h/div {:class "card-actions"}
                             (paper/button ::.indigo {:raised nil} "Some action"))))))

;; (println "loaded work.pages.inline\n")
