(ns four-clojure.medium.056-find-distinct-items)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

#_(Write a function which removes the duplicates from a sequence. Order of the
         items must be maintained.)


;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------


;;Idiomatic?
;; Other solutions
(comment
  (defn remove-dup-
    [coll]
    (reduce (fn [v x]
              (if ((set v) x) ;; what is the time complexity of (set v)?
                v
                (conj v x)))
            []
            coll)))

;;Idiomatic?
(comment
  (defn remove-dup-
    "Remove duplicates from a sequence. Order is maintained"
    [coll]
    (reduce #(if ((set %1) %2) %1 (conj %1 %2)) [] coll)))


(defn remove-dup-
  "Remove duplicates from a sequence. Order is maintained"
  [coll]
  (loop [[f & remain :as s] (seq coll)
         seen               #{}
         rslt               []]
    (if (not s)
      rslt
      (recur remain
             (conj seen f)
             (if (seen f) rslt (conj rslt f))))))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (remove-dup- [1 2 1 3 1 2 4]) [1 2 3 4])
          (= (remove-dup- [:a :a :b :b :c :c]) [:a :b :c])
          (= (remove-dup- '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))
          (= (remove-dup- (range 50)) (range 50))))
