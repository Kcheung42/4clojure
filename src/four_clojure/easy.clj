(ns four-clojure.easy)

(defn my-reverse
  "Reverse a String"
  [coll]
  (reduce conj () coll))

(defn my-nth
  "Nth element"
  [coll n]
  (if (> n 0)
    (recur (rest coll) (dec n))
    (first coll)))

(defn my-count
  "Count of elements in a collection"
  [coll]
  (reduce
    (fn [acc x] (when x (inc acc)))
    0
    coll))

(defn my-flatten
  [coll]
  (lazy-seq
    (when-let [s (seq coll)]
      (if (sequential? (first s))
        (concat (my-flatten (first s)) (my-flatten (rest s)))
        (cons (first s) (my-flatten (rest s)))))))
