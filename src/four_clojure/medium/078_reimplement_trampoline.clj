(ns four-clojure.medium.078-reimplement-trampoline)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:



;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(defn trampoline-
  [f & args]
  (let [rslt (apply f args)]
    (if (fn? rslt)
      (trampoline- rslt)
      rslt)))

;; loop is okay too. Loop enforces tail recursion
(defn trampoline-
  [f & args]
  (loop [f (apply f args)]
    (if (fn? f)
      (recur (f))
      f)))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (letfn [(triple [x] #(sub-two (* 3 x)))
                     (sub-two [x] #(stop?(- x 2)))
                     (stop? [x] (if (> x 50) x #(triple x)))]
               (trampoline- triple 2))
             82)

          (= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
                     (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
               (map (partial trampoline- my-even?) (range 6)))
             [true false true false true false])
          ))
