(ns web-api-example.server
  (:require [io.pedestal.http :as http]
            [io.pedestal.test :as test]
            [web-api-example.routes :as routes]))

(def service-map 
  {::http/routes routes/routes 
    ::http/port 9999
    ::http/type :jetty
    ::http/join? false})

(defonce server (atom nil))

(defn start [& args]
  (let [service (http/create-server service-map)]
    (reset! server (http/start service))))

(defn stop
  []
  (if @server
    (http/stop @server)))

(defn restart
  []
  (stop)
  (start))

(def body-test "{
  \"name\": \"Ir para o jogo de futebol\",
  \"description\": \"Vamos para o jogo\",
  \"status\": \"Pendente\"}")

(defn test-routes
  []
  (let [service (http/create-server service-map)
        server (atom nil)]
    (reset! server (http/start service))
    (println (test/response-for (::http/service-fn @server) :get "/hello?name=Pedestal"))
    (println (test/response-for (::http/service-fn @server) :get "/task"))
    (println (test/response-for (::http/service-fn @server) :post "/task" :body body-test ))))