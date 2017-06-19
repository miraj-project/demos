(ns acme.bitterness.bitterest.core
  (:require [acme.bitterness.bitterest :as bitterest]))





(try
  (js/Polymer
    (cljs.core/clj->js
      {:is :acme-bitterest,
       :properties {},
       :hostAttributes nil,
       :observers [],
       :listeners nil}))
  (catch
    js/Error
    e__7958__auto__
    (clojure.core/println
      "\"Caught exception on component registration:\""
      e__7958__auto__)))