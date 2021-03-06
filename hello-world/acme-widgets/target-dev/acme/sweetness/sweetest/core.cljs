(ns acme.sweetness.sweetest.core
  (:require [acme.sweetness.sweetest :as sweetest]))





(try
  (js/Polymer
    (cljs.core/clj->js
      {:properties
       {:greeting {:value "hello", :observer :G__40652},
        :addressee {:value "SWEETEST", :observer :G__40653},
        :message {:value (sweetest/say)},
        :foo {:type js/String}},
       :ready (fn [] (.log js/console "READY")),
       :_click_Miraj (fn [e] (this-as this (sweetest/click this e))),
       :observers ["compound-obs(greeting, addressee)"],
       :is :acme-sweetest,
       :name {:last "Smith", :first "John"},
       :_tap_Miraj (fn [x] (.log js/console "TAP")),
       :created (fn [] (println "CREATED")),
       :this-fn (fn [this]),
       :G__40652 (fn [new old] (sweetest/observe-greeting new old)),
       :_blur_Miraj (fn [x] (.log js/console "BLUR")),
       :_focusin_Miraj (fn [x] (.log js/console "FOCUSIN")),
       :_mouseover_Miraj (fn [x] (sweetest/mouseover x)),
       :G__40653 #(.log js/console (str "Addressee CHG OBSERVED " %)),
       :attached (fn [] (.log js/console "ATTACHED")),
       :hostAttributes
       {:string-attr1 "attr1",
        :boolean-attr2 true,
        :foo "Hello",
        :tabindex 0},
       :listeners
       {:tap :_tap_Miraj,
        :blur :_blur_Miraj,
        :focusin :_focusin_Miraj,
        :focusout :_focusout_Miraj,
        :click :_click_Miraj,
        :mouseover :_mouseover_Miraj},
       :_focusout_Miraj (fn [x] (.log js/console "FOCUSOUT")),
       :compound-obs
       (fn [greeting addressee]
         (.log
           js/console
           "Compound observation: "
           greeting
           ", "
           addressee)
         (this-as
           this
           (set! (.-foo this) (str greeting ", " addressee " ()"))))}))
  (catch
    js/Error
    e__7958__auto__
    (clojure.core/println
      "\"Caught exception on component registration:\""
      e__7958__auto__)))