package com.vmlens.test.guineaPig;

public class VolatileFieldAccess {

    private volatile int field;

    public void update() {
        field = 1;
    }

}
