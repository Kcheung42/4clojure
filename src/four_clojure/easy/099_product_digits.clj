(ns four-clojure.easy.099-product-digits
  (:require [clojure.string :as str]))

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:


;; Write a function which multiplies two numbers and returns the result as a
;; sequence of its digits.

;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(comment
  (defn f
    [x y]
    (map #(Character/digit % 10) (str (* x y)))))


(comment
  (defn f
    [x y]
    (mapv #(Integer/parseInt (str %)) (str (* x y)))))


(defn f
  [x y]
  (->> (* x y)
       str
       (map #(- (int %) (int \0)))))


;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (f 1 1) [1])
          (= (f 99 9) [8 9 1])
          (= (f 999 99) [9 8 9 0 1])
          ))
