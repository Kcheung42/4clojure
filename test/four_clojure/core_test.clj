(ns four-clojure.core-test
  (:require [clojure.test :refer :all]
            [four-clojure.core :refer :all]
            [four-clojure.easy :refer :all]))

(deftest my-flatten
  (testing "flatten"
    (let [f my-flatten]
     (is (f '(1 2 (3 4) [5 6])) '(1 4 3 4 5 6)))))
