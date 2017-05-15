(ns miraj.demos.hello-world.miraj.sweeter.core)

(enable-console-print!)

(println "Hello world from miraj.demos.hello-world.sweeter.core!")

(let [btn (.getElementById js/document "sweeterbtn")]
  (.addEventListener btn "click"
                     (fn [_] (enable-console-print!)
                       (js/alert "Hello, Sweeter Miraj World!")
                       (println "That tickles, sweeter"))))
