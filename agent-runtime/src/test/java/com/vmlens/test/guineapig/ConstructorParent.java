package com.vmlens.test.guineapig;

public class ConstructorParent {


    public ConstructorParent(int i) {

    }

    public ConstructorParent() {
        this(0);
    }

}
