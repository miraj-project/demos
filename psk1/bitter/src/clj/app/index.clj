(ns index
  ;; (:refer-clojure :exclude [list])
  (:require [miraj.core :as miraj :refer [defcomponent]]
            [miraj.html :as h]
            [miraj.html.x.apple :as apple]
            [miraj.html.x.ms :as ms]
            [miraj.polymer :as p]
            [psk.gadgets :as gadgets :refer :all]))

            ;; for testing only:
            ;; [miraj.core :as wc]
            ;; [miraj.co-dom :as x]
            ;; :reload))

;;(-> shell var meta :miraj/miraj)

;(doseq [r (filter #(-> % second meta :miraj/miraj) (ns-map *ns*))]
;  (println r))

;; (println "loading index")

#_(defcomponent shell :html app-shell
  (:require [miraj.polymer.iron :as iron :refer [pages selector]]
            [miraj.polymer.paper :as paper :refer [drawer-panel
                                                   icon-button
                                                   item
                                                   card
                                                   material
                                                   menu
                                                   scroll-header-panel
                                                   toast
                                                   toolbar]])

  (:codom

   (paper/drawer-panel
    :#paperDrawerPanel
    ;; Drawer Scroll Header Panel
    (paper/scroll-header-panel :?drawer?fixed
        ;; Drawer Toolbar
        (paper/toolbar :#drawerToolbar (h/span :.menu-name "Menu"))

        ;; Drawer Content
        (paper/menu :.app-menu {:attr-for-selected "data-route"
                                :selected (p/bind! :route)}
          (h/a {:data-route "home" :href (p/bind!! :baseUrl)}
            (iron/icon {:icon "home"})
            (h/span "Home"))
          (h/a {:data-route "users" :href (str (p/bind!! :baseUrl)"users")}
            (iron/icon {:icon "info"})
            (h/span "Users"))

          (h/a {:data-route "contact" :href (str (p/bind!! :baseUrl) "contact")}
            (iron/icon {:icon "mail"})
            (h/span "Contact"))))

      ;; Main Area
      (paper/scroll-header-panel :?main#headerPanelMain?condenses?keep-condensed-header
        ;; Main Toolbar
        (paper/toolbar :#mainToolbar.tall
          (paper/icon-button :#paperToggle {:icon "menu"} :?paper-drawer-toggle)
          (h/span :.space)

          ;; Toolbar icons
          (paper/icon-button {:icon "refresh"})
          (paper/icon-button {:icon "search"})

          ;; Application name
          (h/div :.middle.middle-container
            (h/div :.app-name "Miraj Starter Kit"))

          ;; Application sub title
          (h/div :.bottom.bottom-container
            (h/div :.bottom-title "The future of the web today")))

        ;; Main Content
        (h/div :.content

           (iron/pages {:attr-for-selected "data-route" :selected (p/bind!! :route)}
            (h/section {:data-route "home" :tabindex "-1"}
              (paper/material {:elevation "1"}
                (gadgets/greeting)

                (h/p :.subhead "You now have:")
                (gadgets/list)

                (h/p
                "Looking for more Web App layouts? Check out our "
(h/a {:href "https://github.com/PolymerElements/app-layout-templates"} "layouts")
" collection. You can also "
(h/a {:href "http://polymerelements.github.io/app-layout-templates/"} "preview")
" them live."))

              (paper/material {:elevation "1"}
                (h/p "This is another card."))

              (paper/material {:elevation "1"}
                (h/h2 :#license "License")
                (h/p "Everything in this repo is BSD style license unless otherwise specified.")
                (h/p "Copyright (c) 2015 The Polymer Authors. All rights reserved.")
                (h/p "Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:")
                (h/ul
                (h/li "Redistributions of source code must retain the above copyright
                notice, this list of conditions and the following disclaimer.")
                (h/li "Redistributions in binary form must reproduce the above
                copyright notice, this list of conditions and the following disclaimer
                in the documentation and/or other materials provided with the
                distribution.")
                (h/li "Neither the name of Google Inc. nor the names of its
                contributors may be used to endorse or promote products derived from
                this software without specific prior written permission."))

                (h/p "THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS &quot;AS IS&quot; AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.")))

            (h/section {:data-route "users" :tabindex "-1"}
              (paper/material {:elevation "1"}
                (h/h1 :.page-title {:tabindex "-1"} "Users")
                (h/p "This is the users section")
                (h/a {:href (str (p/bind-attr!! :baseUrl) "users/Addy")} "Addy")
                (h/a {:href (str (p/bind-attr!! :baseUrl) "users/Rob")} "Rob")
                (h/a {:href (str (p/bind-attr!! :baseUrl) "users/Chuck")} "Chuck")
                (h/a {:href (str (p/bind-attr!! :baseUrl) "users/Sam")} "Sam")))

            (h/section {:data-route "user-info" :tabindex "-1"}
              (paper/material {:elevation "-1"}
                (h/h1 :.page-title {:tabindex "-1"} "User: " (p/bind!! :params.name))
                (h/div "This is " (p/bind!! :params.name) "'s section")))

            (h/section {:data-route "contact" :tabindex "-1"}
              (paper/material {:elevation "1"}
                (h/h1 :.page-title {:tabindex "-1"} "Contact")
                (h/p "This is the contact section")))))))
    (paper/toast :#toast
      (h/span :.toast-hide-button {:role "button"
                                   :tabindex "0"
                                   :onclick "app.$.toast.hide()"}
              "Ok")))
    miraj.polymer.protocols/Lifecycle
  (ready [] (this-as this (shell/ready this))))
  
  ;;   <!-- Uncomment next block to enable Service Worker support (1/2) -->
  ;;   <!--
  ;;   (paper/toast id="caching-complete"
  ;;                duration="6000"
  ;;                text="Caching complete! This app will work offline.">
  ;;   </paper-toast>

  ;;   <platinum-sw-register auto-register
  ;;                         clients-claim
  ;;                         skip-waiting
  ;;                         base-uri="bower_components/platinum-sw/bootstrap"
  ;;                         on-service-worker-installed="displayInstalledToast">
  ;;     <platinum-sw-cache default-cache-strategy="fastest"
  ;;                        cache-config-file="cache-config.json">
  ;;     </platinum-sw-cache>
  ;;   </platinum-sw-register>
  ;;   -->




(def apple-meta
  #::apple{:mobile #::apple{:status-bar-style :black
                            :title "Miraj Starter Kit"}
           :touch #::h{:icons [#::h{:href "images/touch/apple-touch-icon.png"}]}})

(def ms-meta
       #::ms{:tile #::ms{:color "#3372DF"
                         :image "images/touch/ms-touch-icon-144x144-precomposed.png"}})

(def chrome-meta #::h{:web-app-capable true})

(def platforms (merge apple-meta ms-meta chrome-meta))

(miraj/defpage
  "Miraj Starter Kit 1.3.0"
  (:require [miraj.polymer.iron :as iron :refer [pages selector]]
            [miraj.polymer.paper :as paper :refer [drawer-panel
                                                   icon-button
                                                   item
                                                   card
                                                   material
                                                   menu
                                                   scroll-header-panel
                                                   toast
                                                   toolbar]])

  (:js ["scripts/app.js"])

  #::h{:title "Miraj Starter Kit 1.3.0"
       :description "This page demonstrates of a simple miraj webpage."
       :charset "utf-8"
       :viewport {::h/width :device-width
                  ::h/scale {::h/min 0.5 ::h/max 2 ::h/initial 1}
                  ::h/user-scalable true}
       :platform platforms

       ::h/icons [{::h/href "images/touch/chrome-touch-icon-192x192.png"
                   ::h/sizes #{[192 192]}
                   ::h/type "icon"}]

       ::h/pragma {::h/x-ua-compatible "IE=edge,chrome=1"
                   ::h/default-style "main-style"
                   ::h/pics-label "pics label"}}

  ;; (:js ["main.js"])

  (:body :?unresolved
   (h/span :#browser-sync-binding)
   (h/template {:is "dom-bind" :id "app"}
               (paper/drawer-panel
    :#paperDrawerPanel
    ;; Drawer Scroll Header Panel
    (paper/scroll-header-panel :?drawer?fixed
        ;; Drawer Toolbar
        (paper/toolbar :#drawerToolbar (h/span :.menu-name "Menu"))

        ;; Drawer Content
        (paper/menu :.app-menu {:attr-for-selected "data-route"
                                :selected (p/bind! :route)}
          (h/a {:data-route "home" :href (p/bind!! :baseUrl)}
            (iron/icon {:icon "home"})
            (h/span "Home"))
          (h/a {:data-route "users" :href (str (p/bind!! :baseUrl)"users")}
            (iron/icon {:icon "info"})
            (h/span "Users"))

          (h/a {:data-route "contact" :href (str (p/bind!! :baseUrl) "contact")}
            (iron/icon {:icon "mail"})
            (h/span "Contact"))))

      ;; Main Area
      (paper/scroll-header-panel :?main#headerPanelMain?condenses?keep-condensed-header
        ;; Main Toolbar
        (paper/toolbar :#mainToolbar.tall
          (paper/icon-button :#paperToggle {:icon "menu"} :?paper-drawer-toggle)
          (h/span :.space)

          ;; Toolbar icons
          (paper/icon-button {:icon "refresh"})
          (paper/icon-button {:icon "search"})

          ;; Application name
          (h/div :.middle.middle-container
            (h/div :.app-name "Miraj Starter Kit"))

          ;; Application sub title
          (h/div :.bottom.bottom-container
            (h/div :.bottom-title "The future of the web today")))

        ;; Main Content
        (h/div :.content

           (iron/pages {:attr-for-selected "data-route" :selected (p/bind!! :route)}
            (h/section {:data-route "home" :tabindex "-1"}
              (paper/material {:elevation "1"}
                (gadgets/greeting)

                (h/p :.subhead "You now have:")
                (gadgets/list)

                (h/p
                "Looking for more Web App layouts? Check out our "
(h/a {:href "https://github.com/PolymerElements/app-layout-templates"} "layouts")
" collection. You can also "
(h/a {:href "http://polymerelements.github.io/app-layout-templates/"} "preview")
" them live."))

              (paper/material {:elevation "1"}
                (h/p "This is another card."))

              (paper/material {:elevation "1"}
                (h/h2 :#license "License")
                (h/p "Everything in this repo is BSD style license unless otherwise specified.")
                (h/p "Copyright (c) 2015 The Polymer Authors. All rights reserved.")
                (h/p "Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:")
                (h/ul
                (h/li "Redistributions of source code must retain the above copyright
                notice, this list of conditions and the following disclaimer.")
                (h/li "Redistributions in binary form must reproduce the above
                copyright notice, this list of conditions and the following disclaimer
                in the documentation and/or other materials provided with the
                distribution.")
                (h/li "Neither the name of Google Inc. nor the names of its
                contributors may be used to endorse or promote products derived from
                this software without specific prior written permission."))

                (h/p "THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS &quot;AS IS&quot; AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.")))

            (h/section {:data-route "users" :tabindex "-1"}
              (paper/material {:elevation "1"}
                (h/h1 :.page-title {:tabindex "-1"} "Users")
                (h/p "This is the users section")
                (h/a {:href (str (p/bind-attr!! :baseUrl) "users/Addy")} "Addy")
                (h/a {:href (str (p/bind-attr!! :baseUrl) "users/Rob")} "Rob")
                (h/a {:href (str (p/bind-attr!! :baseUrl) "users/Chuck")} "Chuck")
                (h/a {:href (str (p/bind-attr!! :baseUrl) "users/Sam")} "Sam")))

            (h/section {:data-route "user-info" :tabindex "-1"}
              (paper/material {:elevation "-1"}
                (h/h1 :.page-title {:tabindex "-1"} "User: " (p/bind!! :params.name))
                (h/div "This is " (p/bind!! :params.name) "'s section")))

            (h/section {:data-route "contact" :tabindex "-1"}
              (paper/material {:elevation "1"}
                (h/h1 :.page-title {:tabindex "-1"} "Contact")
                (h/p "This is the contact section")))))))
               (paper/toast :#toast
      (h/span :.toast-hide-button {:role "button"
                                   :tabindex "0"
                                   :onclick "app.$.toast.hide()"}
              "Ok")))
 
   ;; (h/div :#app
   ;;        (shell))
   ;; (widgets/greeting)
   ;; (widgets/list)
   ))

;; (println "loaded index")
