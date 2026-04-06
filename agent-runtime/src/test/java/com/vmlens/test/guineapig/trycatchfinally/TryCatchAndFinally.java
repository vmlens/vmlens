package com.vmlens.test.guineapig.trycatchfinally;

public class TryCatchAndFinally {

    public void update() {
        try{
            callback();
        } catch(RuntimeException e) {

        }
        finally {
            callbackFinally();
        }
    }

    protected void callback() {
        try{
        throw new RuntimeException("test");
        } catch(RuntimeException e) {

        }
        throw new RuntimeException("test");
    }



    protected void callbackFinally() {

    }

}
