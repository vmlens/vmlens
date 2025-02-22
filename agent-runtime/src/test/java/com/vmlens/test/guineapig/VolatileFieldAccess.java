package com.vmlens.test.guineapig;

public class VolatileFieldAccess {

    private volatile int field;

    public void update() {
        field++;
    }

}
