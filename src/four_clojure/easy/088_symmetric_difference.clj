(ns four-clojure.easy.088-symmetric-difference)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; Write a function which returns the symmetric difference of two sets. The symmetric
;; difference is the set of items belonging to one but not both of the two sets.

;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(comment
  (defn sym-dif
    [a b]
    (reduce (fn [acc x]
              (if (contains? acc x)
                (disj acc x)
                (conj acc x)))
      a
      b)))

;;NOTE Remove is the opposite of filter
(defn sym-dif
  [a b]
  (set (concat (remove a b) (remove b a))))

;; more efficient
(defn sym-dif
  [a b]
  (set (reduce conj (remove a b) (remove b a))))

;; replacing remove to use only filter
(defn sym-dif
  [a b]
  (set (reduce conj
         (filter (complement a) b)
         (filter (complement b) a))))


;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
  (vector
    (= (sym-dif #{1 2 3 4 5 6} #{1 3 5 7}) #{2 4 6 7})
    (= (sym-dif #{:a :b :c} #{}) #{:a :b :c})
    (= (sym-dif #{} #{4 5 6}) #{4 5 6})
    (= (sym-dif #{[1 2] [2 3]} #{[2 3] [3 4]}) #{[1 2] [3 4]})))
