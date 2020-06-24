(ns four-clojure.medium.044-rotate-sequence)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; Write a function which can rotate a sequence in either direction.

;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

#_(High Level:
        rotate index = 2

        1 2 3 4 5

        5 4 3 2 1

        rev((5,4)) + rev(3 2 1)
        )

(defn rot_index
  [x n]
  (let [i (mod x n)]
    (if (pos? i)
      (- n i)
      i)))

(defn f
  [x coll]
  (let [n     (count coll)
        i     (rot_index x n)
        rcoll (reverse coll)]
    (concat (reverse (take i rcoll))
            (reverse (drop i rcoll)))))


;; more concise
(defn f
  [x coll]
  (let [n (count coll)]
    (take n (drop (mod x n) (cycle coll)))))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (f 2 [1 2 3 4 5]) '(3 4 5 1 2))
          (= (f -2 [1 2 3 4 5]) '(4 5 1 2 3))
          (= (f 6 [1 2 3 4 5]) '(2 3 4 5 1))
          (= (f 1 '(:a :b :c)) '(:b :c :a))
          (= (f -4 '(:a :b :c)) '(:c :a :b))
          ))
