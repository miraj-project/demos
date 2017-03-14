(ns miraj.demo.hello-world-polymer
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [miraj.core :as miraj]
            [miraj.html :as h]
            ;; [miraj.polymer.Events :as events]
            [miraj.polymer.protocol :as protocols]
            [miraj.html.protocol]

            ;; for testing only:
            [miraj.compiler :as wc]
            [miraj.co-dom :as codom]
            :reload))

;;(println "loading org.example.greetings.arrival")

;(ns-unalias *ns* 'paper)

(miraj/defproperties HelloProps
  "HelloProps docstring here"
  (^Boolean bool-b true :read-only)
  (^Number ^{:doc "number y docstring"} y 99)
  ;; the greeting property will be referenced from the codom
  (^String greeting "Hello from Miraj!" (fn [new old] (.log js/console (str "MSG CHANGE: " new)))))

(miraj/defweb-codom hello-codom
  "hello-codom docstring"
  [greeting]
  ;; (:require [miraj.polymer.paper :as paper :refer [button]])
  ;; (:style  '(styles.shared shared-styles))
  (:codom
   (h/style ":host {display: block; width: 50%; border:thin black solid;} span {background-color:#E0F2F1;}")
   (h/style ":host h1, :host h2 {text-align:center;}")
   (h/h1 "Hello!")
   (h/h2 ::special.page-title :greeting) ;; :greeting == {{greeting}}, 2-way binding
   (h/span ::.paper-font-body2 "Update text to change the greeting.")
   ;; Listens for "input" event and sets {{greeting}} to <input>.value
   (h/input {:class "paper-font-body2" :value :input->greeting})))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(do
  (require '[miraj.core :as miraj] :reload)
  (require '[miraj.polymer.protocol :as protocols] :reload)

(miraj/defweb-component [hello hello-world]
  "custom component: miraj.demo.hello-world/hello => <hello-world>"
  hello-codom

  ;;Properties
  HelloProps

  ;;Protocol Implementations
  ;; protocols/This
  ;; ;; private
  ;; (_foo [] (.log js/console "FOO"))
  ;; ;; public
  ;; (bar [] (.log js/console "BAR"))

  protocols/Lifecycle
  (created [] (.log js/console "CREATED"))
  (attached [] (.log js/console "ATTACHED"))

  ;; FIXEM: protocols, not events. an event suite is a protocol

  protocols/Gesture
  (with-element :special (tap [e] (.log js/console "you tapped the h1 element")))
  (down [x] (do (.log js/console "DOWN")))

  miraj.html.protocol/Mouse
  (click [x] (.log js/console "CLICK"))
  (dblclick [x] (.log js/console "DBLCLICK"))
  (mouseover [x] (.log js/console "MOUSEOVER"))
  ;; (foo [])

;;  (:extensions [miraj.polymer.paper.extensions button checked-element inky-focus])
  )
)

;;(-> hello var meta :miraj/miraj :miraj/prototype)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(miraj/defproperties GoodbyeProps
  "GoodbyeProps docstring here"
  (^Boolean bool-b true :read-only)
  (^Number ^{:doc "number y docstring"} y 99)
  (^String greeting "Goodbye from Miraj!" (fn [new old] (.log js/console (str "MSG CHANGE: " new)))))

(miraj/defweb-codom goodbye-codom
  "goodbye-codom docstring"
  [greeting]
  (:codom
   (h/style ":host {display: block; width: 50%; border:thin red solid;} span {background-color:#DCEDC8;}")
   (h/style ":host h1, :host h2 {text-align:center;}")
   (h/style "#foo {display:block; margin-left: auto; margin-right: auto;}")

   (h/h1 "Goodbye!")
   (h/h2 ::special.page-title :greeting)
   (h/input {:id "foo" :class "paper-font-body2" :value :input->greeting})))

(miraj/defweb-component [goodbye goodbye-world]
  "custom component: miraj.demo.hello-world/goodbye => <goodbye-world>"
  goodbye-codom

  ;;Properties
  GoodbyeProps

  ;;Protocol Implementations
  ;; protocols/This
  ;; ;; private
  ;; (_foo [] (.log js/console "FOO"))
  ;; ;; public
  ;; (bar [] (.log js/console "BAR"))

  protocols/Lifecycle
  (created [] (.log js/console "CREATED"))
  (attached [] (.log js/console "ATTACHED"))

  protocols/Gesture
  (with-element :special (tap [e] (.log js/console "you tapped the h1 element")))
  (down [x] (do (.log js/console "DOWN")))

  miraj.html.protocol/Mouse
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
