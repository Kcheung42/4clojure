(ns four-clojure.medium.065-black-box-testing)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; Clojure has many sequence types, which act in subtly different ways. The core
;; functions typically convert them into a uniform "sequence" type and work with
;; them that way, but it can be important to understand the behavioral and
;; performance differences so that you know which kind is appropriate for your application.

;; Write a function which takes a collection and returns one of
;; :map, :set, :list, or :vector - describing the type of collection it was given.
;; You won't be allowed to inspect their class or use the built-in predicates like
;; list? - the point is to poke at them and understand their behavior.


;; Special Restrictions
;; class
;; type
;; Class
;; vector?
;; sequential?
;; list?
;; seq?
;; map?
;; set?
;; instance?
;; getClass


;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(defn type-
  [coll]
  (cond
    (= "t" (get (conj coll [:t "t"]) :t)) :map

    (= :t (get (conj coll :t) :t)) :set

    (= :b (first (conj coll :a :b))) :list

    (= :b (last (conj coll :a :b))) :vector
    ))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= :map (type- {:a 1, :b 2}))

          (= :list (type- (range (rand-int 20))))

          (= :vector (type- [1 2 3 4 5 6]))

          (= :set (type- #{10 (rand-int 5)}))

          (= [:map :set :vector :list] (map type- [{} #{} [] ()]))

          (= [:map :set :vector :list] (map type- [{} #{} [] ()]))
          ))
