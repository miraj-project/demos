(ns acme.sweetness.sweeter)

(enable-console-print!)

(println "LOADING acme.sweetness.sweeter")

(defn created [] (println "CREATED sweeter"))

(defn mouseover [this e]
  (println "Sweeter mouseover count: " (aget this "mouseover-count"))
  (aset this "mouseover-count" (inc (aget this "mouseover-count"))))

(defn observe-mouseover-count
  [new old] (println "Sweeter mouseover count observation:" old new))

(defn click [this e]
  (let [cc (inc (aget this "click-count"))]
    (println "click" cc)
    ;; toggleClass is a Polymer built-in
    ;; https://www.polymer-project.org/1.0/docs/devguide/instance-methods#class-and-attribute-manipulation
    (.toggleClass this "foobar" (even? cc))
    (aset this "message" (str "CLICKS: " cc))
    (aset this "click-count" cc)))

(defn observe-click-count
  [new old] (println "Sweeter click count observation:" old new))

(defn say
  ([] "HELLO msg from acme-sweeter!"))

(defn observe-greeting
  [new old] (println "OBSERVING:" old new))

(defn greeting-flavor-observer [this greeting flavor]
                   (println "cljs sweeter greeting-flavor-observer: " greeting ", " flavor)
                   (set! (.-message this) (str greeting ", " flavor " ()")))


(def msg "HOWDY")
(defn greet [] "HELLO msg")

