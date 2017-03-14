(ns components.miraj)

(alter-meta! *ns*
             (fn [m] (assoc m
                            :co-ns true
                            :resource-type :polymer
                            :resource-pfx "miraj_components")))

(def pfx "miraj_components")

(def my-greeting)

(def components.miraj
  ;; entries:  [:fn-tag  [elt-kw elt-uri & docstring]]
  {:greeting [:my-greeting "my-greeting/my-greeting.html"]})
