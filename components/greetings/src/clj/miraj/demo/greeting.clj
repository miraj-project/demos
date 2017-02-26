(ns miraj.demo.greeting
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.polymer.Protocols :as protocols]
            [miraj.polymer.paper :as paper]
            ;; [miraj.polymer.paper.behavior :as behaviors]
            [miraj.polymer.Events :as events]
            [miraj.polymer.dom :as dom]

            ;; for testing only:
            [miraj.compiler :as wc]
            [miraj.co-dom :as x]
            :reload))

;;(println "loading org.example.greetings.arrival")

;(ns-unalias *ns* 'paper)

(miraj/defweb-properties AProps
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

(miraj/defweb-properties HelloProps
  "HelloProps docstring here"
  (^Boolean bool-b true :read-only)
  (^Number ^{:doc "number y docstring"} y 99)
  (^String greeting "Hello, Miraj!" (fn [new old] (.log js/console (str "MSG CHANGE: " new)))))

(miraj/defweb-codom hello-codom
  "hello-codom docstring"
  [greeting]
  ;; (:require [miraj.polymer.paper :as paper :refer [button]])
  ;; (:style  '(styles.shared shared-styles))
  (:codom
   (h/style ":host {display: block;} span {background-color:teal;}")
   (h/h1 "Hello!")
   (h/h2 ::special.page-title :greeting)
   (h/span ::.paper-font-body2 "Update text to change the greeting.")
   ;; Listens for "input" event and sets greeting to <input>.value
   (h/input {:class "paper-font-body2" :value :input->greeting})
   #_(paper/button "hi")))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(miraj/defcomponent [hello greeting-hello]
  "custom component: miraj.demo.greeting/hello => <greeting-hello>"
  hello-codom

  ;;Properties
  AProps
  HelloProps

  ;;Protocol Implementations
  protocols/This
  ;; private
  (_foo [] (.log js/console "FOO"))
  ;; public
  (bar [] (.log js/console "BAR"))

  protocols/Lifecycle
  (created [] (.log js/console "CREATED"))
  (attached [] (.log js/console "ATTACHED"))

  ;; FIXEM: protocols, not events. an event suite is a protocol

  events/Gesture
  ;; (with-element :special (tap [e] (.log js/console "you tapped the h1 element")))
  (down [x] (do (.log js/console "DOWN")))

  events/Mouse
  (click [x] (.log js/console "CLICK"))
  (dblclick [x] (.log js/console "DBLCLICK"))
  (mouseover [x] (.log js/console "MOUSEOVER"))

  ;; FIXME:  extensions, not behaviors or mixins
  ;; mixins
                                        ;  behavior/PaperButton
                                        ;  paper.extension/button
  ;; Polymer.Behaviors.PaperCheckedElement
  )

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(miraj/defweb-properties HowdyProps
  "BProps docstring here"
  (^Boolean bool-b true :read-only)
  (^Number ^{:doc "number y docstring"} y 99)
  (^String greeting "Howdy, Miraj!" (fn [new old] (.log js/console (str "MSG CHANGE: " new)))))



(miraj/defweb-codom howdy-codom
  "howdy-codom docstring"
  [greeting]
  ;; (:require [miraj.polymer.paper :as paper :refer [button]])
  ;; (:style  '(styles.shared shared-styles))
  (:codom
   (h/style ":host {display: block;} span {background-color:teal;}")
   (h/h1 "Howdy!")
   (h/h2 ::special.page-title :greeting)
   (h/span ::.paper-font-body2 "Update text to change the greeting.")
   ;; Listens for "input" event and sets greeting to <input>.value
   (h/input {:class "paper-font-body2" :value :input->greeting})
   #_(paper/button "hi")))

;; FIXME: must provide a fn-name, to go along with the tag name.
(miraj/defcomponent [howdy greeting-howdy]
  "miraj.demo.greeting/howdy custom component"
  howdy-codom

  ;;Properties
  AProps
  HowdyProps

  ;;Protocol Implementations
  protocols/This
  ;; private
  (_foo [] (.log js/console "FOO"))
  ;; public
  (bar [] (.log js/console "BAR"))

  protocols/Lifecycle
  (created [] (.log js/console "CREATED"))
  (attached [] (.log js/console "ATTACHED"))

  ;; FIXEM: protocols, not events. an event suite is a protocol

  events/Gesture
  ;; (with-element :special (tap [e] (.log js/console "you tapped the h1 element")))
  (down [x] (do (.log js/console "DOWN")))

  events/Mouse
  (click [x] (.log js/console "CLICK"))
  (dblclick [x] (.log js/console "DBLCLICK"))
  (mouseover [x] (.log js/console "MOUSEOVER"))

  ;; FIXME:  extensions, not behaviors or mixins
  ;; mixins
;  behavior/PaperButton
;  paper.extension/button
  ;; Polymer.Behaviors.PaperCheckedElement
    )

;; (binding [*compile-path* "tmp"]
;;   ;; (wc/compile-webcomponent-nss :html #{(ns-name *ns*)} true true)
;;   (wc/compile-webcomponents-cljs #{(-> *ns* ns-name) 'miraj.demo.adieu} true true))

;; (binding [*compile-path* "tmp"]
;;   (wc/link-component-libs #{'miraj.demo} true))
