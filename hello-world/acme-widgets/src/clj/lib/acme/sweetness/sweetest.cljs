(ns acme.sweetness.sweetest)

(enable-console-print!)

(println "LOADING acme.sweetness.sweetest")

(defn created [] (println "CREATED"))

(defn mouseover [x] (println "Mouseover"))

(defn click [this e]
  (println "MouseClick")
  (let [div (.createElement js/document "div")
        r (.-root this)
        root (.dom js/Polymer r)]
    (set! (.-innerHTML div) "HI from a new div!")
    (.appendChild root div)))

(defn say
  ([] "HELLO from Hello-Card!")
  ([old new] (println "OUT with the sweetest old: " old "\nIn with the sweetest new: " new)))

(defn observe-greeting [old new] (println (str "OBSERVING sweetest: old: " old ", new:" new)))

(defn complex-observer [greeting addressee]
                   (.log js/console "Compound sweetest observation: " greeting ", " addressee)
                   (this-as this (set! (.-foo this) (str greeting ", " addressee " ()"))))


(def msg "HOWDY")
(defn greet [] "HELLO msg")

