(ns psk
  (:require [miraj.core :as miraj :refer [deflibrary]]))
            ;; [miraj.html :as h]
            ;; [miraj.html.protocols :as hp]))

;; (println "loading psk")

(deflibrary gadgets
  "Miraj component library: psk.widgets"
  #:miraj{:require '[[psk.widgets :export :all]]
          :defelements true})
