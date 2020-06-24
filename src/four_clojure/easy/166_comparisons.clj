(ns four-clojure.easy.166-comparisons)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; For any orderable data type it's possible to derive all of the basic
;; comparison operations (<, ≤, =, ≠, ≥, and >) from a single operation
;; (any operator but = or ≠ will work). Write a function that takes three
;; arguments, a less than operator for the data and two items to compare. The
;; function should return a keyword describing the relationship between the two
;; items. The keywords for the relationship between x and y are as follows:

;; x = y → :eq
;; x > y → :gt
;; x < y → :lt



;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(defn f
  [f a b]
  (cond
    (f a b) :lt
    (f b a) :gt
    :else   :eq))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= :gt (f < 5 1))
          (= :eq (f (fn [x y] (< (count x) (count y))) "pear" "plum"))
          (= :lt (f (fn [x y] (< (mod x 5) (mod y 5))) 21 3))
          (= :gt (f > 0 2))
          ))
