(ns four-clojure.reverse)

(defn my-reverse
  [coll]
  (loop [[r & more :as all] (seq coll)
         acc                '()]
    (if all
      (recur more (cons r acc))
      acc)))

(defn my-reverse
  "Reverse a String"
  [coll]
  (reduce conj () coll))
