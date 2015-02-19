(ns west.timer
    (:require 
      [reagent.core :as reagent :refer [atom]]
      [ajax.core :refer [GET POST]]))

(defonce state (atom 0))

(defn post-and-update []
  (GET "/save" { :handler #(reset! state %) }))

(defn div-element []
  [:div "Remote value is: " @state])

(defn display []
  (fn []
    (reagent/create-class 
      {:render div-element
       :component-did-mount post-and-update})))

;(post-and-update)
