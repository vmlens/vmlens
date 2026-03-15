package com.vmlens.test.guineapig.trycatchfinally;

public class MultipleCallWithException {

    public void update() {
        try{
            callback(5);
        } catch(RuntimeException e) {

        }

    }

    protected void callback(int depth) {
        if(depth== 0){
            throw new RuntimeException("test");
        }
        callback(depth-1);
    }






}
