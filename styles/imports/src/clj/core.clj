(ns core
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            ;;[miraj.polymer.paper :as paper :refer [button]]

            ;; for testing only:
            [miraj.compiler :as wc]
            [miraj.co-dom :as x]
            #_:reload))

(miraj/defpage ^{:base "/"} index
  "Styles Demo Page."

  ;; html metadata first
  {:html/title "Miraj demo: styles"
   :html/description "This page demonstrates how to import/include style components."}

  (:require ;;[miraj.polymer.paper :as paper :refer [button]]
            [miraj.polymer.iron :as iron :refer [icons icon demo-snippet]])

  ;; BASE CASE: style modules
  (:styles [;;  color is a style fn within miraj/polymer/paper/styles.clj
            [miraj.polymer.paper.styles color] ;; dropdown-menu]

            [miraj.polymer.iron.styles demo-helpers flex]
            [miraj.polymer.iron.icons device] ;; hardware]

            ;; most styles are style elts in an html file
            ;; but "shared" styles are dom-modules in html files

            ;; paper-item/paper-item-shared-styles.html
            ;; contains "shared" style module called
            ;; paper-item-shared-styles

            ;; [miraj.polymer.paper.styles item]

            ;; two kinds of ns, filesys and modular. :import uses
            ;; filesys, :require uses modular. icons and shared styles
            ;; use modular, so they use :refer

            ;; e.g. iron-flex-layout-classes.html contains multiple style modules:
            ;; iron-flex, iron-flex-reverse, etc. we just want to name these simply:

            ;; [miraj.polymer.iron.styles.shared :refer [flex flex-reverse]]

            ;; then again as long as there are no name clashes we can
            ;; always go modular but use the import syntax
            ;; [miraj.polymer.iron.styles flex flex-reverse]
            ;; or
            ;; [miraj.polymer.iron.styles.layout flex flex-reverse]
            ;; even though flex and flex-reverse are modules, not files, no prob,
            ;; they're implemented as fns in iron.styles

            ;; so really no need to expose distinction between <style> and style modules.


            ;; divs and headers are dom-modules within galapagos.html
            ;; with miraj ns packaging:
;            [styles.themes.galapagos :modules [divs headers]]

            ;; with filesystem packaging:
;            {:href "/styles/themes/galapagos.html" :modules [divs headers]}
            ])

;  (:icons [...])

            ;; require a cljs resource
  (:js [{:src "main.js"}])

  #_(:css [{:href "https://unpkg.com/purecss@0.6.2/build/pure-min.css"
          :integrity "sha384-UQiGfs9ICog+LwheBSRCt1o5cbyKIHbwjWscjemyBMT9YCUMZffs6UqUTd0hObXD"
          :crossorigin "anonymous"}
         [styles.css button card hello]
         {:href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"}])

;;  (:css [[styles.css user]]) ;; /styles/css/user.css

;  (:import [[styles.css user]]) ;; /styles/css/user.html

  ;; you can use :import to import anything.  if your styles are
  ;; packaged in miraj libs, you can use the namespace syntax;
  ;; otherwise, you can use the href syntax.

  ;; if your styles are in miraj component packages, you can use the
  ;; namespace syntax.
;  (:styles [[styles.themes.galapagos button card]])

  (:body
   (h/h1 "Welcome")

   (h/div ::main
    (h/h2 "I am an h2, in a div#main"))

   (h/div ::.foo
          (h/span "I am a span in a .foo div"))
   ))

(-> index var meta :miraj/miraj)

;(require '[miraj.compiler :as wc])

;; (stencil.loader/set-cache (clojure.core.cache/ttl-cache-factory {} :ttl 0))

;; (binding [*compile-path* "tmp"]
;;   ;;miraj/*miraj-sym* (str (gensym "miraj"))]
;;   (wc/compile-page-nss #{(-> *ns* ns-name) 'work.pages.hello} true true))

;; (binding [*compile-path* "tmp"]
;;   (wc/link-pages #{(-> *ns* ns-name) 'work.pages.hello} true))

;; (-> 'miraj.demo.greeter find-ns meta)


;(-> index var meta)

;(-> *ns* ns-name)

;(remove-ns 'work.pages.styled.imported)
;(remove-ns 'miraj.demo.hello)


;; (remove #(or (nil? %) (= 'clojure.core (-> % ns-name)))
;;         (map (fn [[k v]] (-> v meta :ns)); :miraj/miraj)) ;(:ns (meta (var v))))
;;              (ns-refers *ns*)))

;; (remove nil? ;; #(or (nil? %) (= 'clojure.core (-> % ns-name)))
;;         (filter (fn [v] (-> v meta :miraj/miraj)) ;(:ns (meta (var v))))
;;              (all-ns)))
;; ;             (ns-refers *ns*)))

;; (all-ns)

;(ns-refers *ns*)

;;(ns-aliases *ns*)
