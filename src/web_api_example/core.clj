(ns web-api-example.core
  (:require [web-api-example.server :as server]))

(defn -main [& args]
  (server/start))