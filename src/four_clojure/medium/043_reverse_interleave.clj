(ns four-clojure.043-reverse-interleave)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; Write a function which reverses the interleave process into x number of subsequences.

;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(defn reverse_interleave
  [coll n]
  (let [part (partition n coll)]
    (for [i (range n)]
      (map #(nth % i) part))))

(defn reverse_interleave
  [coll n]
  (for [i (range n)]
    (take-nth n (drop i coll))))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
  (vector
    (= (reverse_interleave [1 2 3 4 5 6] 2)
      '((1 3 5) (2 4 6)))

    (= (reverse_interleave (range 9) 3)
      '((0 3 6) (1 4 7) (2 5 8)))

    (= (reverse_interleave (range 10) 5)
      '((0 5) (1 6) (2 7) (3 8) (4 9)))

    (= (reverse_interleave (range 10) 5)
      '((0 5) (1 6) (2 7) (3 8) (4 9)))
    ))
