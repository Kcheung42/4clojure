(ns four-clojure.bfs)

(defn find-neighbors
  "Returns the sequence of neighbors for the given node"
  [v coll]
  (get coll v))

(defn visited?
  [v coll]
  (some #(= % v) coll))

(defn graph-bfs
  "Traverses a graph in Breadth First Search (BFS)."
  [graph v]
  (loop [queue   (conj clojure.lang.PersistentQueue/EMPTY v)
         visited []]
    (if (empty? queue) visited
        (let [v           (peek queue)
              neighbors   (find-neighbors v graph)
              not-visited (filter (complement #(visited? % visited)) neighbors)
              new-queue   (apply conj (pop queue) not-visited)]
          (recur new-queue (conj visited v))))))

(def graph {:A [:B :C]
            :B [:A :X]
            :X [:B :Y]
            :Y [:X]
            :C [:A :D]
            :D [:C :E :F]
            :E [:D :G]
            :F [:D :G]
            :G [:E :F]})

(graph-bfs graph :A)
[:A :B :C :X :D :Y :E :F :G]
