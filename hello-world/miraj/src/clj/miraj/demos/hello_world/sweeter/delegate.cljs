(ns miraj.demos.hello-world.sweeter.delegate)

(enable-console-print!)

(println "Hello world from miraj.demos.hello-world.sweeter.delegate!")

(let [btn (.getElementById js/document "sweeterbtn")]
  (.addEventListener btn "click"
                     (fn [_] (js/alert "Hello World (from miraj.core)!")
                       (println "That tickles"))))
