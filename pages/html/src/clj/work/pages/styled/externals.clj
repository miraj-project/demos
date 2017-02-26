(ns work.pages.styled.externals
  (:require [miraj.core :as miraj]
            [miraj.html :as h]
            [miraj.polymer.paper :as paper :refer [button card]]
            [miraj.polymer.paper.styles :as pstyles :refer [color]]
            [miraj.polymer.iron :as iron :refer [icons icon]]
            :reload))

(miraj/defpage hello
  "Styled version of hello webpage."
  {:title "Miraj demo: imported external css."
   :description "This page demonstrates usage of external css."}

  (:css [{:href "https://unpkg.com/purecss@0.6.2/build/pure-min.css"
          :integrity "sha384-UQiGfs9ICog+LwheBSRCt1o5cbyKIHbwjWscjemyBMT9YCUMZffs6UqUTd0hObXD"
          :crossorigin "anonymous"}
         [styles.css button card hello]
         {:href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"}])

  (:body
   (h/h1 "Hello Miraj!")
   (h/div ::cards
          (paper/card {:heading "Demo: webpage with imported CSS."}
                      (h/div (iron/icon {:icon "communication:call"}))
                      (h/div {:class "card-content"}
                             (h/p "This page directly imports CSS
                             files using '<link
                             rel=\"stylesheet\"...>'.")
                             (h/p "It demonstrates importation of 3rd party resources via href.")
                             (h/p "Note that plain CSS has no effect
                             on the styling of custom properties of
                             webcomponents. Here the background-color
                             of the button webcomponent is not a
                             custom property, so plain CSS works for
                             it. But the card webcomponent uses a
                             custom property to set background color,
                             so plain CSS has no effect. See "
                                  (h/a {:href "/work/pages/styled/imported/hello.html"}
                                       "/work/pages/styled/imported/hello.html")
                                  " and "
                                  (h/a {:href "/work/pages/styled/inlined/hello.html"}
                                       "/work/pages/styled/inlined/hello.html")
                                  " for examples.")
                             (h/p "To style custom properties, you must
                             use custom styles - styles in a <style>
                             element with attribute
                             'is=\"custom-style\"'.")
                      (h/div {:class "card-actions"}
                             (paper/button ::.green {:raised nil} "Some action")))))))

