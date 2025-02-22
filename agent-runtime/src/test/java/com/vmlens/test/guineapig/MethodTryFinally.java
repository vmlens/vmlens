package com.vmlens.test.guineapig;

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
