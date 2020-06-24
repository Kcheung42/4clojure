(ns four-clojure.easy.041-drop-every-nth-item)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

#_(Write a function which drops every Nth item from a sequence.)


;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(defn f
  [coll n]
  (->> (map-indexed (fn [i x]
                      (if (and (not= 0 i) (= (mod i (dec n)) 0))
                        nil
                        x))
                    coll)
       (remove nil?)))


(defn partition-all-
  [n step coll]
  (lazy-seq
    (when-let [s (seq coll)]
      (let [seg (take n s)]
        (cons seg (partition-all- n step (nthrest s step)))))))

(defn f
  [coll n]
  (apply concat (partition-all- (dec n) n coll)))

(f [1 2 3 4 5 6 7 8] 3)
;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (f [1 2 3 4 5 6 7 8] 3)
             [1 2 4 5 7 8])

          (= (f [:a :b :c :d :e :f] 2)
             [:a :c :e])

          (= (f [1 2 3 4 5 6] 4)
             [1 2 3 5 6])))
