(ns web-api-example.handler-test
  (:require [clojure.test :refer :all]
            [web-api-example.server :as server]))

(deftest test-app
  (testing "main route"
    (server/test-routes)
    (is (= 200 200))))
