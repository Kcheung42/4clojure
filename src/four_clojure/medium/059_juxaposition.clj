(ns four-clojure.medium.059-juxaposition)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; Take a set of functions and return a new function that takes a variable number of
;; arguments and returns a sequence containing the result of applying each function
;; left-to-right to the argument list.

;; Special Restrictions
;; juxt

;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(defn juxt-
  [& fs]
  (fn [& as]
    (map #(apply % as) fs)))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= [21 6 1] ((juxt- + max min) 2 3 5 1 6 4))
          (= ["HELLO" 5] ((juxt- #(.toUpperCase %) count) "hello"))
          (= [2 6 4] ((juxt- :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10}))
          (= [2 6 4] ((juxt- :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10}))
          ))
