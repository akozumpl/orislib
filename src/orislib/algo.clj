(ns orislib.algo)

(defn percentage [part total]
  (-> ( / part total)
      ( * 100)
      double))
