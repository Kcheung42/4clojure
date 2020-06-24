(ns four-clojure.easy.153-pairwise-disjoint-set)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:



;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

;; count the number of elements in all sets
;; reduce with union over all sets
;; Both number should be equal if

(comment
  (defn pairwise-disjoint
    [coll]
    (= (apply + (map count coll))
      (count (reduce clojure.set/union coll)))))

(defn pairwise-disjoint
  [coll]
  (apply distinct? (apply concat coll)))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
  (vector
    (= (pairwise-disjoint #{#{\U} #{\s} #{\e \R \E} #{\P \L} #{\.}})
      true)

    (= (pairwise-disjoint #{#{:a :b :c :d :e}
                            #{:a :b :c :d}
                            #{:a :b :c}
                            #{:a :b}
                            #{:a}})
      false)

    (= (pairwise-disjoint #{#{[1 2 3] [4 5]}
                            #{[1 2] [3 4 5]}
                            #{[1] [2] 3 4 5}
                            #{1 2 [3 4] [5]}})
      true)

    (= (pairwise-disjoint #{#{'a 'b}
                            #{'c 'd 'e}
                            #{'f 'g 'h 'i}
                            #{''a ''c ''f}})
      true)
    (= (pairwise-disjoint #{#{'(:x :y :z) '(:x :y) '(:z) '()}
                            #{#{:x :y :z} #{:x :y} #{:z} #{}}
                            #{'[:x :y :z] [:x :y] [:z] [] {}}})
      false)

    (= (pairwise-disjoint #{#{(= "true") false}
                            #{:yes :no}
                            #{(class 1) 0}
                            #{(symbol "true") 'false}
                            #{(keyword "yes") ::no}
                            #{(class '1) (int \0)}})
      false)

    (= (pairwise-disjoint #{#{distinct?}
                            #{#(-> %) #(-> %)}
                            #{#(-> %) #(-> %) #(-> %)}
                            #{#(-> %) #(-> %) #(-> %)}})
      true)

    (= (pairwise-disjoint #{#{(#(-> *)) + (quote mapcat) #_ nil}
                            #{'+ '* mapcat (comment mapcat)}
                            #{(do) set contains? nil?}
                            #{, , , #_, , empty?}})
      false)
    ))
