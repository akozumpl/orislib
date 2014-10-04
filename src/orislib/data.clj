(ns orislib.data
  (:require [clojure.string :as string])
  (:require [orislib.db :as db]))

(def START-CODE 0)
(def FINISH-CODE 10000)

(defn races []
  (let [q (str "select id, oris_id, name, time, place from race where visible "
               "order by time desc;")]
    (db/get q)))

(def ^{:private true} entry-select
  (str "select entry.id, entry.race, entry.class from "
       "entry join competitor on (entry.competitor = competitor.id) "
       "join race on (entry.race = race.id) "
       "where competitor.registration = ? and race.oris_id = ?"))

(defn reg2entry [registration oris-id]
  (first (db/get entry-select (string/upper-case registration) oris-id)))
