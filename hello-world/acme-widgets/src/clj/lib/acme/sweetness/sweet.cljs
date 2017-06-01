(ns acme.sweetness.sweet)

(enable-console-print!)

(println "LOADING acme.sweetness.sweet")

(defn created [] (println "CREATED sweet"))

(defn mouseover [this e]
  (println "Sweet mouseover count: " (aget this "mouseover-count"))
  (aset this "mouseover-count" (inc (aget this "mouseover-count"))))

(defn observe-mouseover-count
  [new old] (println "Sweet mouseover count observation:" old new))

(defn foo-click [this e]
  (println "foo-click"))

(defn click [this e]
  (let [cc (inc (aget this "click-count"))]
    (println "sweet click" cc)
    ;; toggleClass is a Polymer built-in
    ;; https://www.polymer-project.org/1.0/docs/devguide/instance-methods#class-and-attribute-manipulation
    (.toggleClass this "foobar" (even? cc))
    (aset this "click-count" cc)
    (aset this "message" (str "Sweet clicks: " cc))))

(defn observe-click-count
  [new old] (println "Sweet click count observation:" old new))

(defn say
  ([] "HELLO msg from acme-sweet!"))

(defn observe-greeting
  [new old] (println "Sweet OBSERVING:" old new))

(defn greeting-flavor-observer [greeting flavor]
                   (println "sweet cljs greeting-flavor-observer: " greeting ", " flavor)
                   (this-as this (set! (.-message this) (str greeting ", " flavor " ()"))))

;; (def msg "HOWDY")
;; (defn greet [] "HELLO sweet msg")

