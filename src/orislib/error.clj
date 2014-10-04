(ns orislib.error
  (:use clojure.algo.monads))

(defmonad maybe-err
  [m-result (fn [v] {:ret v})
   m-bind (fn [v f] (if (:err v)
                      v
                      (f (:ret v))))])

(defn nil2err [f str]
  (fn [& args]
    (if-let [ret (apply f args)]
      {:ret ret}
      {:err str})))
