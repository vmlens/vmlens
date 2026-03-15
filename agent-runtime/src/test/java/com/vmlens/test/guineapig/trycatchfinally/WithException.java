package com.vmlens.test.guineapig.trycatchfinally;

public class WithException {

    public void update() {
        try {
            methodWithException();
        }  catch(RuntimeException e) {

        }
    }

    public void methodWithException() {
        try{
            callback();
        } catch(RuntimeException e) {
            throw e;
        }
    }


    protected void callback() {
        throw new RuntimeException("should not be called");
    }

}
