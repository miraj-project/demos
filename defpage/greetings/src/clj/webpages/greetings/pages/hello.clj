(ns greetings.pages.hello
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.markup :as x]

            [clojure.pprint :as pp]
            ;; [polymer.paper :as paper]
            ;; [polymer.iron :as iron]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(println "loading greetings.pages.hello")

;;(remove-ns 'greetings.pages.hello)
;;(remove-ns 'polymer.iron)

(def home-meta
  {:title "miraj hello demo"
   :description "this page demonstrates usage of miraj.html and polymer"
   :platform {:apple {:mobile-web-app {:capable true
                                       :status-bar-style :black
                                       :title :hello-str} ;; "Hello"
                      :touch {:icon {:uri "/images/touch/chrome-touch-icon-192x192.png"}}}
              :ms {:navbutton-color "#FFCCAA"
                   :tile-color "#3372DF"
                   :tile-image "images/ms-touch-icon-144x144-precomposed.png"}
              :mobile {:agent {:format :html5
                               :url "http://example.org/"}
                       :web-app-capable true}}})

;;(ns-unmap *ns* 'homepage)
(miraj/defweb-page homepage
  "clojure docstring for webpage here (use metadata map for HTML, e.g. description)."
  home-meta  ;; metadata map
  (:require [polymer.paper :as paper :refer [button card textarea]]
            [polymer.iron  :as iron :refer [icon icons]]
            [org.example.greetings.departure :as bye :refer [goodbye-world]]
            [org.example.greetings.arrival   :as hi  :refer [hello-world]] ;; howdy-world
            ;; [vaadin :as vaadin :refer [combo-box date-picker grid icons upload]]
            ;; [bosonic :as b :refer [accordion draggable dropdown]]
            ;; (foo bar baz)
            ;; :cdn
            :reload
            ;; :verbose
            )
  ;; (:import (hello.styles hello))
  ;;          (hello.scripts jquery bootstrap-min underscore))
  (:body
   (h/h1 "hello miraj!")
   (h/div (icon {:icon "menu"}))
   (h/div (hi/hello-world))
   (h/div (bye/goodbye-world))
   (h/div ::cards
          (paper/card {:heading "Hello, you ol' Card!"}
                      (h/div {:class "card-content"} "Some content")
                      (h/div {:class "card-actions"}
                             (paper/button {:raised nil} "Some action"))))))

;; (var homepage)

;; (x/pprint homepage)

;; ;;(pp/pprint (boot.core/get-checkouts))

;; (pp/pprint (meta #'homepage))

;; (x/pprint
;;  (x/optimize
;;   (x/normalize #'homepage)))

;; (meta (var homepage))

;; hello-world

;; (var hello-world)

;; (hello-world)

;; (meta (var hello-world))

;; (pp/pprint (boot.core/get-checkouts))


;; button

;; (meta (var my.custom.components/foo))

;; (meta (var polymer.paper/card))

;; (x/serialize (button))


;; (homepage)
;; (x/pprint
;;  ;;(x/serialize
;;   (x/normalize homepage)))

;; (meta (homepage))
;; (meta homepage)

;; (miraj/defweb-cofn main
;;   []
;;   ;; (log/info "loading datafab.main")
;;   (h/body {}

;;    (h/div {:id "header"}
;;     (iron/image {:id "logo" :src "images/norclogo.png"}))

;;    ;; (paper/toolbar {:id "tb"}
;;    ;;  (paper/icon-button {:icon "menu" :on-tap "menuAction"})
;;    ;;  (iron/image {:id "logo" :src "images/norclogo.png"})
;;    ;;  (h/span {:class "title"} "NORC at the University of Chicago")
;;    ;;  (paper/icon-button {:icon "more-vert" :on-tap "moreAction"})
;;    ;;  (paper/icon-button {:icon "refresh"})
;;    ;;  (paper/icon-button {:icon "add"} "+"))

;;    (h/div {:id "cards"}
;;           (h/h5 "NORC at the University of Chicago is pleased to
;;                                  accept applications for its")
;;           (h/h1 "Conference on Data Fabrication/Curbstoning")
;;           (h/h2 "23 February 2016")
;;           (h/h3 "Washington, D.C.")

;;           (paper/card (h/div {:class "card-content"}
;;                              (h/div "This will be an intensive
;;                                  one-day conference on detecting,
;;                                  preventing and/or repairing
;;                                  fabrication in surveys.")))
;;           (paper/card (h/div {:class "card-content"}
;;                              (h/div "While there will be web
;;                                  access, in-person access is by
;;                                  invitation only to established
;;                                  experts with a track record of
;;                                  research and publication in one of
;;                                  the following topic
;;                                  areas:")))
;;           (paper/card
;;            (h/div {:class "card-content"}
;;                   (h/div (h/ul
;;                           (paper/item
;;                            (iron/icon {:icon "check"})
;;                            (h/span
;;                            "The role of organizational
;;                               culture in preventing fabrication."))

;;                           (paper/item
;;                            (iron/icon {:icon "check"})
;;                            (h/span "Beyond the curbstone -- new
;;                               methods for detecting fabricated survey
;;                               data."))

;;                           (paper/item
;;                            (iron/icon {:icon "check"})
;;                            (h/span "Fabrication in international
;;                               surveys and difficult research
;;                               environments."))

;;                           (paper/item
;;                            (iron/icon {:icon "check"})
;;                            (h/span "Improving fabrication detection
;;                               methods using synthetic data."))

;;                           (paper/item
;;                            (iron/icon {:icon "check"})
;;                            (h/span "Approaches to reporting and
;;                               storing datasets with fabricated
;;                               data."))

;;                           (paper/item
;;                            (iron/icon {:icon "check"})
;;                            (h/span "Practical and ethical
;;                               considerations for handling data
;;                               fabrication."))))))
;;           (paper/card
;;            (h/div {:class "card-content"}
;;                   (h/div "Qualified applicants will be invited to
;;                       participate and will be able to attend all
;;                       sessions but will be expected to speak in a
;;                       panel discussion on their area of expertise.")))
;;           (paper/card
;;            (h/div {:class "card-content"}
;;                   (h/div "Lunch will be provided and coffee/soft
;;                       drinks throughout the day. Participants will
;;                       need to arrange and purchase their own
;;                       transportation and lodging to attend the
;;                       conference. In return, they will have the
;;                       opportunity to engage in in-depth discussions
;;                       with noted experts in this area of research
;;                       vital to researchers and consumers of research
;;                       findings across the social sciences.")))
;;           (paper/card
;;            (h/div {:class "card-content"}
;;                   (h/div "Participation will be limited to about
;;                       30 experts onsite. Presentations will be
;;                       organized into panels corresponding to the six
;;                       topic areas.  Designated session leaders will be
;;                       asked to prepare a summary on their session for
;;                       inclusion in a book to be published in September
;;                       of 2016.")))
;;           (paper/card
;;            (h/div {:class "card-content"}
;;                   (h/div "To apply for inclusion in this
;;                        workshop click: "
;;                          (h/span
;;                           (paper/button {:id "apply"
;;                                          :data-dialog "scrolling"
;;                                          :raised "raised"
;;                                          :onclick "clickHandler(event)"} "Apply")))))
;;           (h/div {:class "card-content"}
;;                  (h/div "For additional
;;                        information or questions, contact"
;;                         (h/ul
;;                          (h/li (h/a {:href "mailto:skoczela@massincpolling.com"} "Steve Koczela")
;;                                ", (617) 224-1646")
;;                          (h/li (h/a {:href "mailto:scheuren@aol.com"} "Fritz Schueren")
;;                                     ", (301) 634-9439")))))
;;    ;; (mydialog)
;;    (paper/toast {:id "toasty" :text "Thank you!"})

;;    (h/script "
;;     document.getElementById('formPost').addEventListener('iron-form-submit', closeit);
;;     function submitHandler(event) {
;;       console.log('SUBMIT');
;;       document.getElementById('formPost').submit();
;;     }
;;     function closeit(event) {
;;       document.getElementById('formPost').reset();
;;       document.getElementById('scrolling').close();
;;       document.getElementById('toasty').open();
;;     }
;;     function resetHandler(event) {
;;       Polymer.dom(event).localTarget.parentElement.reset();
;;     }")

;;    (h/script  "
;;     function clickHandler(e) {
;;       var button = e.target;
;;       if (!button.hasAttribute('data-dialog')) {
;;         return;
;;       }
;;       var id = button.getAttribute('data-dialog');
;;       var dialog = document.getElementById(id);
;;       if (dialog) {
;;         dialog.open();
;;       }
;;     }")))

;; ;;(x/pprint (main))


;; (defn homepage
;;   []
;;   (do
;;     ;; (println "HTML: ")
;;     ;; (println (h/serialize home-html))

;;     (-> home-html
;;         (with-meta home-meta)
;;         h/normalize
;;         h/optimize
;;         h/serialize)))


(println "loaded greetings.pages.hello\n")
