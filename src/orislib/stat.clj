(ns orislib.stat)

(defn average [s]
  "The average as a rational number."
  (/ (reduce + s) (count s)))

(defn percentile [s n]
  (cond
    (empty? s) nil
    (zero? n) (first s)
    :default
    (let [cnt (count s)
          which (->
                 (/ n 100)
                 (* cnt)
                 double
                 Math/ceil
                 int)]
      (nth (sort s) (dec which)))))

(defn median [s]
  (percentile s 50))

(defn variance [s]
  "Return variance as a rational number."
  (let [avg (average s)]
    (->> s
     (map #(- avg %))
     (map #(* % %))
     average )))

(defn std-dev [s]
  "Standard deviation as a floating point number."
  (Math/sqrt (variance s)))
