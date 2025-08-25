package com.vmlens.test.guineapig;


public class ConstructorChild extends ConstructorParent {

    public ConstructorChild(int h) {
        super(h + 7);
    }

    public ConstructorChild() {
        this(calculate());
    }

    private static int calculate() {
        return 0;
    }
}
