(ns miraj.demos.hello-world.polymer.sweetest.core)

(enable-console-print!)

(println "Hello world from miraj.demos.hello-world.sweetest.core!")

(let [btn (.getElementById js/document "sweetestbtn")]
  (.addEventListener btn "click"
                     (fn [_] (enable-console-print!)
                       (js/alert "Hello, Sweetest Polymer World!")
                       (println "That tickles, sweetest"))))
