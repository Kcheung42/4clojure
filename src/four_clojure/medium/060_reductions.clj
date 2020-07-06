(ns four-clojure.medium.060-reductions)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; Write a function which behaves like reduce, but returns each intermediate
;; value of the reduction. Your function must accept either two or three
;; arguments, and the return sequence must be lazy.

;; Special Restrictions
;; reductions

;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(defn reductions-
  ([func coll]
   (reductions- func (first coll) (rest coll)))

  ([func init [f & remain :as coll]]
   (cons init
         (lazy-seq
           (when (seq coll)
             (reductions- func (func init f) remain))))))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (take 5 (reductions- + (range)))
             [0 1 3 6 10])

          (= (reductions- conj [1] [2 3 4])
             [[1] [1 2] [1 2 3] [1 2 3 4]])

          (= (last (reductions- * 2 [3 4 5]))
             (reduce * 2 [3 4 5]) 120)
          ))
