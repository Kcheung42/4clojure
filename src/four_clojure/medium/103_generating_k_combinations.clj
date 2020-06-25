(ns four-clojure.medium.103-generating-k-combinations)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; Given a sequence S consisting of n elements generate all k-combinations of
;; S, i. e. generate all possible sets consisting of k distinct elements taken from
;; S. The number of k-combinations for a sequence is equal to the binomial coefficient.

;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------


(defn k-combinations
  [k coll]
  (cond
    (zero? k)
    #{#{}} ;; nested because map in #1

    (empty? coll)
    #{}

    :else
    (set (clojure.set/join
           (map #(conj % (first coll)) (k-combinations (dec k)(rest coll))) ;;#1
           (k-combinations k (rest coll)))))) ;;#2


;; NOTE
;; DFS algorithm using recursion.
;; At each step 2 branches are made
;; #1 - Considers first of the working coll
;;       - Conj's the first to all subproblems (i.e. (k-combinations (dec k)(rest coll)))
;; #2 - Skips the first of the working coll
;; Finally join the results of both branches and return to parent

;; Child k-comb will always return a set to Parent
;; Even though (map #(conj % 1) #{}) => ()
;; This satisfies the case (k-combinations 3 #{1}) => #{}

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (k-combinations 1 #{4 5 6})
             #{#{4} #{5} #{6}})

          (= (k-combinations 10 #{4 5 6})
             #{})
          (= (k-combinations 2 #{0 1 2})
             #{#{0 1} #{0 2} #{1 2}})

          (= (k-combinations 3 #{0 1 2 3 4})
             #{#{0 1 2} #{0 1 3} #{0 1 4} #{0 2 3} #{0 2 4}
               #{0 3 4} #{1 2 3} #{1 2 4} #{1 3 4} #{2 3 4}})

          (= (k-combinations 4 #{[1 2 3] :a "abc" "efg"})
             #{#{[1 2 3] :a "abc" "efg"}})

          (= (k-combinations 2 #{[1 2 3] :a "abc" "efg"})
             #{#{[1 2 3] :a} #{[1 2 3] "abc"} #{[1 2 3] "efg"}
               #{:a "abc"} #{:a "efg"} #{"abc" "efg"}}
             ))
