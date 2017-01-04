(ns gae.servlets)

(def config
  {:servlets [{:ns 'hello.miraj.echo
               :name "echo-servlet"
               :display {:name "Awesome Echo Servlet"}
               :desc {:text "blah blah"}
               :url "/echo/*"
               :params [{:name "greeting" :val "Hello"}]
               :load-on-startup {:order 3}}

              {:ns 'hello.miraj.greeting
               :name "greeting-servlet"
               :url "/greeting/*"
               :params [{:name "op" :val "+"}
                        {:name "arg1" :val 3}
                        {:name "arg2" :val 2}]}

              {:ns 'hello.miraj.math
               :name "math-servlet"
               :url "/math/*"
               :params [{:name "op" :val "+"}
                        {:name "arg1" :val 3}
                        {:name "arg2" :val 2}]}]})
