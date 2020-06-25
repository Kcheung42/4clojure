(ns four-clojure.medium.058-function-comp)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; Write a function which allows you to create function compositions. The
;; parameter list should take a variable number of
;; functions, and create a function that applies them from right-to-left.

;; Special Restrictions
;; comp

;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(defn comp-
  ([] identity)

  ([f] f)

  ([f g]
   (fn
     ([] (f (g)))
     ([x] (f (g x)))
     ([x y] (f (g x y)))
     ([x y & z] (f (apply g x y z)))))

  ([f g & fs]
   (reduce comp- (list* f g fs))))

;; more concise
(defn comp-
  ([] identity)
  ([f] f)
  ([f g]
   (fn
     ([] (f (g)))
     ([& args] (f (apply g args)))))
  ([f g & fs]
   (reduce comp- (list* f g fs))))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= [3 2 1] ((comp- rest reverse) [1 2 3 4]))
          (= 5 ((comp- (partial + 3) second) [1 2 3 4]))
          (= true ((comp- zero? #(mod % 8) +) 3 5 7 9))
          (= "HELLO" ((comp- #(.toUpperCase %) #(apply str %) take) 5 "hello world"))
          (= "HELLO" ((comp- #(.toUpperCase %) #(apply str %) take) 5 "hello world"))
          ))
