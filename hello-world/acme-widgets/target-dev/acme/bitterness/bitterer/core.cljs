(ns acme.bitterness.bitterer.core
  (:require [acme.bitterness.bitterer :as bitterer]))





(try
  (js/Polymer
    (cljs.core/clj->js
      {:is :acme-bitterer,
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