(ns miraj.demos.hello-world.polymer.sweetest
  (:require [miraj.core :as miraj :refer [compile-page-nss defpage normalize]]
            [miraj.html :refer :all :exclude [button]]
            [miraj.co-dom :as codom :refer [pprint serialize]]
            [miraj.polymer.paper :as paper :refer [button]]
            :reload))

;; sweetest: use sweetest/deps.edn

(defpage
  "sweetest polymer demo"
  (:require [miraj.polymer.iron :as iron :refer [collapse icon demo-snippet]]
            [miraj.polymer.paper :as paper :refer [card]])

  ;; these are lexical dependencies, but data, not functions?
  ;; (ditto for images?)
  ;; (:icons [[miraj.polymer.iron communication hardware iron social]
  ;;          [miraj.polymer.iron.icons communication hardware iron social]
  ;;          [miraj.vaadin vaadin]])

  (:styles [[miraj.polymer.iron.icons communication hardware iron social]
            [miraj.polymer.iron.styles demo flex-mixins]
            [miraj.polymer.paper.styles color typography]])

  (:body
   (h1 :.amber "Hello World!")
   (h2 "(from miraj.polymer.paper, sweetest)")
   (div :#cards.vertical-section-container.centered
        (paper/card {:heading "Hello, you ol' Card!"}
                    (div :.card-content
                         (iron/icon :.star {:icon "star"})
                         "Some content")
                    (div :.card-actions.centered
                         (paper/button :#btn.centered!raised "Click me"))))))

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
