(ns acme.bitterness.bitter.core
  (:require [acme.bitterness.bitter :as bitter]))





(try
  (js/Polymer
    (cljs.core/clj->js
      {:properties
       {:greeting {:value "hello", :observer :G__40878},
        :addressee {:value "BUTTON", :observer :G__40879},
        :message {:value (bitter/say)},
        :foo {:type js/String}},
       :ready (fn [] (.log js/console "READY")),
       :_click_Miraj (fn [e] (this-as this (bitter/click this e))),
       :G__40879 #(.log js/console (str "Addressee CHG OBSERVED " %)),
       :observers ["compound-obs(greeting, addressee)"],
       :is :acme-bitter,
       :name {:last "Smith", :first "John"},
       :created (fn [] (println "CREATED")),
       :this-fn (fn [this]),
       :G__40878 (fn [new old] (bitter/observe-greeting new old)),
       :_private-method (fn [baz]),
       :public-method (fn [foo bar] 99),
       :_blur_Miraj (fn [x] (.log js/console "BLUR")),
       :_focusin_Miraj (fn [x] (.log js/console "FOCUSIN")),
       :_mouseover_Miraj (fn [x] (bitter/mouseover x)),
       :_private-method2 #(.log js/console (str "Hash fn: " %)),
       :attached (fn [] (.log js/console "ATTACHED")),
       :hostAttributes
       {:string-attr1 "attr1",
        :boolean-attr2 true,
        :foo "Hello",
        :tabindex 0},
       :listeners
       {:blur :_blur_Miraj,
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