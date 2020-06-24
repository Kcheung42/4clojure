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

(defn f
  [coll]
  (vals
    (reduce (fn [acc, x]
              (let [ttype (type x)]
                (assoc acc ttype (conj (get acc ttype []) x))))
      {}
      coll)))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
  (vector
    (= (set (f [1 :a 2 :b 3 :c]))
      #{[1 2 3] [:a :b :c]})

    (= (set (f [:a "foo"  "bar" :b]))
      #{[:a :b] ["foo" "bar"]})

    (= (set (f [[1 2] :a [3 4] 5 6 :b]))
      #{[[1 2] [3 4]] [:a :b] [5 6]})))
