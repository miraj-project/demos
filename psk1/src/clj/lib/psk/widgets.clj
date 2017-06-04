(ns psk.widgets
  (:refer-clojure :exclude [list])
  (:require [miraj.core :as miraj :refer [defcomponent]]
            [miraj.html :as h]
            [miraj.polymer :as p]
            [miraj.polymer.protocols]
            ;;[miraj.core :as wc]
            #_[miraj.co-dom :as x]))

            ;; for testing only:
            ;; [miraj.co-dom :as codom]
            ;; #_:reload))

;; (println "loading psk.widgets")

(miraj/defcomponent greeting :html my-greeting
  "my-greeting component"

  (:codom
   (h/h1 :.page-title {:tabindex "-1"} (p/bind!! :greeting))
   (h/label {:for "greeting-input"} "Update text to change the greeting.")
    ;; Listens for "input" event and sets greeting to <input>.value
    (h/input :#greeting-input {:value (p/bind!! :input->greeting)}))

  {:polymer/properties {:greeting ^String{:value "Welcome"
                                          :type String
                                          :notify :true}}}
  miraj.polymer.protocols/Lifecycle
  (ready [] (.log js/console "READY greeting")))


(miraj/defcomponent list :html my-list
  "my-list component"

  (:codom
   (h/ul
      ;; <template is="dom-repeat" items="{{items}}">
    (p/repeat {:items (p/bind!! :items)}
              (h/li (h/span (p/bind!! :item))))))

  {:polymer/properties {:items ^Vector{:notify :true}}}

  miraj.polymer.protocols/Lifecycle
  (ready [] (this-as this (list/ready this))))

;; (println "loaded psk.widgets")
