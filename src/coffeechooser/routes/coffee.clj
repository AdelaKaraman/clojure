(ns coffeechooser.routes.coffee
  (:require [compojure.core :refer :all]
            [coffeechooser.layout :as layout]
            [coffeechooser.util :as util])
  (:use coffeechooser.db.core))


(defn coffee-page []
  (layout/render
    "home.html" {:content (apply str(coffees))}))

(defn single-coffee-page [id]
  (layout/render
    "home.html" {:content (apply str(single-coffee id))}))

(defn chooser [coffeetype
               milk
               sugar
               additions]
  (coffee-choose  (+ (read-string coffeetype)
                  (read-string milk)
                  (read-string sugar)
                  (read-string additions))))

(defn adder [coffeename
             coffeetype
             milk
             sugar
             additions]
  (coffee-add (+ (read-string coffeetype)
              (read-string milk)
              (read-string sugar)
              (read-string additions))
    coffeetype
    milk
    sugar
    additions
    coffeename))

(defroutes coffee-routes
  (GET "/coffee" [] (coffee-page))      ;gets all coffee instances
  (GET "/coffee/:id" [id] (single-coffee-page id))      ;gets single coffee
  (POST "/choose" [coffeetype
                   milk
                   sugar
                   additions] (chooser coffeetype milk sugar additions))) ;actually chooses the coffee
  (DELETE "/coffee/:id" [id] (coffee-destroyer id)) ;deletes a coffee by id

  (POST "/add" [coffeename
                coffeetype
                milk
                sugar
                additions] (adder coffeename coffeetype milk sugar additions))  ;sends the request for the creation of a
                                                                                ;new coffee