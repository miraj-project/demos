(ns components.greetings.hello-world)

(js/Polymer
 (clj->js
  {:properties
   {:president {:type js/Boolean, :value true, :readOnly true},
    :x
    {:type js/Number,
     :value 0,
     :notify true,
     :reflectToAttribute true,
     :observer :_x5014},
    :lname {:type js/String, :value "Lincoln", :observer :_lname5015},
    :bool-b {:type js/Boolean, :value true, :readOnly true},
    :y {:type js/Number, :value 99},
    :greeting
    {:type js/String,
     :value "Howdy Miraj!",
     :observer :_greeting5027}},
   :is :hello-world,
   :_lname5015
   (fn
    [new old]
    (.log js/console (str "Old pres: " old "; new: " new))),
   :_mouseover5043 (fn [x] (.log js/console "MOUSEOVER")),
   :bar (fn [] (.log js/console "BAR")),
   :_special.tap5039
   (fn [e] (.log js/console "you tapped the h1 element")),
   :created (fn [] (.log js/console "CREATED")),
   :behaviors [],
   :_click5041 (fn [x] (.log js/console "CLICK")),
   :_x5014 (fn [new old] (+ new old)),
   :_greeting5027
   (fn [new old] (.log js/console (str "MSG CHANGE: " new))),
   :_foo (fn [] (.log js/console "FOO")),
   :_dblclick5042 (fn [x] (.log js/console "DBLCLICK")),
   :attached (fn [] (.log js/console "ATTACHED")),
   :hostAttributes
   {:string-attribute "Value", :boolean-attribute true, :tabindex 0},
   :_down5040 (fn [x] (do (.log js/console "DOWN"))),
   :listeners
   {:special.tap :_special.tap5039,
    :down :_down5040,
    :click :_click5041,
    :dblclick :_dblclick5042,
    :mouseover :_mouseover5043}}))
