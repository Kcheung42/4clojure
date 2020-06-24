(ns four-clojure.easy.062-re-implement-iterate)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; Given a side-effect free function f and an initial value x write a function
;; which returns an infinite lazy sequence of x, (f x), (f (f x)), (f (f (f x))), etc.

;; Special Restrictions: `iterate`

;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(defn iterate-
  [f x]
  (cons
    x
    (lazy-seq
      (iterate- f (f x)))))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (take 5 (iterate- #(* 2 %) 1))
             [1 2 4 8 16])

          (= (take 100 (iterate- inc 0))
             (take 100 (range)))

          (= (take 9 (iterate- #(inc (mod % 3)) 1))
             (take 9 (cycle [1 2 3])))
          ))
