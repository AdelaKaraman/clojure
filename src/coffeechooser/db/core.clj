(ns coffeechooser.db.core
  (:require [clojure.java.jdbc :as j]))

(def mysql-db {:subprotocol "mysql"
               :subname "//127.0.0.1:3306/coffeechooser"
               :user "root"
               :password ""})

(defn coffees []    ;gathers all the cofees from the database
  (j/query mysql-db
    ["select * from coffee"]))

(defn coffee-choose [res] ;selects a singular coffee according to the result of the algorithm
  (concat
    (j/query mysql-db
      ["select * from coffee where result = ?" (+ res 2)])
    (j/query mysql-db
      ["select * from coffee where result = ?" res])
    (j/query mysql-db
      ["select * from coffee where result = ?" (- res 2)])))

(defn coffee-add [result  ;adds a new coffee into the database
                  coffeetype
                  milk
                  sugar
                  additions
                  coffeename]
  (j/insert! mysql-db :coffee
    {:result result
     :milk milk
     :sugar sugar
     :additions additions
     :coffeename coffeename}))

(defn coffee-destroyer [id]
  (j/delete! mysql-db :coffee
    ["id = ?" id]))

(defn single-coffee [id]
  (j/query mysql-db
    ["select * from coffee where id = ?" id]))