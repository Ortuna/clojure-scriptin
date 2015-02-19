(ns west.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found resources]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [selmer.parser :refer [render-file]]
            [prone.middleware :refer [wrap-exceptions]]
            [environ.core :refer [env]]))

(def mem (atom 0))

(defroutes routes
  (GET "/" [] (render-file "templates/index.html" {:dev (env :dev?)}))
  (GET "/save" [] 
    (swap! mem inc)
    (str @mem))
  (resources "/")
  (not-found "Not Found"))

(def app
  (let [handler (wrap-defaults routes site-defaults)]
    (if (env :dev?) (wrap-exceptions handler) handler)))
