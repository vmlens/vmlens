package com.vmlens.test.guineaPig;

public class MethodTryFinally {

    public void update() {
        try {
            System.out.println("");
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("");
        }
    }

}
