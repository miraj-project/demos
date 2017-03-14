(ns miraj.html.demos.style.inlined
  ;; (:import [hello.styles world])
  (:require [miraj.core :as miraj]
            [miraj.html :as h]))

(miraj/defpage hello
  "Hello webpage with inlined CSS and Javascript."
  {:title "Miraj demo: inlined styles and scripts."
   :description "This page demonstrates usage of inlined styles and scripts."}

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

  (:js "handleClick = function() { alert('ouch!');}")

  (:body
   ;; (h/link {:href "/styles/button.html" :rel "import"})
   (h/h1 "Hello Miraj!")
   ;; (h/div (hi/hello-world))
   ;; (h/div (bye/goodbye-world))

   (h/div :#main
          (h/h1 "Demo: inline styles and scripts.")
          (h/div :.content "This example demonstrates use of inlined CSS and Javascript.")
          (h/div :.actions
                 (h/button :#myButton.indigo!raised {:onClick "handleClick();"}
                           "Click me!")))))

;; (println "loaded work.pages.inline\n")
