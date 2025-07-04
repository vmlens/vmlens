package com.vmlens.api;

public class ExceptionHandlerForRunnableWithException implements Runnable {

    private final RunnableWithException runnable;
    private final ThrowableExceptionContainer exceptionContainer;

    public ExceptionHandlerForRunnableWithException(RunnableWithException runnable,
                                                    ThrowableExceptionContainer exceptionContainer) {
        this.runnable = runnable;
        this.exceptionContainer = exceptionContainer;
    }

    @Override
    public void run() {
        try{
            runnable.run();
        } catch(Throwable exp) {
            exp.printStackTrace();
            exceptionContainer.setException(exp);
        }
    }
}
