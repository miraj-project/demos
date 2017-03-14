(ns miraj.demo.farewell
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [miraj.core :as core]
            [miraj.co-dom :as x]
            [miraj.html :as h]
            [miraj.polymer.Protocols :as protocols]
            ;; [miraj.polymer.Behaviors :as behaviors]
            [miraj.polymer.Events :as events]
            [miraj.polymer.dom :as dom]
            ))

(println "loading miraj.demo.farewell")
;; (remove-ns 'org.example.greetings.goodbye)

(core/defweb-codom goodbye-codom
  "goodbye-codom docstring"
  [greeting]
  ;; metadata here?
  (:require ;;[polymer.iron :as iron :refer [icon]]
            [polymer.paper :as paper :refer [slider]])
  ;; (h/import  '(styles.shared shared-styles))
  ;;FIXME: (:style ...<garden exprs>...) ?
  ;; (h/style ":host {display: block;}")
  (:codom
   (h/h2 ::special.page-title (x/element :content))
   (h/span ::.paper-font-body2 "Update text to change the greeting.")
   ;; Listens for "input" event and sets greeting to <input>.value
   ;; (iron/icon {:icon "polymer"})
   (h/input {:class "paper-font-body2" :value :input->greeting})
   (paper/slider)))

(core/defweb-properties AProps
  ;; hostAttributes map
  {:string-attribute "Value"
   :boolean-attribute true
   :tabindex 0}
  (^Boolean president true :read-only)
  (^Number x 0 (fn [new old] (+ new old)) :notify :reflect)
  (^String lname "Lincoln" (fn [new old] (.log js/console
                                               (str "Old pres: " old "; new: " new)))))

(core/defweb-properties BProps
  "BProps docstring here"
  (^Boolean bool-b true :read-only)
  (^Number ^{:doc "number y docstring"} y 99)
  (^String greeting "Goodbye, Miraj!" (fn [new old] (.log js/console (str "MSG CHANGE: " new)))))

(core/defweb-component [goodbye goodbye-world]
  "components.greetings/goodbye-world custom component"
  goodbye-codom
  ;;Properties
  AProps
  BProps
  ;;Protocols
  protocols/This
  ;; private
  (_foo [] (.log js/console "FOO"))
  ;; public
  (bar [] (.log js/console "BAR"))

  protocols/Lifecycle
  (created [] (.log js/console "CREATED"))
  (attached [] (.log js/console "ATTACHED"))

  events/Gesture
  (with-element :special (tap [e] (.log js/console "you tapped the h1 element")))
  (down [x] (do (.log js/console "DOWN")))

  events/Mouse
  (click [x] (.log js/console "CLICK"))
  (dblclick [x] (.log js/console "DBLCLICK"))
  (mouseover [x] (.log js/console "MOUSEOVER"))

  ;; behaviors/PaperButton
  ;; Polymer.Behaviors.PaperCheckedElement
    )
