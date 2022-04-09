(defproject web-api-example "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                [io.pedestal/pedestal.service "0.5.7"]
                [io.pedestal/pedestal.route "0.5.7"]
                [io.pedestal/pedestal.jetty "0.5.7"]
                [org.clojure/data.json "0.2.6"]
                [org.slf4j/slf4j-simple "1.7.28"]]
  :repl-options { :init-ns web-api-example.core }
  :main web-api-example.core)