(ns miraj.demos.hello-world.polymer.sweeter
  (:refer-clojure :exclude [map meta time])
  (:require [miraj.core :as miraj :refer [compile-page-nss defpage normalize]]
            [miraj.html :refer :all :exclude [button]]
            [miraj.co-dom :as codom :refer [pprint serialize]]
            [miraj.polymer.paper :as paper :refer [button]]
            :reload))

;; sweeter: use import instead of <script>
(defpage
  "demo"
  (:require [miraj.polymer.paper :as paper :refer [card]])
  (:js ["/js/sweeter.js"])

  (:body
   (h1 "Hello, Sweeter Polymer World!")

   (div :#cards
        (paper/card {:heading "Hello, you ol' Card!"}
                    (div :.card-content "Some content")
                    (div :.card-actions
                         (paper/button :!raised {:onclick "handle_click('sweeter paper');"}
                                       "Some action"))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#_(binding [miraj/*debug* true
          miraj/*verbose* true
          miraj/*keep* true
          miraj/*pprint* true
          *compile-path* "target"]
  (miraj/mcc ;;:namespaces #{*ns*}
                 ;;:pages ['index]
                 :page (symbol (str (ns-name *ns*)) "index")
                 ))


  ;; (miraj/compile-page-nss #{(-> *ns* ns-name)} true true))

  ;; (wc/compile {:nss #{(-> *ns* ns-name)}
  ;;              :pages [index]
  ;;              :optimization 2 :debug true})


