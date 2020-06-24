(ns four-clojure.easy.063-group-a-sequence)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:


;; Given a function f and a sequence s, write a function which returns a map.
;; The keys should be the values of f applied to each item in s. The value at
;; each key should be a vector of corresponding items in the order they appear in s.

;; Special Restrictions: group-by


;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(comment
  (defn group-by-
    [f coll]
    (reduce (fn [acc x]
              (assoc acc (f x) (conj (get acc (f x) []) x)))
      {}
      coll)))

(comment
  (defn group-by-
    [f coll]
    (apply merge-with into (for [v coll] {(f v) [v]}))))


(comment
  (defn group-by-
    [f coll]
    (apply merge-with into (map (fn [v] {(f v) [v]}) coll))))

;; which is more readable map? or for?


(defn group-by-
  [f coll]
  (group-by f coll))




;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------


(every? true?
  (vector
    (= (group-by- #(> % 5) [1 3 6 8])
      {false [1 3], true [6 8]})

    (= (group-by- #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
      {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})

    (= (group-by- count [[1] [1 2] [3] [1 2 3] [2 3]])
      {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})
    ))
