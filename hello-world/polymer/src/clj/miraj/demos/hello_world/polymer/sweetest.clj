(ns miraj.demos.hello-world.polymer.sweetest
  (:refer-clojure :exclude [map meta time])
  (:require [miraj.core :as miraj :refer [compile-page-nss defpage normalize]]
            [miraj.html :refer :all :exclude [button]]
            [miraj.co-dom :as codom :refer [pprint serialize]]
            [miraj.polymer.paper :as paper :refer [button]]
            :reload))

;; sweetest: use sweetest/deps.edn
(defpage
  "Hello, Sweetest Polymer World demo"
  (:require [miraj.polymer.iron :as iron :refer [collapse icon demo-snippet]]
            [miraj.polymer.paper :as paper :refer [card]])

   (:body
   (h1 :.amber "Hello, Sweetest Polymer World!")

   (div :#cards.vertical-section-container.centered
        (paper/card {:heading "Hello, you ol' Card!"}
                    (div :.card-content
                         (iron/icon :.star {:icon "star"})
                         "Some content")
                    (div :.card-actions.centered
                         (paper/button :#sweetestbtn.centered?raised "Click me"))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#_(binding [miraj/*debug* true
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
