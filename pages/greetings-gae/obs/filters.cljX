(ns gae.filters)

(def config
  {:filters [{:ns 'foo
              :name "foo"
              :display {:name "foo filter"}
              :urls [{:url "/foo/*"}]
              :desc {:text "foo filter"}}]})
