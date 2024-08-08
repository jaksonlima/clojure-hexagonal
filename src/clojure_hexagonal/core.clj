(ns clojure-hexagonal.core)

(defprotocol User
  (create [this user-data])
  (findAll [this]))

(defrecord InMemoryUserRepo [storage]
  User
  (create [this user-data]
    (swap! storage conj user-data)
    user-data)
  (findAll [this]
    @storage)
  )

(defn create-in-memory-repo []
  (->InMemoryUserRepo (atom [])))

(defn -main [& args]
  (let [repo (create-in-memory-repo)]
    (create repo {:id 1 :name "lima"})
    (create repo {:id 2 :name "jack"})
    (as->(findAll repo) result
         (println result))))