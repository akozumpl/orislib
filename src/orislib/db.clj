(ns orislib.db
  (:import (java.io File))
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc])
  (:require [orislib.env :as env]))

(def db (:db env/env))

(defn add! [table entry]
  (-> (jdbc/insert! db table entry)
      first :id))

(defn add-all! [table entries]
  (jdbc/with-db-transaction [con db]
    (doseq [e entries]
      (jdbc/insert! con table e))))

(defn add-race! [racemap]
  (add! :race racemap))

(defn get [& qs]
  (jdbc/query db qs))

(defn update! [table set-map where-clause]
  (jdbc/update! db table set-map where-clause))

;; migrations

(def create-table-ddl jdbc/create-table-ddl)

(defn execute! [cmnds]
  (jdbc/with-db-transaction [trans db]
    (doall (mapcat #(jdbc/execute! trans [%]) cmnds))))
