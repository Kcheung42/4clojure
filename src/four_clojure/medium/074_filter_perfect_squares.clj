(ns four-clojure.medium.074-filter-perfect-squares)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; Given a string of comma separated integers, write a function which returns a new
;; comma separated string that only contains the numbers which are perfect squares.

;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(defn filter-squares
  [s]
  (let [nums     (map #(Integer/parseInt %) (clojure.string/split s #",") )
        psquare? (fn [n] (let [sqrt (Math/sqrt n)] (= (Math/floor sqrt) sqrt)))
        perfect  (filter psquare? nums)]
    (apply str (interpose "," perfect))))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (filter-squares "4,5,6,7,8,9") "4,9")
          (= (filter-squares "15,16,25,36,37") "16,25,36")
          ))

