(ns coffeechooser.routes.home
  (:require [compojure.core :refer :all]
            [coffeechooser.layout :as layout]
            [coffeechooser.util :as util]))

(defn homepagetext []
  "Choose your coffee! You are awesome because you drink coffee!")

(defn home-page []
  (layout/render
    "home.html" {:content "Choose the option below:"}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page)))
