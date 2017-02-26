;;     compile 'miraj:miraj:1.1.4-SNAPSHOT'

(miraj/co-ns datafab.core
       "docstring here"
       (:title "DataFab 2016")
       (:main 'main)
       (:polymer [polymer.paper :as paper :only [button card checkbox
                                                 dialog dialog-scrollable
                                                 icon-button input item
                                                 styles textarea toast toolbar]]
                 [polymer.iron :as iron :only [flex-layout form icons image]]
                 [polymer.neon :as neon :only [animation]])
                 ;; [polymer.font :use [roboto]])
                 ;; [polymer.support :use [promise-polyfill webcomponents]])
       (:styles [styles.datafab :css :refer [core]])
       (:html [paper.styles :one [color]])
       ;; (:scripts [scripts.hello.world :js :refer [core]])
       ;;        ;; [foo.bar :css :refer [baz]]
       ;;        ;; [foo.bar :js :refer [baz]]
       ;;FIXME: also support: :html, maybe :theme?
       (:require [miraj :as miraj :refer :all]
                 [miraj.html :as h]
                 [miraj.http.response :refer [ok created]]
                 [cheshire.core :as json :refer :all]
                 [clojure.tools.logging :as log :only [trace debug error info]]
                 [clojure.pprint :as pp]
                 [clojure.string :as str])
       (:import [com.google.appengine.api.mail MailService]
                [java.util Properties]
                [javax.mail Message Message$RecipientType MessagingException Transport]
                [javax.mail Session]
                [javax.mail.internet AddressException InternetAddress MimeMessage]))

;; (log/trace "loading co-ns datafab.core")

;; (println "datafab.core metadata: " (meta (find-ns 'datafab.core)))

(defn mydialog []
  (paper/dialog {:id "scrolling"}
                (h/h2 "DataFab 2016 Application")
                (paper/dialog-scrollable
                (h/div {:class "horizontal-section"}
                       (h/form {:is "iron-form" :id "formPost" :method "get" :action "/application"}
                               (paper/input {:name "applicant-name"
                                             :label "Applicant Name"
                                             :required "required"
                                             :autofocus "autofocus"})
                               (h/br)
                               (paper/input {:name "affiliation"
                                             :label "Affiliation"
                                             :required "required"})

                               (paper/input {:name "address1"
                                             :label "address line 1"
                                             :required "required"})

                               (paper/input {:name "address2"
                                             :label "address line 2"})
                               (paper/input {:name "city"
                                             :label "city"
                                             :required "required"})
                               (paper/input {:name "state"
                                             :label "state"
                                             :required "required"})
                               (paper/input {:name "zip"
                                             :label "zip"
                                             :required "required"})
                               (paper/input {:name "email"
                                             :label "email"
                                             :required "required"})

                               (h/label "Interest")
                               (h/select {:name "interest"}
                                         (h/option {:value "culture"} "Organizational culture")
                                         (h/option {:value "methods"} "New methods")
                                         (h/option {:value "i18n"} "International")
                                         (h/option {:value "synthetic"} "Synthetic data methods")
                                         (h/option {:value "reporting"} "Reporting and storing")
                                         (h/option {:value "practical"}
                                                   "Practical/Ethical considerations"))

                               (h/div
                                (h/div "Please briefly describe your
                                experience related to survey data
                                fabrication.")
                                (paper/textarea {:label "comments?"
                                                 :name "comments"
                                                 :textarea "textarea"
                                                 :value ""
                                                 :input "input"}))

                               (h/div {:class "buttons"}
                                      (paper/button {:dialog-dismiss ""} "Cancel")
                                      (paper/button {;;:dialog-confirm "dialog-confirm"
                                                     :onclick "submitHandler(event)"} "Submit")))))))

;;(println (meta hello.world))
(miraj/co-fn main
  []
  ;; (log/info "loading datafab.main")
  (h/body {}

   (h/div {:id "header"}
    (iron/image {:id "logo" :src "images/norclogo.png"}))

   ;; (paper/toolbar {:id "tb"}
   ;;  (paper/icon-button {:icon "menu" :on-tap "menuAction"})
   ;;  (iron/image {:id "logo" :src "images/norclogo.png"})
   ;;  (h/span {:class "title"} "NORC at the University of Chicago")
   ;;  (paper/icon-button {:icon "more-vert" :on-tap "moreAction"})
   ;;  (paper/icon-button {:icon "refresh"})
   ;;  (paper/icon-button {:icon "add"} "+"))

   (h/div {:id "cards"}
          (h/h5 "NORC at the University of Chicago is pleased to
                                 accept applications for its")
          (h/h1 "Conference on Data Fabrication/Curbstoning")
          (h/h2 "23 February 2016")
          (h/h3 "Washington, D.C.")

          (paper/card (h/div {:class "card-content"}
                             (h/div "This will be an intensive
                                 one-day conference on detecting,
                                 preventing and/or repairing
                                 fabrication in surveys.")))
          (paper/card (h/div {:class "card-content"}
                             (h/div "While there will be web
                                 access, in-person access is by
                                 invitation only to established
                                 experts with a track record of
                                 research and publication in one of
                                 the following topic
                                 areas:")))
          (paper/card
           (h/div {:class "card-content"}
                  (h/div (h/ul
                          (paper/item
                           (iron/icon {:icon "check"})
                           (h/span
                           "The role of organizational
                              culture in preventing fabrication."))

                          (paper/item
                           (iron/icon {:icon "check"})
                           (h/span "Beyond the curbstone -- new
                              methods for detecting fabricated survey
                              data."))

                          (paper/item
                           (iron/icon {:icon "check"})
                           (h/span "Fabrication in international
                              surveys and difficult research
                              environments."))

                          (paper/item
                           (iron/icon {:icon "check"})
                           (h/span "Improving fabrication detection
                              methods using synthetic data."))

                          (paper/item
                           (iron/icon {:icon "check"})
                           (h/span "Approaches to reporting and
                              storing datasets with fabricated
                              data."))

                          (paper/item
                           (iron/icon {:icon "check"})
                           (h/span "Practical and ethical
                              considerations for handling data
                              fabrication."))))))
          (paper/card
           (h/div {:class "card-content"}
                  (h/div "Qualified applicants will be invited to
                      participate and will be able to attend all
                      sessions but will be expected to speak in a
                      panel discussion on their area of expertise.")))
          (paper/card
           (h/div {:class "card-content"}
                  (h/div "Lunch will be provided and coffee/soft
                      drinks throughout the day. Participants will
                      need to arrange and purchase their own
                      transportation and lodging to attend the
                      conference. In return, they will have the
                      opportunity to engage in in-depth discussions
                      with noted experts in this area of research
                      vital to researchers and consumers of research
                      findings across the social sciences.")))
          (paper/card
           (h/div {:class "card-content"}
                  (h/div "Participation will be limited to about
                      30 experts onsite. Presentations will be
                      organized into panels corresponding to the six
                      topic areas.  Designated session leaders will be
                      asked to prepare a summary on their session for
                      inclusion in a book to be published in September
                      of 2016.")))
          (paper/card
           (h/div {:class "card-content"}
                  (h/div "To apply for inclusion in this
                       workshop click: "
                         (h/span
                          (paper/button {:id "apply"
                                         :data-dialog "scrolling"
                                         :raised "raised"
                                         :onclick "clickHandler(event)"} "Apply")))))
          (h/div {:class "card-content"}
                 (h/div "For additional
                       information or questions, contact"
                        (h/ul
                         (h/li (h/a {:href "mailto:skoczela@massincpolling.com"} "Steve Koczela")
                               ", (617) 224-1646")
                         (h/li (h/a {:href "mailto:scheuren@aol.com"} "Fritz Schueren")
                                    ", (301) 634-9439")))))
   (mydialog)
   (paper/toast {:id "toasty" :text "Thank you!"})

   (h/script "
    document.getElementById('formPost').addEventListener('iron-form-submit', closeit);
    function submitHandler(event) {
      console.log('SUBMIT');
      document.getElementById('formPost').submit();
    }
    function closeit(event) {
      document.getElementById('formPost').reset();
      document.getElementById('scrolling').close();
      document.getElementById('toasty').open();
    }
    function resetHandler(event) {
      Polymer.dom(event).localTarget.parentElement.reset();
    }")

   (h/script  "
    function clickHandler(e) {
      var button = e.target;
      if (!button.hasAttribute('data-dialog')) {
        return;
      }
      var id = button.getAttribute('data-dialog');
      var dialog = document.getElementById(id);
      if (dialog) {
        dialog.open();
      }
    }")))

(defn serialize []
  (spit "datafab.html"
        (with-out-str (miraj.markup/pprint :html (miraj.sync/activate 'datafab.core/main)))))

(defn dump
  []
  (println "DUMP")
  (miraj/dump-dispatch-map)
  (ok "DUMP!"))


