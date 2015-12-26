(ns orislib.stat-test
  (:require [clojure.test :as t])
  (:use [orislib.stat]))

(t/deftest test-average
  (t/are [s result] (= (average s) result)
         [1] 1
         [1 3] 2
         [-1 2 3 3 1 4 -23] -11/7))

(t/deftest test-percentile
  (t/are [s pth-percentile result] (= (percentile s pth-percentile) result)
         [10 20 30] 0 10
         [10 20 30] 1 10
         [10 20 30] 50 20
         [10 20 30] 100 30
         (range 1 11) 50 5
         (range 1 11) 51 6
         nil 69 nil
         ))

(t/deftest test-variance
  (t/is (= (variance [1 2 -1 5]) 75/16)))

(t/deftest test-std-dev
  (t/are [s result epsilon] (<= (- result epsilon) (std-dev s) (+ result epsilon))
         [1 2 -1 5] 2.165 0.001
         [2 4 4 4 5 5 7 9] 2.0 0))
