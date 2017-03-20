(ns miraj.demos.hello-world.polymer.sweeter
  (:require [miraj.core :as miraj :refer [compile-page-nss defpage normalize]]
            [miraj.html :refer :all :exclude [button]]
            [miraj.co-dom :as codom :refer [pprint serialize]]
            [miraj.polymer.paper :as paper :refer [button]]
            :reload))

(defpage index
  "demo"
  (:require [miraj.polymer.paper :as paper :refer [card]])
  (:import  [[html.hello-world polymer]])

  (:body
   (h1 "Hello World!")
   (h2 "(from miraj.polymer.paper, sweeter)")
   (div :#cards
        (paper/card {:heading "Hello, you ol' Card!"}
                    (div :.card-content "Some content")
                    (div :.card-actions
                         (paper/button :!raised {:onclick "handle_click('sweeter paper');"}
                                       "Some action"))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(binding [miraj/*debug* true
          miraj/*verbose* true
          miraj/*keep* true
          miraj/*pprint* true
          *compile-path* "target"]
  (miraj/compile ;;:namespaces #{*ns*}
                 ;;:pages ['index]
                 :page (symbol (str (ns-name *ns*)) "index")
                 ))


  ;; (miraj/compile-page-nss #{(-> *ns* ns-name)} true true))

  ;; (wc/compile {:nss #{(-> *ns* ns-name)}
  ;;              :pages [index]
  ;;              :optimization 2 :debug true})


