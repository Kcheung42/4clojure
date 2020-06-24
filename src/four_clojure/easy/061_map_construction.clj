(ns four-clojure.easy.061-map-construction)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; Write a function which takes a vector of keys and a vector of values and
;; constructs a map from them.

;; Special Restriction: Zipmap



;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------


(comment
  ;; my solution
  (defn f
    [kys vls]
    (loop [rslt                    {}
           [fk & k-remain :as kys] (seq kys)
           [fv & v-remain :as vls] (seq vls)]
      (if (empty? kys)
        rslt
        (recur (if fv (assoc rslt fk fv) rslt)
               k-remain
               v-remain)))))

(comment
  (defn f
    [kys vls]
    (apply assoc {} (interleave kys vls))))


(defn f
  [kys vls]
  (apply hash-map (interleave kys vls)))


(interleave [:a :b :c] [1 2 3]) ;;=> (:a 1 :b 2 :c 3)

(zipmap [:a :b :c] [1 2 3]) ;;=>{:a 1, :b 2, :c 3}

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------


(every? true?
        (vector
          (= (f [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3})
          (= (f [1 2 3 4] ["one" "two" "three"]) {1 "one", 2 "two", 3 "three"})
          (= (f [:foo :bar] ["foo" "bar" "baz"]) {:foo "foo", :bar "bar"})
          ))
