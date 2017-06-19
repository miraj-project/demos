(ns acme.sweetness.sweet.core
  (:require [acme.sweetness.sweet :as sweet]))





(try
  (js/Polymer
    (cljs.core/clj->js
      {:properties
       {:greeting
        {:value "Hello", :type js/String, :observer :G__40196},
        :flavor {:value "SWEET", :type js/String},
        :message {:value (sweet/say)},
        :click-count {:value 0, :type js/Number, :observer :G__40197},
        :mouseover-count
        {:value 0, :type js/Number, :observer :G__40198}},
       :G__40198
       (fn [new old] (sweet/observe-mouseover-count new old)),
       :foo-click (fn [evt] (this-as this (sweet/foo-click this evt))),
       :ready (fn [] (.log js/console "READY sweet")),
       :_click_Miraj (fn [e] (this-as this (sweet/click this e))),
       :observers ["greeting-flavor-observer(greeting, flavor)"],
       :is :acme-sweet,
       :name {:last "Smith", :first "John"},
       :created (fn [] (sweet/created)),
       :greeting-flavor-observer
       (fn [greeting flavor]
         (this-as
           this
           (sweet/greeting-flavor-observer this greeting flavor))),
       :G__40197 (fn [new old] (sweet/observe-click-count new old)),
       :_mouseover_Miraj
       (fn [e] (this-as this (sweet/mouseover this e))),
       :hostAttributes
       {:stringattr1 "attr1",
        :boolean-attr2 true,
        :greeting "Hello",
        :tabindex 0},
       :listeners {:click :_click_Miraj, :mouseover :_mouseover_Miraj},
       :G__40196 (fn [new old] (sweet/observe-greeting new old))}))
  (catch
    js/Error
    e__7958__auto__
    (clojure.core/println
      "\"Caught exception on component registration:\""
      e__7958__auto__)))