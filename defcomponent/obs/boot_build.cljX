(ns boot-build
  (:require [boot.core :as core]
            [boot.task.built-in :as task]))

;; (core/deftask build
;;   "Build my project."
;;   []
;;   (comp (task/pom) (task/jar) (task/install)))

(core/deftask options
  "Demonstrate the task options DSL."
  [a a-option VAL  kw    "The option."
   c counter       int   "The counter."
   e entry    VAL  sym   "An entrypoint symbol."
   f flag          bool  "Enable flag."
   o o-option VAL  str   "The other option."]
  *opts*)

;; (alter-meta! *ns*
;;              (fn [m] (assoc m
;;                             :co-ns true
;;                             :resource-type :polymer
;;                             :resource-pfx "miraj_components")))

;; (def pfx "miraj_components")

;; (def my-greeting)

;; (def components.miraj
;;   ;; entries:  [:fn-tag  [elt-kw elt-uri & docstring]]
;;   {:greeting [:my-greeting "my-greeting/my-greeting.html"]})
