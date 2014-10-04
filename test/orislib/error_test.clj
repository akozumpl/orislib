(ns orislib.error-test
  (:require [clojure.algo.monads :as m]
            [clojure.test :refer :all]
            [orislib.error :refer :all]))

(defn decreaser [a]
  (if (odd? a)
    {:err "ODD"}
    {:ret (dec a)}))

(defn increaser [a]
  (if (zero? a)
      {:err "ZERO"}
      {:ret (inc a)}))

(deftest maybe-err-test
  (is (=
       (m/domonad maybe-err
                  [x (decreaser 2)
                   y (increaser 5)]
                  (+ x y))
       {:ret 7}))
  (is (=
       (m/domonad maybe-err
                  [x (decreaser 2)
                   y (increaser 0)]
                  (+ x y))
       {:err "ZERO"})))
