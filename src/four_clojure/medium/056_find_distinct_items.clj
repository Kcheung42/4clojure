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

(avg (repeatedly 1000 #(bench (remove-dup- (repeat 1000000 1)))))
;; => Elapsed time: 814.123751 msecs

(defn distinct-source
  [coll]
  (let [step (fn step [xs seen]
               (lazy-seq ;;#3
                 ((fn [[f :as xs] seen]    ;;#1
                    (when-let [s (seq xs)]
                      (if (contains? seen f)
                        (recur (rest s) seen) ;;#2
                        (cons f (step (rest s) (conj seen f)))))) ;;#4
                  xs seen)))]
    (step coll #{})))
;; (time (time (remove-dup- (repeat 1000000 1))))
;; => Elapsed time: 0.069488 msecs

(defn distinct-
  ([coll]
   (distinct- coll #{}))

  ([coll seen]
   (lazy-seq
     ((fn [[f :as xs] seen]
        (when-let [s (seq xs)]
          (if (contains? seen f)
            (recur (rest s) seen)
            (cons f (distinct- (rest s) (conj seen f))))))
      coll seen))))

;;#1 naming with xs when destructuring a coll
;;#2 note this uses recur and not step
;;#3 base case calls (lazy-seq nil) => returns (). This allows for cons to work
;;    (cons f (lazy-seq nil)) => f
;;#4 pattern (cons x (recursive call))

(defmacro bench
  "Times the execution of forms, discarding their output and returning
  a long in nanoseconds."
  ([& forms]
   `(let [start# (System/nanoTime)]
      ~@forms

      (- (System/nanoTime) start#))))

(defn avg
  [coll]
  (float (/ (reduce + coll) (count coll))))

(avg (repeatedly 1000 #(bench (distinct (repeat 1000000 1)))))  ;;=> 927.352
(avg (repeatedly 1000 #(bench (distinct- (repeat 1000000 1))))) ;;=> 867.697

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (remove-dup- [1 2 1 3 1 2 4]) [1 2 3 4])
          (= (remove-dup- [:a :a :b :b :c :c]) [:a :b :c])
          (= (remove-dup- '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))
          (= (remove-dup- (range 50)) (range 50))))
