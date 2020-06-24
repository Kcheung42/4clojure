(ns four-clojure.medium.054-partition-a-sequence)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:



;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(comment
  (defn partition-
    [n coll]
    (when-let [s (seq coll)]
      (let [seg (take n s)]
        (when (= (count seg) n)
          (cons seg (partition- n (nthrest coll n))))))))

;; more concise
(defn partition-
  [n coll]
  (when (and (seq coll) (>= (count coll) n))
    (cons (take n coll) (partition- n (drop n coll) ))))


;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (partition- 3 (range 9))
             '((0 1 2) (3 4 5) (6 7 8)))

          (= (partition- 2 (range 8))
             '((0 1) (2 3) (4 5) (6 7)))

          (= (partition- 3 (range 8))
             '((0 1 2) (3 4 5)))

          (= (partition- 3 (range 8))
             '((0 1 2) (3 4 5)))
          ))
