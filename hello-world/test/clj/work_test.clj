(ns work-test
  ;; (:import [hello.styles world])
  (:require [miraj.core :as miraj]
            [miraj.compiler :as wc]
            [miraj.co-dom :as x]
            [miraj.html :as h]
            ;; [miraj.polymer.paper :as paper :refer [button card textarea]]
            ;; [miraj.polymer.iron :as iron :refer [icon]]

            ;; [work.pages.styled :as s]

            [clojure.pprint :as pp]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clojure.tools.logging :as log :only [trace debug error info]]
            :reload))

;(remove-ns 'work-test)
;(remove-ns 'work.pages.hello)
;(remove-ns 'miraj.core)
;(remove-ns 'miraj.co-dom)
;(remove-ns 'miraj.html)

(println "loading work-test")

;;(remove-ns 'work.pages.hello)
;;(remove-ns 'work.pages.goodbye)
;;(remove-ns 'work.pages.styled)
;;(remove-ns 'polymer.iron)

;;(ns-unmap *ns* 'homepage)

;;(require '[work.pages.styled :as s] :reload)
;(require '[work.pages.hello :as hello] :reload)

(do
  (require '[miraj.co-dom :as x]
           '[miraj.compiler :as wc]
           '[miraj.core :as miraj] :reload)

  ;; (require '[work.pages.styled.externals :as page] :reload)
  ;; (require '[work.pages.styled.imported :as page] :reload)
  (require '[work.pages.styled.style-modules :as page] :reload)

  (->> #'page/hello
       miraj/normalize
       miraj/optimize
       (x/pprint :html)))

(h/script "foo")

;; (meta #'s/hello)

;; (println (x/serialize (h/div {:foo 'bar} "hi")))

(def page-ns (find-ns 'work.pages.goodbye))

(println (ns-map page-ns))


;; (binding [*ns* page-ns]
;;   (println (all-ns)))

#_(let [refs (filter (fn [r] (let [nm (-> r second meta :ns ns-name)]
                             (not= 'clojure.core nm)))
                       (ns-refers page-ns))]
  (doseq [ref refs] (println ref)))

#_(let [as (ns-aliases page-ns)
      ms (filter #(-> % second meta :miraj/miraj) as)]
  (doseq [m ms] (println m)))


;; (def pv (var hello/homepage))

;; ;;(pp/pprint (meta pv))
;; (wc/compile pv)

;; (wc/compile-polymer-components)

;; (def paper-ns (find-ns 'miraj.polymer.paper))
;; (def iron-ns (find-ns 'miraj.polymer.iron))

;; (:_webimports (meta (var hello/homepage)))

;; (println (:miraj/miraj (meta (var paper/card))))

;; (doseq [[k v] (sort (ns-refers paper-ns))] (println "k: " k ", v: " v))

;; (doseq [[k v] (filter #(= iron-ns (:ns (meta (last %)))) (ns-publics iron-ns))] (println "k: " k ", v: " v))

;; (doseq [[k v] (ns-publics paper-ns)] (println "k: " k ", v: " v))
;; (doseq [[k v] (ns-publics *ns*)] (println "k: " k ", v: " v))

;; (doseq [[k v] (ns-map paper-ns)] (println "k: " k ", v: " v))

;; (x/pprint (x/normalize #'hello/homepage))

;; (pp/pprint hello/homepage)

;; (println (meta (find-ns 'miraj.polymer.paper)))

;; ;; (var hello/homepage)

;; ;; (x/pprint hello/homepage)

;; ;; (pp/pprint (boot.core/get-checkouts))

;; ;; (pp/pprint (meta #'hello/homepage))





;; ;; (meta (var hello/homepage))

;; ;; hello-world

;; ;; (var hello-world)

;; ;; (hello-world)

;; ;; (meta (var hello-world))

;; ;; (pp/pprint (boot.core/get-checkouts))


;; ;; button

;; ;; (meta (var my.custom.components/foo))

;; ;; (meta (var polymer.paper/card))

;; ;; (x/serialize (button))

(println "loaded work_test\n")
