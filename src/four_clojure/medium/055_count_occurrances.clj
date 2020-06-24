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

(defn f1
  [coll]
  (when-let [s (seq coll)]
    (reduce (fn [m x]
              (if (contains? m x)
                (assoc m x (inc (get m x)))
                (assoc m x 1)))
            {}
            s)))

(defn f
  [coll]
  (when-let [s (seq coll)]
    (reduce (fn [m x]
              (assoc m x (inc (get m x 0))))
            {}
            s)))

(defn
  [coll]
  (reduce (fn [m x]
            (update-in m [x] (fn [v] (inc (or v 0)))))
          {}
          coll))

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
