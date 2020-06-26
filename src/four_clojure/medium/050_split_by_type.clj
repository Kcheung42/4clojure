(ns four-clojure.split-by-type)
;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags: #medium

#_(
   Write a function which takes a sequence consisting of items with different types
   and splits them up into a set of homogeneous sub-sequences. The internal
   order of each sub-sequence should be maintained, but the sub-sequences
   themselves can be returned in any order (this is why 'set' is used in the test cases).
   )


;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

#_(
   h[type] => [v1, v2 ...]
   )

(comment
  (defn split-by-type
    [coll]
    (vals
      (reduce (fn [acc, x]
                (let [ttype (type x)]
                  (assoc acc ttype (conj (get acc ttype []) x))))
              {}
              coll))))

;; why does this overflow when coll is (range 4000)
(comment
  (defn split-by-type
    [coll]
    (vals
      (apply merge-with into (map (fn [x] {(type x) [x]}) coll)))))


;; more concise
(defn split-by-type
  [coll]
  (vals (group-by type coll)))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (set (split-by-type [1 :a 2 :b 3 :c]))
             #{[1 2 3] [:a :b :c]})

          (= (set (split-by-type [:a "foo"  "bar" :b]))
             #{[:a :b] ["foo" "bar"]})

          (= (set (split-by-type [[1 2] :a [3 4] 5 6 :b]))
             #{[[1 2] [3 4]] [:a :b] [5 6]})))
