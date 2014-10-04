(ns orislib.calendar
  (:import (java.util GregorianCalendar))
  (:import (java.text SimpleDateFormat)))

(def ^:private date-format "yyyy-MM-dd")
(def ^:private date-time-format "yyyy-MM-dd HH:mm:ss")

(defn add-days [cal days]
  (let [new-cal (.clone cal)]
    (.add new-cal GregorianCalendar/DAY_OF_MONTH days)
    new-cal))

(defn cal-sql [cal]
  (let [millis (.. cal getTime getTime)]
    (java.sql.Timestamp. millis)))

(defn cal-str [cal & {:keys [with-time?] :or {with-time? true}}]
  (let [format (SimpleDateFormat. (if with-time? date-time-format date-format))
        time (.getTime cal)]
    (.format format time)))

(defn date-cal [date]
  (let [cal (GregorianCalendar.)]
    (.setTime cal date)
    cal))

(defn diff-cals [cal1 cal2]
  (let [ms1 (.getTimeInMillis cal1)
        ms2 (.getTimeInMillis cal2)]
    (unchecked-divide-int (- ms1 ms2) 1000)))

(defn now []
  (GregorianCalendar.))

(defn str-cal [str & {:keys [with-time?] :or {with-time? true}}]
  (let [format (SimpleDateFormat. (if with-time? date-time-format date-format))
        date (.parse format str)]
  (.getCalendar format)))

(defn str-sql [str & {:keys [with-time?] :or {with-time? true}}]
  (-> str (str-cal :with-time? with-time?) cal-sql))
