prototypal_aop_engine_android
=============================

If you are reading this file maybe you are searching for information about the code included in this repo: the code represents the core of a prototypal, funny* AOP engine that realize a mechanism similar to the method-proxy mechanism realized by SpringFramework; this behaviour is realized using java.lang.reflect.Proxy ; the proxy behaviour is configurable via a text file containing a JSON object (with key "targets") and it works in generic Java Apps and Android apps. I'd like you enjoy it .... have fun !!!


NOTE on Dependencies: -You'll need the following jars on classpath: a) json-simple-1.1.1.jar (or analogous) -I've compiled and runned it with java7

NOTE: Under test_android_project you'll find sources and resources of a test Android App

*I think it's funny
