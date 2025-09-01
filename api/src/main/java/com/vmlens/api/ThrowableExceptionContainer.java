package com.vmlens.api;

class ThrowableExceptionContainer {

    private volatile Throwable exception;

    public Throwable exception() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

}
