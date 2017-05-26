(ns acme.sweetness.sweet)

(enable-console-print!)

(println "LOADING acme.sweetness.sweet")

(defn created [] (println "CREATED sweet"))

(defn mouseover [this e]
  (println "Mouseover count: " (aget this "mouseover-count"))
  (aset this "mouseover-count" (inc (aget this "mouseover-count"))))

(defn observe-mouseover-count
  [new old] (println "Mouseover count observation:" old new))

(defn click [this e]
  (let [cc (aget this "click-count")]
    (println "click" cc)
    ;; toggleClass is a Polymer built-in
    ;; https://www.polymer-project.org/1.0/docs/devguide/instance-methods#class-and-attribute-manipulation
    (.toggleClass this "foobar" (even? cc))
    (aset this "click-count" (inc (aget this "click-count")))))

(defn observe-click-count
  [new old] (println "Click count observation:" old new))

(defn say
  ([] "HELLO from sweetness-sweet!")
  ([new old] (println "OUT with the sweet old: " old "\nIn with the sweet new: " new)))

(defn observe-greeting
  [new old] (println "OBSERVING:" old new))

(defn greeting-flavor-observer [greeting flavor]
                   (println "cljs greeting-flavor-observer: " greeting ", " flavor)
                   (this-as this (set! (.-message this) (str greeting ", " flavor " ()"))))


(def msg "HOWDY")
(defn greet [] "HELLO msg")

