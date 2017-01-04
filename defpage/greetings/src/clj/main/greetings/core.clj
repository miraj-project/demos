(ns greetings.core
  (:require [greetings.pages.hello :as hi]
            [miraj.markup :as x]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.handler.dump :refer :all] ; ring-devel
            [ring.util.response :as rsp]
            [ring.util.servlet :as ring]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes

  (GET "/aloha" []

       (-> (var hi/homepage)
           x/normalize
           x/optimize
           x/serialize))

  ;; (GET "/sayonara" []
  ;;      (pages/goodbye/homepage))

  (GET "/foo" [] "<h1>Hello World</h1>")

  ;; (GET "/bower_components/paper-button/paper-button.html"
  ;;      []
  ;;      (rsp/file-response "/bower_components/paper-button/paper-button.html" {:root "target"}))

       ;; "<h1>bower</h1>")

  (route/files "/") ; {:root "target"})

;;  (route/resources "/" {:root "bower_components"})

  (route/not-found "NOT FOUND"))


(def app
  
  (wrap-defaults app-routes site-defaults))

;; servlets
;; (ring/defservice
;;    (-> (routes
;;         app-routes)
;;        (wrap-defaults api-defaults)
;;        ))
