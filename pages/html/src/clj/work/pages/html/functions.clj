(ns work.pages.html.functions
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.polymer.paper :as paper :refer [button card]]
            [miraj.polymer.paper.styles :as pstyles :refer [color]]
            [miraj.polymer.iron :as iron :refer [icons icon]]
            :reload))

(defn greenspan [text]
  (h/span {:style "background-color: #C8E6C9"} text))

(defn greenbutton [text]
  (h/button {:style "background-color: #C8E6C9"} text))

(defn greenlist [n]
  (h/ul {:style "background-color: #C8E6C9"}
   (for [i (range 1 (inc n))]
     (h/li (h/span "foo " i)))))

(defn amberdiv [args]
  (h/div {:style "background-color: #FFECB3"}
   args
   ))

(def amberdiv-title (h/h3 "I am an amberdiv composed of other functions, greenspan and greenlist."))

(defn my-div []
  (list amberdiv-title
        (greenspan "Here's another greenspan.")
        (greenlist 4)
        (h/br)))

;; WARNING: this is not really a component, it's just here to demo
;; functional HTML in Miraj. You do not want to use this
;; technique. Instead you should create a Polymer webcomponent, which
;; supports Clojurescript.
(defn todo-list [{:keys [title]} _]
  (list
   (h/script "
function addItem(text) {
var list = document.getElementById('todolist');
var entry = document.createElement('li');
entry.appendChild(document.createTextNode(text));
list.appendChild(entry);}
")
   (h/div {:style "background-color: #C8E6C9"}
          (h/h3 (or title "TODO"))
          (h/ul ::todolist
          (h/input ::todoinput {:type "text"})
                                 ;; :onChange "alert(document.getElementById(todoinput').value)"})
          (h/button {:onClick "addItem(document.getElementById('todoinput').value);"}
                    "Add")))))

(miraj/defpage hello
  "HTML webpage demonstrating functional composition."
  {:title "Miraj demo: imported styles"
   :description "This page demonstrates functional composition."}
  (:body
   (h/h1 "Hello Miraj!")
   (h/h2 "Functional HTML Demo.")
   (h/div ::.content
          "This page is composed using higher-order functions composed
          using the HTML functions provided by the miraj/html
          library. The higher-order functions defined and composed
          here are pure Clojure; they are " (h/i "not") " HTML
          components! For that, you need to use the Polymer
          capabilities of Miraj.")
   (h/h3 "Here is a greenspan created by a higher-order function:")
   (h/div  (greenspan "Hi there. I am a greenspan; I was begat by a higher-order function."))
   (h/h3 "Here is a greenlist created by a higher-order function:")
   (greenlist 3)
   (amberdiv (my-div))
   (h/h3 "Here is a greenbutton created by a higher-order function:")
   (greenbutton  "Click me. I don't do anything.")
   (h/h3 "Here is a green todo list created by a higher-order function:")
   (todo-list :title "Green TODO List")))

