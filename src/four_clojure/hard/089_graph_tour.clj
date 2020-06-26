(ns four-clojure.hard.089-graph-tour)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; Starting with a graph you must write a function that returns true if it is
;; possible to make a tour? of the graph in which every edge is visited exactly once.

;; The graph is represented by a vector of tuples, where each tuple represents a
;; single edge.

;; The rules are:

;; - You can start at any node.
;; - You must visit each edge exactly once.
;; - All edges are undirected.


;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------



(defn graph
  [edges]
  (reduce (fn [acc [a b]]
            (merge-with concat acc {a [b]} {b [a]}))
          {}
          edges))

(defn visited?
  [v coll]
  (some #(= % v) coll))

(defn bfs
  [graph start]
  (loop [queue   (conj clojure.lang.PersistentQueue/EMPTY start)
         visited #{}]
    (if (empty? queue)
      true
      (let [node      (peek queue)
            neighbors (get graph node)
            new-queue (apply conj (pop queue) neighbors)]
        (if (visited? node visited)
          ()
          (recur new-queue (conj visited node)))))))

(defn tour?
  [edges]
  (let [g (graph edges)]
    ;; (bfs g (ffirst edges))
    ;; (set (flatten edges))
    (bfs g (ffirst edges))))


;; At most 2 nodes can have odd degrees
(defn tour? [edges]
  (let [degrees (fn [edges]
                  (apply merge-with + {} (for [[a b] edges
                                               :when (not= a b)]
                                           {a 1 b 1})))
        gdeg    (degrees edges)]
    (and
      (not (empty? gdeg))
      (->> (vals gdeg) (filter odd?) count (>= 2)))))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= true (tour? [[:a :b]]))
          (= false (tour? [[:a :a] [:b :b]]))
          (= false (tour? [[:a :b] [:a :b] [:a :c] [:c :a]
                           [:a :d] [:b :d] [:c :d]]))
          (= true (tour? [[1 2] [2 3] [3 4] [4 1]]))
          (= true (tour? [[:a :b] [:a :c] [:c :b] [:a :e]
                          [:b :e] [:a :d] [:b :d] [:c :e]
                          [:d :e] [:c :f] [:d :f]]))
          (= false (tour? [[1 2] [2 3] [2 4] [2 5]]))
          ))
