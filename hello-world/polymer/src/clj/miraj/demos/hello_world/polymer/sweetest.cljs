(ns miraj.demos.hello-world.polymer.sweetest)

(enable-console-print!)

(println "Hello world from miraj.demos.hello-world.sweetest!")

(let [btn (.getElementById js/document "sweetestbtn")]
  (.addEventListener btn "click"
                     (fn [_] (enable-console-print!)
                       (js/alert "Hello, Sweetest Polymer World!")
                       (println "That tickles, sweetest"))))
