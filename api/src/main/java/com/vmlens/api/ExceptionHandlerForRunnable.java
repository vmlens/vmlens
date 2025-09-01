package com.vmlens.api;

class ExceptionHandlerForRunnable implements Runnable {

    private final Runnable runnable;
    private final RuntimeExceptionContainer exceptionContainer;

    public ExceptionHandlerForRunnable(Runnable runnable,
                                       RuntimeExceptionContainer exceptionContainer) {
        this.runnable = runnable;
        this.exceptionContainer = exceptionContainer;
    }

    @Override
    public void run() {
        try{
            runnable.run();
        } catch(RuntimeException exp) {
            exp.printStackTrace();
            exceptionContainer.setException(exp);
        }
    }
}
