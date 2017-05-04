(ns miraj.demos.hello-world.polymer.sweet
  (:require [miraj.core :as miraj :refer [compile-page-nss defpage normalize]]
            [miraj.html :refer :all :exclude [button]]
            [miraj.co-dom :as codom :refer [pprint serialize]]
            [miraj.polymer.paper :as paper :refer [button]]
            :reload))

;; like bitter, but we use defpage, so the <head> stuff is taken care
;; of automatically
(defpage index
  "demo"
  (require [miraj.polymer.paper :as paper :refer [card]])

  (:body
   (h1 "Hello World!")
   (h2 "(from miraj.polymer.paper, sweet)")
   (div :#cards
        (paper/card {:heading "Hello, you ol' Card!"}
                    (div :.card-content "Some content")
                    (div :.card-actions
                         (paper/button :!raised {:onclick "handle_click('sweet paper');"}
                                       "Some action"))))
   (script {:src "/js/custom-elements.min.js"
            :type "text/javascript"})
   (script {:src "/js/miraj.js"
            :type "text/javascript"})))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; repl:
;; (serialize index)
;; (pprint index)
;; print to file  (in cli: use boot.miraj/compile)
;; this will write to *compile-path*/<ns>/<page>.html,
;; i.e. target/miraj/demos/hello_world/polymer/sweet/index.htmnl
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


