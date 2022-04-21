(ns web-api-example.routes
  (:require [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [web-api-example.data.task-repository :as task-repository]
            [web-api-example.data.memory-database :as database]))

(defn assoc-store
  [context]
  (update context :request assoc :store database/store))

(def db-interceptor
  {:nome :db-interceptor
   :enter assoc-store})

(defn hello-function
  [request]
  (let [name (get-in request [:query-params :name] "Everybody")]
  {:status 200 :body (str "Hello World! " name)}))

(defn create-task
  [request]
  (let [body (:json-params request)
        store (:store request)
        task (task-repository/insert-task body store)]
    {:status 200 :body task}))
          
(defn get-tasks
  [request]
  (let [store (:store request)
        tasks (task-repository/get-tasks store)]
    {:status 200 :body @tasks}))

(defn delete-task
  [request]
  (let [store (:store request)
        task-id (get-in request [:path-params :id])]
    (task-repository/delete-task task-id store)
    {:status 200 :body (str "Task " task-id " deleted!")}))

(defn update-task
  [request])

(def routes (route/expand-routes 
  #{["/hello" :get hello-function :route-name :hello-world]
    ["/task" :get [db-interceptor get-tasks] :route-name :get-task]
    ["/task"  :post [db-interceptor (body-params/body-params) create-task] :route-name :create-task ]
    ["/task/:id" :patch [db-interceptor update-task] :route-name :update-task]
    ["/task/:id" :delete [db-interceptor delete-task] :route-name :delete-task]}))