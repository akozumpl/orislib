(ns orislib.format)

(defn seconds2str
  ([seconds] (seconds2str seconds false))
  ([seconds show-positive]
    (let [plus-sign (if show-positive "+" " ")
          sign (cond (< seconds 0) "-"
                     (> seconds 0) plus-sign
                     :default " ")
          seconds (Math/abs seconds)
          min (int (/ seconds 60))
          seconds (mod seconds 60)]
      (format "%s%d:%02d" sign min seconds))))
