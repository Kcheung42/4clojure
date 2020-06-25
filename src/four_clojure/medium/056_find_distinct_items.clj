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

;; Initial


(comment
  (defn f
    "Remove duplicates from a sequence. Order is maintained"
    [coll]
    (letfn [(step [[f s t :as xs] seen]
              (when-let [s (seq xs)]
                (if (contains? seen f)
                  (recur (rest s) seen)
                  (cons f (step (rest s) (conj seen f))))))]
      (step coll #{}))))

;;Idiomatic?
;; Other solutions
(comment
  (defn f
    [coll]
    (reduce (fn [v x]
              (if ((set v) x) ;; what is the time complexity of (set v)?
                v
                (conj v x)))
            []
            coll)))

;;Idiomatic?
(comment
  (defn f
    "Remove duplicates from a sequence. Order is maintained"
    [coll]
    (reduce #(if ((set %1) %2) %1 (conj %1 %2)) [] coll)))

(defn f
  "Remove duplicates from a sequence. Order is maintained"
  [coll]
  (loop [[f & remain :as x] (seq coll)
         seen               #{}
         rslt               []]
    (if (empty? x)
      rslt
      (recur remain
             (conj seen f)
             (if (seen f) rslt (conj rslt f))))))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (f [1 2 1 3 1 2 4]) [1 2 3 4])
          (= (f [:a :a :b :b :c :c]) [:a :b :c])
          (= (f '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))
          (= (f (range 50)) (range 50))))
