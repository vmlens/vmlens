package com.vmlens.api;

@FunctionalInterface
public interface RunnableWithException {

    void run() throws Exception;

}
