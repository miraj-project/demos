(ns index
  ;; (:refer-clojure :exclude [list])
  (:require [miraj.core :as miraj :refer [defcomponent]]
            [miraj.html :as h]
            [miraj.html.x.apple :as apple]
            [miraj.html.x.ms :as ms]
            [psk.gadgets :as widgets :refer :all]))

            ;; for testing only:
            ;; [miraj.core :as wc]
            ;; [miraj.co-dom :as x]
            ;; :reload))
        
;;(-> shell var meta :miraj/miraj)

;(doseq [r (filter #(-> % second meta :miraj/miraj) (ns-map *ns*))]
;  (println r))

;; (println "loading index")

(defcomponent shell :html app-shell
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
 
  ;; <template is="dom-bind" id="app">
  (:codom
   (paper/card (h/div "shell with card")))
  ;;   <paper-drawer-panel id="paperDrawerPanel">
  ;;     <!-- Drawer Scroll Header Panel -->
  ;;     <paper-scroll-header-panel drawer fixed>

  ;;       <!-- Drawer Toolbar -->
  ;;       <paper-toolbar id="drawerToolbar">
  ;;         <span class="menu-name">Menu</span>
  ;;       </paper-toolbar>

  ;;       <!-- Drawer Content -->
  ;;       <paper-menu class="app-menu" attr-for-selected="data-route" selected="[[route]]">
  ;;         <a data-route="home" href="{{baseUrl}}">
  ;;           <iron-icon icon="home"></iron-icon>
  ;;           <span>Home</span>
  ;;         </a>

  ;;         <a data-route="users" href="{{baseUrl}}users">
  ;;           <iron-icon icon="info"></iron-icon>
  ;;           <span>Users</span>
  ;;         </a>

  ;;         <a data-route="contact" href="{{baseUrl}}contact">
  ;;           <iron-icon icon="mail"></iron-icon>
  ;;           <span>Contact</span>
  ;;         </a>
  ;;       </paper-menu>
  ;;     </paper-scroll-header-panel>

  ;;     <!-- Main Area -->
  ;;     <paper-scroll-header-panel main id="headerPanelMain" condenses keep-condensed-header>
  ;;       <!-- Main Toolbar -->
  ;;       <paper-toolbar id="mainToolbar" class="tall">
  ;;         <paper-icon-button id="paperToggle" icon="menu" paper-drawer-toggle></paper-icon-button>

  ;;         <span class="space"></span>

  ;;         <!-- Toolbar icons -->
  ;;         <paper-icon-button icon="refresh"></paper-icon-button>
  ;;         <paper-icon-button icon="search"></paper-icon-button>

  ;;         <!-- Application name -->
  ;;         <div class="middle middle-container">
  ;;           <div class="app-name">Polymer Starter Kit</div>
  ;;         </div>

  ;;         <!-- Application sub title -->
  ;;         <div class="bottom bottom-container">
  ;;           <div class="bottom-title">The future of the web today</div>
  ;;         </div>
  ;;       </paper-toolbar>

  ;;       <!-- Main Content -->
  ;;       <div class="content">
  ;;         <iron-pages attr-for-selected="data-route" selected="{{route}}">
  ;;           <section data-route="home" tabindex="-1">
  ;;             <paper-material elevation="1">
  ;;               <my-greeting></my-greeting>

  ;;               <p class="subhead">You now have:</p>
  ;;               <my-list></my-list>

  ;;               <p>Looking for more Web App layouts? Check out our <a href="https://github.com/PolymerElements/app-layout-templates">layouts</a> collection. You can also <a href="http://polymerelements.github.io/app-layout-templates/">preview</a> them live.</p>
  ;;             </paper-material>

  ;;             <paper-material elevation="1">
  ;;               <p>This is another card.</p>
  ;;             </paper-material>

  ;;             <paper-material elevation="1">
  ;;               <h2 id="license">License</h2>
  ;;               <p>Everything in this repo is BSD style license unless otherwise specified.</p>
  ;;               <p>Copyright (c) 2015 The Polymer Authors. All rights reserved.</p>
  ;;               <p>Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:</p>
  ;;               <ul>
  ;;               <li>Redistributions of source code must retain the above copyright
  ;;               notice, this list of conditions and the following disclaimer.</li>
  ;;               <li>Redistributions in binary form must reproduce the above
  ;;               copyright notice, this list of conditions and the following disclaimer
  ;;               in the documentation and/or other materials provided with the
  ;;               distribution.</li>
  ;;               <li>Neither the name of Google Inc. nor the names of its
  ;;               contributors may be used to endorse or promote products derived from
  ;;               this software without specific prior written permission.</li>
  ;;               </ul>
  ;;               <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS &quot;AS IS&quot; AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.</p>
  ;;             </paper-material>
  ;;           </section>

  ;;           <section data-route="users" tabindex="-1">
  ;;             <paper-material elevation="1">
  ;;               <h1 class="page-title" tabindex="-1">Users</h1>
  ;;               <p>This is the users section</p>
  ;;               <a href$="{{baseUrl}}users/Addy">Addy</a><br>
  ;;               <a href$="{{baseUrl}}users/Rob">Rob</a><br>
  ;;               <a href$="{{baseUrl}}users/Chuck">Chuck</a><br>
  ;;               <a href$="{{baseUrl}}users/Sam">Sam</a>
  ;;             </paper-material>
  ;;           </section>

  ;;           <section data-route="user-info" tabindex="-1">
  ;;             <paper-material elevation="-1">
  ;;               <h1 class="page-title" tabindex="-1">User: {{params.name}}</h1>
  ;;               <div>This is {{params.name}}'s section</div>
  ;;             </paper-material>
  ;;           </section>

  ;;           <section data-route="contact" tabindex="-1">
  ;;             <paper-material elevation="1">
  ;;               <h1 class="page-title" tabindex="-1">Contact</h1>
  ;;               <p>This is the contact section</p>
  ;;             </paper-material>
  ;;           </section>
  ;;         </iron-pages>
  ;;       </div>
  ;;     </paper-scroll-header-panel>
  ;;   </paper-drawer-panel>

  ;;   <paper-toast id="toast">
  ;;     <span class="toast-hide-button" role="button" tabindex="0" onclick="app.$.toast.hide()">Ok</span>
  ;;   </paper-toast>

  ;;   <!-- Uncomment next block to enable Service Worker support (1/2) -->
  ;;   <!--
  ;;   <paper-toast id="caching-complete"
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

  ;; </template>
)

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
   (h/h1 "Miraj Starter Kit")
   (h/span :#browser-sync-binding)
   (shell)
   (widgets/greeting)
   (widgets/list)
   ))
 
;; (println "loaded index")
