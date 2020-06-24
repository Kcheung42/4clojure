(ns four-clojure.easy.081-set-intersection)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; Write a function which returns the intersection of two sets. The intersection
;; is the sub-set of items that each set has in common.

;; Special Restriction: intersection

;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(comment
  (defn intersection-
    [s1 s2]
    (reduce (fn [acc x]
              (if (contains? s1 x)
                (conj acc x)
                acc))
      #{}
      s2)))


(comment
  (defn intersection-
    [a b]
    (set (filter #(contains? b %) a))))


(defn intersection-
  [a b]
  (comp set filter))
;; This works because a set can be used as the function predicate in `filter`


;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
  (vector
    (= (intersection- #{0 1 2 3} #{2 3 4 5}) #{2 3})
    (= (intersection- #{0 1 2} #{3 4 5}) #{})
    (= (intersection- #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d})
    ))
