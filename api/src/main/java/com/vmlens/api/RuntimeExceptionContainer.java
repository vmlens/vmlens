package com.vmlens.api;

public class RuntimeExceptionContainer {

    private volatile RuntimeException exception;

    public RuntimeException exception() {
        return exception;
    }

    public void setException(RuntimeException exception) {
        this.exception = exception;
    }
}
