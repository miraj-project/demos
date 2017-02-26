(ns work.pages.styled.imported
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.polymer.paper :as paper :refer [button card]]
            [miraj.polymer.paper.styles :as pstyles :refer [color]]
            [miraj.polymer.iron :as iron :refer [icons icon]]
            [miraj.demo.greeter :as hi] ; :refer :all] ; [hello]]
            [miraj.demo.salutations :as greetings :refer :all]

            ;; for testing only:
            [miraj.compiler :as wc]
            [miraj.co-dom :as x]
            :reload))

(miraj/defpage index
  "Styled version of hello webpage."

  ;; html metadata first
  {:html/title "Miraj demo: imported styles"
   :html/description "this page demonstrates usage of imported styles."}

  ;; imports are html files that may contain any html. here we treat
  ;; the namespace symbol as the path of a directory containing files:
  (:import [[styles.hello button card]]) ;; /styles/hello/button.html, /styles/hello/card.html

  ;; imported modules are different; they are <dom-module> elements in an HTML
  ;; file rather than files in a directory. see style_modules.clj for an example.

;;  (:css [[styles.css hello]])

  (:body
   ;; (h/link {:href "/styles/button.html" :rel "import"})
   (h/h1 "Hello from index page!")
   (greetings/howdy)
   ;; (h/script {:src "/miraj/demo/salutations.js"})

   (h/h3 "here's another component:")
   (greetings/hello)

   (h/div ::cards
          (paper/card {:heading "Demo of index page."}
                      (h/div (iron/icon {:icon "settings-bluetooth"}))
                      (h/div {:class "card-content"} "This is a styled paper-card, containing a styled paper-button.")
                      (h/div {:class "card-actions"}
                             (paper/button ::.green {:raised nil} "Some action"))))

   ;; (h/h3 "Now it's time to say goodbye with another component:")
   ;; (greetings/bye)
   (h/h3 "testing")
   (miraj.demo.greeter/hello)
   ))

(miraj/defpage home
  "demo, second page in pagespace"

  ;; html metadata first
  {:html/title "Miraj demo: page 2"
   :html/description "this page demonstrates multiple pages in a pagespace."}
;;  (:import [[styles.hello button card]]) ;; /styles/hello/button.html, /styles/hello/card.html
  (:body
   ;; (h/link {:href "/styles/button.html" :rel "import"})
   (h/h1 "Hello from home page!")
   ;; (greetings/howdy)
   ;; (h/script {:src "/miraj/demo/salutations.js"})

   ;; (h/h3 "here's another component:")
   ;; (greetings/hello)

   (h/div ::cards
          (paper/card {:heading "Demo of imported styles."}
                      (h/div (iron/icon {:icon "settings-bluetooth"}))
                      (h/div {:class "card-content"} "This is a styled paper-card, containing a styled paper-button.")
                      (h/div {:class "card-actions"}
                             (paper/button ::.green {:raised nil} "Some action"))))

   ;; (h/h3 "Now it's time to say goodbye with another component:")
   ;; (greetings/bye)
   (h/h3 "testing")
   (hi/hello)
   ))

(stencil.loader/set-cache (clojure.core.cache/ttl-cache-factory {} :ttl 0))

(binding [*compile-path* "tmp"]
  ;;miraj/*miraj-sym* (str (gensym "miraj"))]
  (wc/compile-page-nss #{(-> *ns* ns-name) 'work.pages.hello} true true))

(binding [*compile-path* "tmp"]
  (wc/link-pages #{(-> *ns* ns-name) 'work.pages.hello} true))

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
