(ns org.example.greetings.arrival
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [miraj.core :as core]
            [miraj.markup :as x]
            [miraj.html :as h]
            [miraj.polymer.Protocols :as protocols]
            [miraj.polymer.Behaviors :as behaviors]
            [miraj.polymer.Events :as events]
            [miraj.polymer.dom :as dom]
            ))

(println "loading org.example.greetings.arrival")

(core/defweb-codom hello-codom
  "hello-codom docstring"
  [greeting]
  (:require [polymer.paper :as paper :refer [button]])
  ;; (:style  '(styles.shared shared-styles))
  (:codom
   (h/style ":host {display: block;}")
   (h/h2 ::special.page-title (x/element :content))
   (h/span ::.paper-font-body2 "Update text to change the greeting.")
   ;; Listens for "input" event and sets greeting to <input>.value
   (h/input {:class "paper-font-body2" :value :input->greeting})
   (paper/button "hi")))

;; (pp/pprint hello-codom)

;; (print (x/serialize hello-codom))

;; (x/pprint hello-codom)

(core/defweb-properties AProps
  ;; hostAttributes map
  {:string-attribute "Value"
   :boolean-attribute true
   :tabindex 0}
  (^Boolean president true :read-only)
  (^Number x 0 (fn [new old] (+ new old)) :notify :reflect)
  (^String lname "Lincoln" (fn [new old] (.log js/console
                                               (str "Old pres: " old "; new: " new)))))
;; AProps
;; (pp/pprint AProps)

(core/defweb-properties BProps
  "BProps docstring here"
  (^Boolean bool-b true :read-only)
  (^Number ^{:doc "number y docstring"} y 99)
  (^String greeting "Howdy Miraj!" (fn [new old] (.log js/console (str "MSG CHANGE: " new)))))

(core/defweb-component hello-world
  "components.greetings/hello-world custom component"
  hello-codom
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

  behaviors/PaperButton
  ;; Polymer.Behaviors.PaperCheckedElement
    )

(core/defweb-component howdy-world
  "components.greetings/howdy-world custom component"
  hello-codom
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

  behaviors/PaperButton
  ;; Polymer.Behaviors.PaperCheckedElement
    )

;; (var hello-world)

;; (println (keys (meta (var hello-world))))

;; (println (keys (:miraj (meta (var hello-world)))))

;; (println (:component (:miraj (meta (var hello-world)))))
;; (h/pprint (:codom (:miraj (meta (var hello-world)))))

;; (h/pprint (miraj.markup/<<! hello-world))

;; (println (:prototype (:miraj (meta (var hello-world)))))

;; (h/<<! hello-world)

;(;meta (var my-greeting))

(println "loaded org.example.greetings.arrival\n")
