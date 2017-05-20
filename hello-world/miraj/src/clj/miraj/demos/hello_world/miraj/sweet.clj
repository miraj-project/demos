(ns miraj.demos.hello-world.miraj.sweet
  (:refer-clojure :exclude [map meta time])
  (:require [miraj.core :as miraj]
            [miraj.html :as h :refer :all :exclude [meta]]
            [miraj.co-dom :as codom :refer [pprint serialize]]
            :reload))

(miraj/defpage
  "Hello, Sweet Miraj World"

  ;; external deps moved to deps.edn

  {::h/title "Sweet Hello World"
   ::h/description "This page demonstrates of a simple miraj webpage."
   ::h/charset "utf-8"
   ::h/viewport {::h/width :device-width
                 ::h/scale {::h/min 0.5 ::h/max 2 ::h/initial 1}
                 ::h/user-scalable true}
   ::h/pragma {::h/x-ua-compatible "IE=edge,chrome=1"
               ::h/default-style "main-style"
               ::h/pics-label "pics label"}
   ::h/icons [{::h/href "favicon.png" ::h/sizes #{[16 16]} ::h/type "image/png"}
              {::h/href "mac.icns" ::h/sizes #{[128 128] [512 512] [8192 8192] [32768 32768]}}]}

  (:body
   (h1 "Hello Sweet Miraj World!")
   (h2 :#h {:miraj.style/hover {:background-color "#A5D6A7"}} "(sweet)")
   (div :#main
        (span :?centered
              (button {:onclick "handle_click()"} "click me")))))

