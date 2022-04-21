(ns web-api-example.data.task-repository)

(defn get-tasks
  [store]
  store)

(defn insert-task
  [task store]
  (let [new-id (java.util.UUID/randomUUID)
    new-task (assoc task :id new-id)]
    (swap! store assoc new-id new-task)
    new-task))

(defn delete-task
  [task-id store]
  (let [task-uuid (java.util.UUID/fromString task-id)]
  (swap! store dissoc task-uuid)))

(defn create-task-from-request
  [request]
  (let [task (get-in request [:json-params])
        task-name (get-in task [:name])
        task-description (get-in task [:description])
        task-status (get-in task [:status])
        task-id (java.util.UUID/randomUUID)]
    (let [new-task {
            :name task-name
            :description task-description
            :status task-status
            :id task-id
          }]
      new-task)))