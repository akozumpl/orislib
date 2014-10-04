(ns orislib.env
  (:import (java.io File)))

(defn- homedir
  []
  (->
   (or (java.lang.System/getenv "OPENSHIFT_DATA_DIR")
       (java.lang.System/getenv "HOME"))
   (File. ".orislib")))

(defn getenv- []
  (load-file (.. (File. (homedir) "env.clj") toString)))


(def env (getenv-))

(defn on-openshift? []
  (not (nil? (java.lang.System/getenv "OPENSHIFT_DATA_DIR"))))
