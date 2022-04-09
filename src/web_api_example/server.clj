(ns web-api-example.server
  (:require [io.pedestal.http.route :as route]
    [io.pedestal.http :as http]))

(defn hello-function
  [request]
  (let [name (get-in request [:query-params :name] "Everybody")]
  {:status 200 :body (str "Hello World! " name)}))

(def routes (route/expand-routes 
  #{["/hello" :get hello-function :route-name :hello-world]}))

(def service-map 
  {::http/routes routes 
    ::http/port 9999
    ::http/type :jetty
    ::http/join? false})

(defn start [& args]
  (let [service (http/create-server service-map)]
    (http/start service)
    (println "Server started")))