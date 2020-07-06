(ns four-clojure.medium.055-count-occurrances)
;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

#_(
   Write a function which returns a map containing the number of occurences of
   each distinct item in a sequence.
   )


;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(defn f
  [coll]
  (reduce (fn [m x]
            (if (contains? m x)
              (assoc m x (inc (get m x)))
              (assoc m x 1)))
          {}
          coll))

(defn f
  [coll]
  (reduce (fn [counts x]
            (assoc counts x (inc (get counts x 0))))
          {}
          coll))

(defn f
  [coll]
  (reduce (fn [m x]
            (update-in m [x] (fn [v] (inc (or v 0)))))
          {}
          coll))

(defn f
  [coll]
  (reduce #(update-in %1 [%2] (fnil inc 0)) {} coll))


;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (f [1 1 2 3 2 1 1])
             {1 4, 2 2, 3 1})
          (= (f [:b :a :b :a :b])
             {:a 2, :b 3})
          (= (f '([1 2] [1 3] [1 3]))
             {[1 2] 1, [1 3] 2})))
