(ns miraj.demos.hello-world.miraj.bitter
  (:require [miraj.core :as miraj]
            [miraj.html :as h :refer :all :exclude [meta]]
            [miraj.co-dom :as codom :refer [pprint serialize]]
            :reload))

(miraj/defpage
  "Hello World - Miraj (bitter) Demo Page"

  ;; embedded dep refs - sweeter is to use deps.edn
  (:css
   ;; ;; inlined css
   ;; "button { background:green; width: 50%; margin: 16px;}"

   ;; ;; inlined, custom style
   ;; {:custom "button { color:green; width: 50%; margin: 16px;}"}

   ;; raw path syntax
   ;; ["/css/bitter.css" "/css/bitter2.css"]

   ;; style modules with custom styles (:custom required for main doc css)
   ;; [styles.modules :custom [foo bar]]

   ;; we can add inline csss to a module reference:
   ;; [styles.modules :custom [[foo "button {width:50%;height:1in;background:green;}"]
   ;;                          bar]]

   ;; no :custom needed for defcomponent - won't work for defpage
   ;; [styles.modules foo bar] ;; link /styles/modules.html, include modules foo and bar

   ;; to put <style> in html files without modules, use :imports instead of :css

   ;; map syntax: direct encoding of link attribs (except :rel, defaults to "stylesheet"
   ;; {:href "https://unpkg.com/purecss@0.6.2/build/pure-min.css"
   ;;  :integrity "sha384-UQiGfs9ICog+LwheBSRCt1o5cbyKIHbwjWscjemyBMT9YCUMZffs6UqUTd0hObXD"
   ;;  :crossorigin "anonymous"}
   ;; {:href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"}
   )

  (:imports ["/imports/bitter.html"])

  (:js  ["/js/bitter.js"])

  ;; html metadata
  {::h/title"Miraj Demo: bitter hello-world"
   ::h/description "This page demonstrates of a simple miraj webpage."
   ::h/charset "utf-8"
   ::h/viewport {::h/width :device-width
                 ::h/scale {::h/min 0.5
                            ::h/max 2
                            ::h/initial 1}
                 ::h/user-scalable true}
   ::h/pragma {::h/x-ua-compatible "IE=edge,chrome=1"
               ;; :content-language "en"   ;; obsolete
               ;; ::h/content-security-policy "default-src https://cdn.example.net; child-src 'none'; object-src 'none'"
               ;; :content-type "foo"   ;; obsolete
               ::h/default-style "main-style"
               ;; ::h/refresh "20; URL=page4.html"
               ;; :set-cookie "foo"  ;; obsolete
               ::h/pics-label "pics label"
               }

   ::h/icons [{::h/href "favicon.png" ::h/sizes #{[16 16]} ::h/type "image/png"}
              {::h/href "mac.icns" ::h/sizes #{[128 128] [512 512] [8192 8192] [32768 32768]}}]
   }


  (:body
   (h1 "Hello, Bitter Miraj World!")
   (h2 :#h {:miraj.style/hover {:background-color "#03A9F4"}} "(bitter)")
   (div :#main
        (span :!centered
              (button {:onclick "handle_click()"} "click me")))))
