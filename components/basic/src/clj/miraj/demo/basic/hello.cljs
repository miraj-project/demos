(ns miraj.demo.basic.hello)

(enable-console-print!)

(println "LOADING miraj.demo.basic.hello")

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
  ([] "HELLO from HELLO")
  ([old new] (println "OUT with the old: " old "\nIn with the new: " new)))

(defn observe-greeting [old new] (println "OBSERVING:" old new))

(defn complex-observer [greeting addressee]
                   (.log js/console "Compound observation: " greeting ", " addressee)
                   (this-as this (set! (.-foo this) (str greeting ", " addressee " ()"))))


(def msg "HOWDY")
(defn greet [] "HELLO msg")

