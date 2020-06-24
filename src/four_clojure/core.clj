(ns four-clojure.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(do
  (defn my-reverse
    [coll]
    (loop [[r & more :as all] (seq coll)
           acc '()]
      (if all
        (recur more (cons r acc))
        acc))))

(defn my-reverse-2
  [coll]
  (reduce conj () coll)) ;=> conj to a list prepends)

(defn my-nth
  [coll n]
  (if (> n 0)
    (recur (rest coll) (- n 1))
    (first coll)))

(defn my-count
  [coll]
  (loop [c     coll
         count 0]
    (if (not (empty? c))
      (recur (rest c) (+ count 1))
      count)))

(defn my-fib
  [n]
  (let [fib (fn fib-seq
              [a b]
              (lazy-seq (cons a (fib-seq b (+ a b))))
              0 1)]
    (take n fib)))

(defn my-flatten
  [coll]
  (lazy-seq
    (when-let [s (seq coll)]
      (if (sequential? (first s))
        (concat (my-flatten (first s)) (my-flatten (rest s)))
        (cons (first s) (my-flatten (rest s)))))))

(do
  (defn my-compress
    [coll]
    (map first (partition-by identity coll)))
  (my-compress "abcccd"))

(do
  (defn primes
    [s]
    (cons (first s)
      (lazy-seq (primes (filter #(not= 0 (mod % (first s)))
                          (rest s))))))
  (take 20 (primes (iterate inc 2)))
  (nth (primes (iterate inc 2)) 1000))

(do
  (defn my-range
    ""
    [start end]
    (take (- end start) (iterate inc start)))
  (my-range -2 10))

(defn factorial-
  [n]
  (apply * (take n (iterate inc 1))))


(defn flatten-
  "Easy"
  [coll]
  (lazy-seq
    (when-let [s (seq coll)]
      (if (sequential? (first s))
        (concat (my-flatten (first s)) (my-flatten (rest s)))
        (cons (first s) (my-flatten (rest s)))))))


;; ################################################################################
;; interleave (E)
;; ################################################################################


(defn interleave-
  [col1 col2]
  (mapcat vector col1 col2))


;; ################################################################################
;; interpose (E)
;; ################################################################################

(defn my-interpose
  [x coll]
  (when-let [s (seq coll)]
    (if (next s)
      (concat (vector (first s)) (vector x) (my-interpose x (rest s)))
      (vector (first s)))))


(defn interpose-v2
  "Cleaner"
  [x coll]
  (butlast (mapcat #(vector % x) coll)))


;; ################################################################################
;; Split by Type (M)
;; ################################################################################


(defn split-by-type
  [coll]
  (vals
    (reduce (fn [acc x]
              (let [ttype (type x)]
                (assoc acc ttype (conj (get acc ttype []) x))))
      {}
      coll)))

(def split-by-type-v2
  (comp vals (partial group-by type)))


;; ################################################################################
;; #22 Count a Sequence (E)
;; ################################################################################


(defn count-
  [coll]
  (reduce
    (fn[acc x]
      (when x
        (+ acc 1)))
    0
    coll))

(defn count-2-
  [coll]
  (loop [c     coll
         count 0]
    (if (not (empty? c))
      (recur (rest c) (+ count 1))
      count)))

;; ################################################################################
;; #41 Drop Every Nth Item (E)
;; ################################################################################

(defn drop-every-nth
  [s n]
  (if (seq s)
    (lazy-seq
      (concat
        (take (dec n) s)
        (drop-every-nth (drop n s) n)))))


;; ################################################################################
;; Functional Composition (M)
;; ################################################################################


(defn comp-
  ([] identity)
  ([f] f)
  ([f g]
   (fn
     ([] (f (g)))
     ([& args] (f (apply g args)))))
  ([f g & fs]
   (reduce comp- (list* f g fs))))


;; ################################################################################
;; #146 Trees to Tables (E)
;; ################################################################################


(defn trees-to-tables
  [t]
  (into {} (for [[x y] t
                 [k v] y]
             [[x k] v])))

;; ################################################################################
;; #19 Last (E)
;; ################################################################################

(defn last-
  [coll]
  (first (reverse coll)))

(defn last-2
  [coll]
  (if (next coll)
    (recur (next coll))
    (first coll)))


;; ################################################################################
;; #83 Half Truth (E)
;; ################################################################################


(defn half-truth
  ([]
   false)
  ([a]
   false)
  ([a & args]
   (let [result (reduce (fn[acc x]
                          (if (= true x)
                            (bit-or acc 1)
                            (bit-or acc 2)))
                  0
                  (list* a args))]
     (if (= 3 result)
       true
       false))))


;; ################################################################################
;; Rotate Sequence
;; ################################################################################

(defn rotate-sequence
  [n s]
  (when (seq s)
    (let [len (count s)
          x (if (> n 0)
              (mod n len)
              (- len (mod (max n (- n)) len)))]
      (concat (drop x s) (take x s)))))


;; ################################################################################
;; Quick Sort
;; ################################################################################

(defn quick-sort
  [[pivot & coll]]
  (when pivot
    (concat
      (quick-sort (filter #(< % pivot) coll))
      pivot
      (quick-sort (filter #(> % pivot) coll)))))

;; ################################################################################
;; #46 Flipping Out
;;
;; Write a higher-order function which flips the order of the arguments
;; of an input function.
;; ################################################################################

(defn flip-out
  [f]
  (fn
    ([] f)
    ([x] (f x))
    ([x & args]
     (let [[first & rest] (reverse (list* x args))]
       (apply f first rest)))))

#_(defn flip-out
    [f] (fn [arg1 arg2] (f arg2 arg1)))


;; ################################################################################
;; #55 Count Occurances
;;
;; Write a function which returns a map containing the number of occurences of
;; each distinct item in a sequence.
;; ################################################################################


(def frequencies-
  [coll]
  (when-let [s (seq coll)]
    (reduce (fn [m x]
              (if (contains? m x)
                (assoc m x (inc (get acc x)))))
      {}
      s)))

[1 1 1 2 2 2 3]

{1 2
 2 3
 3 1}

(defn frequencies-
  [coll]
  (when-let [s (seq coll)]
    (reduce
      (fn [acc x]
        (if (contains? acc x)
          (assoc acc x (+ 1 (get acc x)))
          (assoc acc x 1)))
      {}
      s)))


;; ################################################################################
;; #56 Find Distict Items
;;
;; Write a function which removes the duplicates from a sequence. Order of the
;; items must be maintained.
;; ################################################################################


(defn distinct-
  [coll]
  (letfn [(step [[f s t :as xs] seen]
            (when-let [s (seq xs)]
              (if (contains? seen f)
                (recur (rest s) seen)
                (cons f (step (rest s) (conj seen f))))))]
    (step coll #{})))
