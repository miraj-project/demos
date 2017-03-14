(ns miraj.demo.hello-world-html
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.polymer.Events :as events]
            [miraj.polymer.Protocols :as protocols]

            ;; for testing only:
            [miraj.compiler :as wc]
            [miraj.co-dom :as codom]
            :reload))

(miraj/defproperties HelloProps
  "HelloProps docstring here"
  ;; the greeting property will be referenced from the codom
  (^String greeting "Hello from Miraj!"))

(miraj/defweb-codom hello-codom
  "hello-codom docstring"
  [greeting]
  (:codom
   (h/style ":host {display: block; width: 50%; border:thin black solid;} span {background-color:#E0F2F1;}")
   (h/style ":host h1, :host h2 {text-align:center;}")
   (h/h1 "Hello!")
   (h/h2 ::special.page-title :greeting) ;; :greeting == {{greeting}}, 2-way binding
   (h/span ::.paper-font-body2 "Update text to change the greeting.")
   ;; Listens for "input" event and sets {{greeting}} to <input>.value
   (h/input {:class "paper-font-body2" :value :input->greeting})))

;hello-codom

(do
  (clojure.core/require '[miraj.core :as miraj] :reload)

  (miraj/defweb-component [hello hello-world]
    "custom component: miraj.demo.hello-world/hello => <hello-world>"
    hello-codom
    ;;Properties
    HelloProps
    ;; see hello-world-polymer for an example using event handlers etc.
    )

)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(miraj/defproperties GoodbyeProps
  "GoodbyeProps docstring here"
  (^String greeting "Goodbye from Miraj!"))

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

#_(miraj/defweb-component [goodbye goodbye-world]
  "custom component: miraj.demo.hello-world/goodbye => <goodbye-world>"

  ;; first: props map
  {:miraj/markup goodbye-world          ;; required, to get html tag
   :greeting {:default "Goodbye"
              :observer (fn [chg] ...)}
   :addressee {:default "World"
               :observer (fn [chg] ...)}
   :message {:computed (foo greeting addressee)}}

  goodbye-codom

  GoodbyeProps

  ;; compound observers take properties as params
  (foo [:greeting :addressee] (str greeting ", " addressee))

  )
