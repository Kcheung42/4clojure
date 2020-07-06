(ns four-clojure.medium.054-partition-a-sequence)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; Write a function which returns a sequence of lists of x items each. Lists of
;; less than x items should not be returned.

;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(comment
  (defn partition-
    [n coll]
    (when-let [s (seq coll)]
      (let [seg (take n s)]
        (when (= (count seg) n)
          (cons seg (partition- n (nthrest coll n))))))))

;; more concise
(defn partition-
  [n coll]
  (when (and (seq coll) (>= (count coll) n))
    (cons (take n coll) (partition- n (drop n coll) ))))

(defn partition-
  [n coll]
  (when-let [s (seq coll)]
    (when (<= n (count s))
      (cons (take n s) (partition- n (drop n s))))))

(comment ;;Source
  (defn partition
    "Returns a lazy sequence of lists of n items each, at offsets step
  apart. If step is not supplied, defaults to n, i.e. the partitions
  do not overlap. If a pad collection is supplied, use its elements as
  necessary to complete last partition upto n items. In case there are
  not enough padding elements, return a partition with less than n items."
    {:added  "1.0"
     :static true}
    ([n coll]
     (partition n n coll))

    ([n step coll]
     (lazy-seq
       (when-let [s (seq coll)]
         (let [p (doall (take n s))] ;;#2
           (when (= n (count p))
             (cons p (partition n step (nthrest s step)))))))) ;;#1

    #_([n step pad coll]
       (lazy-seq
         (when-let [s (seq coll)]
           (let [p (doall (take n s))]
             (if (= n (count p))
               (cons p (partition n step pad (nthrest s step)))
               (list (take n (concat p pad))))))))))

;; #1 Why use `nthrest` instead of `drop`?
;; #2 Why `doall` ?

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (partition- 3 (range 9))
             '((0 1 2) (3 4 5) (6 7 8)))

          (= (partition- 2 (range 8))
             '((0 1) (2 3) (4 5) (6 7)))

          (= (partition- 3 (range 8))
             '((0 1 2) (3 4 5)))

          (= (partition- 3 (range 8))
             '((0 1 2) (3 4 5)))
          ))
