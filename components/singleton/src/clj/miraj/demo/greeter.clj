(ns miraj.demo.greeter
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
(miraj/defcomponent [hello hello-world]
  "custom component: miraj.demo.greeter/hello => <hello-world>"
  hello-codom

  ;;Properties
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
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(miraj/defweb-properties GoodbyeProps
  "GoodbyeProps docstring here"
  (^Boolean bool-b true :read-only)
  (^Number ^{:doc "number y docstring"} y 99)
  (^String greeting "Goodbye, Miraj!" (fn [new old] (.log js/console (str "MSG CHANGE: " new)))))

(miraj/defweb-codom goodbye-codom
  "goodbye-codom docstring"
  [greeting]
  ;; (:require [miraj.polymer.paper :as paper :refer [button]])
  ;; (:style  '(styles.shared shared-styles))
  (:codom
   (h/style ":host {display: block;} span {background-color:teal;}")
   (h/h1 "Goodbye!")
   (h/h2 ::special.page-title :greeting)
   (h/span ::.paper-font-body2 "Update text to change the greeting.")
   ;; Listens for "input" event and sets greeting to <input>.value
   (h/input {:class "paper-font-body2" :value :input->greeting})
   #_(paper/button "hi")))



(miraj/defcomponent [goodbye goodbye-world]
  "custom component: miraj.demo.greeter/goodbye => <goodbye-world>"
  goodbye-codom

  ;;Properties
  GoodbyeProps

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

;(-> goodbye var meta :miraj/miraj :miraj/ns)

;; (binding [*compile-path* "tmp"]
;;   ;; (wc/compile-webcomponent-nss :html #{(ns-name *ns*)} true true)
;;   (wc/compile-webcomponents-cljs #{(-> *ns* ns-name) 'miraj.demo.adieu} true true))

;; (binding [*compile-path* "tmp"]
;;   (wc/link-component-libs #{'miraj.demo} true))
