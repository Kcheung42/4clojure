(ns four-clojure.medium.102-intoCamelCase)

;; -----------------------------------------------------------------------------
;; Question
;; -----------------------------------------------------------------------------
;; tags:

;; When working with java, you often need to create an object with fieldsLikeThis,
;; but you'd rather work with a hashmap that has :keys-like-this until it's time
;; to convert. Write a function which takes lower-case hyphen-separated strings
;; and converts them to camel-case strings.

;; -----------------------------------------------------------------------------
;; Solutions
;; -----------------------------------------------------------------------------

(defn into-camel-case
  [string]
  (let [[f & remain] (clojure.string/split string #"-")
        camel-remain (map clojure.string/capitalize remain)]
    (clojure.string/join (cons f camel-remain))))

(defn into-camel-case
  [string]
  (let [[f & remain] (clojure.string/split string #"-")]
    (str f (apply str (map clojure.string/capitalize remain)))))

;; -----------------------------------------------------------------------------
;; Tests
;; -----------------------------------------------------------------------------

(every? true?
        (vector
          (= (into-camel-case "something") "something")
          (= (into-camel-case "multi-word-key") "multiWordKey")
          (= (into-camel-case "leaveMeAlone") "leaveMeAlone")
          ))
