(ns acme.sweetness
  (:require ;;[clojure.java.io :as io]
            ;;[clojure.pprint :as pp]
            [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.html.protocols :as hp]
            [miraj.polymer :as p :refer [slot]]
            [miraj.polymer.protocols :as poly]
            ;; [miraj.polymer.paper :as paper]

            ;; for testing only:
            ;; [miraj.co-dom :as codom]
            #_:reload))

;; (println "loading acme.sweetness")

;; sweetness: local css (embedded/external), polymer bindings,
;; property observers, event
;; protocols (including v0 lifecycle callbacks), behaviors

;; NB: implementation files go in acme/sweetness/; the interface fn is
;; acme.widgets/sweet, defined by deflibrary in acme.clj.

;(-> sweet var meta :miraj/miraj)


(miraj/defcomponent sweet :html acme-sweet
  "sweet"
  (:require [miraj.polymer.paper :as paper :refer [button card]]
            [miraj.polymer.iron :as iron :refer [icon]])

  ;; embedded inline css
  (:css "#foo {color: blue;}") ;; this will be moved into the <template> element
  (:codom
   ;; inlining css in html style elements works too, inside :codom
   (h/style ".sweet {text-align: center;margin:0 auto;padding:10px;width:50%;border:thin blue dotted;}
   .card {text-align: center;margin:0 auto;padding:10px;width:50%;}")

   (h/div :.sweet
          (h/div {:stringattr1 (p/bind-attr!! :message)} (p/bind! :message))
          (paper/card :.card {:heading (p/bind!! :flavor)}
                      (h/div :#foo
                             ;; element-local event handling
                             (h/span {:on-click :foo-click ;; :foo-click = instance method fld, below
                                      :attr1 99}
                                     "Click count: " (p/bind!! :click-count)))
                      (h/div :.bar  (h/span "Mouseover count: " (p/bind!! :mouseover-count)))
                      (h/div
                       (h/span (p/bind!! :greeting) ", " (p/bind! :flavor))
                       (h/span
                        (p/slot)
                        )))))

  ;; prototype map - this will generate the js prototype code for the component
  {:polymer/properties {;; :greeting is a property, also set as a static attribute below
                        :greeting ^String{:value "Hello"
                                          :type String
                                          :observer (fn [new old] (sweet/observe-greeting new old))}
                        :flavor {:value "SWEET"
                                 :type String}
                        :message {:value (sweet/say)}
                        :click-count {:value 0 :type Number
                                      :observer (fn [new old]
                                                  (sweet/observe-click-count new old))}
                        :mouseover-count {:value 0 :type Number
                                          :observer (fn [new old]
                                                      (sweet/observe-mouseover-count new old))}
                        }

   ;; static html attributes on host (Polymer hostAttributes property)
   ;; see https://www.polymer-project.org/1.0/docs/devguide/registering-elements#host-attributes
   ;; these will cause html attrs to be set at create time
   ;; inspect the rendered host element to see them
   :polymer/static {:stringattr1 "attr1"
                    :boolean-attr2 true
                    :greeting "Hello"
                    :tabindex 0}

   ;; complex observers take (keyword) properties as params
   ;; this will be fired whenever either property changes
   ;; https://www.polymer-project.org/1.0/docs/devguide/observers#complex-observers
   :greeting-flavor-observer (fn [:greeting :flavor]
                               ;; pass the args as syms
                               (sweet/greeting-flavor-observer greeting flavor))

   ;; local properties - we can put them in the prototype, or in a cljs namespace
   ;; for polymer binding, they must be in the prototype
   :name {:last "Smith"
          :first "John"}

   ;; "instance" methods (https://www.polymer-project.org/1.0/docs/devguide/instance-methods)
   ;; with javascript, instance methods go in the component's prototype
   ;; with clojurescript, we don't need this - just use functions in the delegate namespace
   ;; BUT: use instance method keywords for element-local event handlers
   :foo-click (fn [evt] (this-as this (sweet/foo-click this evt)))
   }

  ;; lifecycle "callbacks":
  ;; https://www.polymer-project.org/1.0/docs/devguide/registering-elements#lifecycle-callbacks
  ;; miraj protocolizes them:
  miraj.polymer.protocols/Lifecycle
  (created [] (sweet/created))
  (ready [] (.log js/console "READY sweet"))

  ;; event "listeners": https://www.polymer-project.org/1.0/docs/devguide/events
  ;; miraj protocolizes them:
  miraj.html.protocols/Mouse
  (click [e] (this-as this (sweet/click this e)))
  (mouseover [e] (this-as this (sweet/mouseover this e)))
  )

;; sweenteners: use external dependencies
(miraj/defcomponent sweeter :html acme-sweeter
  "sweeter"

  (:require [miraj.polymer.paper :as paper :refer [button card]]
            [miraj.polymer.iron :as iron :refer [icon]])

  ;; external dependency, embedded
  (:css ["/css/sweeter.css"]) ;; will be moved to <template>
  ;; also: ./sweeter.css will be auto-detected and inserted

  (:codom
   (h/div :.sweeter
          (h/div {:stringattr1 (p/bind-attr!! :message)} (p/bind! :message))
          (paper/card {:heading (p/bind!! :flavor)}
                      (h/div :#foo (h/span "Click count: " (p/bind!! :click-count)))
                      (h/div :.bar  (h/span "Mouseover count: " (p/bind!! :mouseover-count)))
                      (h/div
                       (h/span (p/bind!! :greeting) ", " (p/bind! :flavor))
                       (h/span
                        (p/slot)
                        )))))

  ;; prototype map - this will generate the js prototype code for the component
  {:polymer/properties {;; :greeting is a property, also set as a static attribute below
                        :greeting ^String{:value "Hello"
                                          :type String
                                          :observer (fn [new old] (sweeter/observe-greeting new old))}
                        :flavor {:value "SWEETER"
                                 :type String}
                        :message {:value (sweeter/say)}
                        :click-count {:value 0 :type Number
                                      :observer (fn [new old]
                                                  (sweeter/observe-click-count new old))}
                        :mouseover-count {:value 0 :type Number
                                          :observer (fn [new old]
                                                      (sweeter/observe-mouseover-count new old))}
                        }

   :polymer/static {:stringattr1 "attr1"
                    :boolean-attr2 true
                    :greeting "Hello"
                    :tabindex 0}

   :greeting-flavor-observer (fn [:greeting :flavor]
                               ;; pass the args as syms
                               (sweeter/greeting-flavor-observer greeting flavor))

   :name {:last "Smith"
          :first "John"}
   }

  ;; event "listeners", protocolized:
  miraj.html.protocols/Mouse
  (click [e] (this-as this (sweeter/click this e)))
  (mouseover [e] (this-as this (sweeter/mouseover this e)))
  )

;; sweetest - manage all non-lexical dependencies externally (via <component>.edn file)[
(miraj/defcomponent sweetest :html acme-sweetest
  "sweetest"
  ;;(:require [miraj.polymer.paper :as paper])

  ;; external deps:  ./sweetest.edn will be auto-detected
  ;; css:  ./sweetest.css will be auto-detected and injected

  (:codom
   (h/h1 "Sweetest!")

   (h/div (h/h4 "Another Fine, Sweet Message!"))
   (h/div (p/bind! :message)) ;; 1-way binding, [[message]]
   (h/div :#special.page-title
          (h/span (p/bind!! :greeting)) ;; 2-way binding, {{greeting}}
          (h/span ", ")
          (h/span (p/bind! :addressee)))
   (h/span "UPDATE text to change the greeting.")
   ;; input->greeting means input event sends value to greeting
   (h/input {:class "paper-font-body2" :value (p/bind!! :input->greeting)})
   (h/input {:class "paper-font-body2" :value (p/bind!! :input->addressee)}))

  ;; polymer prototype
  {:polymer/properties {:greeting ^String{:value "hello"
                                          ;; :type String
                                          ;; FIXME: alias for helper ns
                                          :observer (fn [new old] (sweetest/observe-greeting new old))
                                          ;;:observer (clj->js 'sweetest/observe-greeting)
                                          }
                        :addressee {:value "SWEETEST"
                                    ;; :type String
                                    :observer #(.log js/console (str "Addressee CHG OBSERVED " %))
                                    }
                        ;; :message {:value (fn [:greeting :addressee] (str greeting ", " addressee))}
                        :message {:value (sweetest/say)}

                        :foo {:type String}
                        }

   ;; see https://www.polymer-project.org/1.0/docs/devguide/registering-elements#host-attributes
   :polymer/static {:string-attr1 "attr1"
                    :boolean-attr2 true
                    :foo "Hello"
                    :tabindex 0}

   ;; complex observers take properties as params
   :compound-obs (fn [:greeting :addressee]
                   (.log js/console "Compound observation: " greeting ", " addressee)
                   (this-as this (set! (.-foo this) (str greeting ", " addressee " ()"))))

   ;; local properties - we can put them in the prototype, or in a cljs namespace
   ;; for polymer binding, they must be in the prototype
   :name {:last "Smith"
          :first "John"}

   ;; "instance" methods (https://www.polymer-project.org/1.0/docs/devguide/instance-methods)
   }

  ;; public methods

  ;; every component has an implicit This protocol:
  ;; FIXME: make This protocol methods, not listeners, just like for Lifecycle protocol
  This
  (this-fn [this]  )
  ;; (bar [this]  )

  poly/Lifecycle
  (created [] (println "CREATED"))
  (ready [] (.log js/console "READY"))
  (attached [] (.log js/console "ATTACHED"))
  ;;(bad-fn [])

  miraj.polymer.protocols/Gesture
  (tap [x] (.log js/console "TAP"))

  miraj.html.protocols/Focus
  (blur [x] (.log js/console "BLUR"))
  (focusin [x] (.log js/console "FOCUSIN"))
  (focusout [x] (.log js/console "FOCUSOUT"))

  miraj.html.protocols/Mouse
  (click [e] (this-as this
               (sweetest/click this e)))
  (mouseover [x] (sweetest/mouseover x))
  )

;; (println "loaded acme.sweetness")
