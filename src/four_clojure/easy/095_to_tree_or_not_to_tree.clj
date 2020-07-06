(ns four-clojure.easy.095-to-tree-or-not-to-tree)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; Write a predicate which checks whether or not a given sequence represents a
;; binary tree. Each node in the tree must have a value, a left child, and a right child.


;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(defn binary-tree? [coll]
  (cond
    (nil? coll)
    true

    (or (seq? coll) (vector? coll))
    (let [[root left right] coll]
      (true? (and (= 3 (count coll))
                  root
                  (binary-tree? left)
                  (binary-tree? right))))

    :else
    false))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (binary-tree? '(:a (:b nil nil) nil))
             true)
          (= (binary-tree? '(:a (:b nil nil)))
             false)
          (= (binary-tree? [1 nil [2 [3 nil nil] [4 nil nil]]])
             true)
          (= (binary-tree? [1 [2 nil nil] [3 nil nil] [4 nil nil]])
             false)
          (= (binary-tree? [1 [2 [3 [4 nil nil] nil] nil] nil])
             true)
          (= (binary-tree? [1 [2 [3 [4 false nil] nil] nil] nil])
             false)
          (= (binary-tree? '(:a nil ()))
             false)

          (= (binary-tree? '(nil (:b nil nil) nil))
             false)
          ))
