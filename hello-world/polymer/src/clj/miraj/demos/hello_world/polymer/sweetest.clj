(ns miraj.demos.hello-world.polymer.sweetest
  (:require [miraj.core :as miraj :refer [compile-page-nss defpage normalize]]
            [miraj.html :refer :all :exclude [button]]
            [miraj.co-dom :as codom :refer [pprint serialize]]
            [miraj.polymer.paper :as paper :refer [button]]
            :reload))

(defpage index
  "sweetest polymer demo"
  (:require [miraj.polymer.paper :as paper :refer [card]])

  (:body
   (h1 "Hello World!")
   (h2 "(from miraj.polymer.paper, sweetest)")
   (div :#cards
        (paper/card {:heading "Hello, you ol' Card!"}
                    (div :.card-content "Some content")
                    (div :.card-actions
                         (paper/button :#btn!raised "Click me"))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(binding [miraj/*debug* true
          miraj/*verbose* true
          miraj/*keep* true
          miraj/*pprint* true
          *compile-path* "target"]
  (miraj/mcc :page (var index)
             :polyfill :lite ;; :full, :imports, :components, :shadow
             :linkage "imports.html"
             ))

 ;;:namespaces #{*ns*}
                 ;;:pages ['index]

  ;; (miraj/compile-page-nss #{(-> *ns* ns-name)} true true))

  ;; (wc/compile {:nss #{(-> *ns* ns-name)}
  ;;              :pages [index]
  ;;              :optimization 2 :debug true})
