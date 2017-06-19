(ns acme.sweetness.sweeter.core
  (:require [acme.sweetness.sweeter :as sweeter]))





(try
  (js/Polymer
    (cljs.core/clj->js
      {:properties
       {:greeting
        {:value "Hello", :type js/String, :observer :G__40620},
        :flavor {:value "SWEETER", :type js/String},
        :message {:value (sweeter/say)},
        :click-count {:value 0, :type js/Number, :observer :G__40621},
        :mouseover-count
        {:value 0, :type js/Number, :observer :G__40622}},
       :G__40621 (fn [new old] (sweeter/observe-click-count new old)),
       :_click_Miraj (fn [e] (this-as this (sweeter/click this e))),
       :observers ["greeting-flavor-observer(greeting, flavor)"],
       :is :acme-sweeter,
       :name {:last "Smith", :first "John"},
       :G__40620 (fn [new old] (sweeter/observe-greeting new old)),
       :greeting-flavor-observer
       (fn [greeting flavor]
         (this-as
           this
           (sweeter/greeting-flavor-observer this greeting flavor))),
       :_mouseover_Miraj
       (fn [e] (this-as this (sweeter/mouseover this e))),
       :hostAttributes
       {:stringattr1 "attr1",
        :boolean-attr2 true,
        :greeting "Hello",
        :tabindex 0},
       :G__40622
       (fn [new old] (sweeter/observe-mouseover-count new old)),
       :listeners
       {:click :_click_Miraj, :mouseover :_mouseover_Miraj}}))
  (catch
    js/Error
    e__7958__auto__
    (clojure.core/println
      "\"Caught exception on component registration:\""
      e__7958__auto__)))