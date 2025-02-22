package com.vmlens.test.guineapig;

import java.util.function.Consumer;

public class StandardFunctionCallback {

    private void onConsumer(Consumer consumer) {

    }

    public void call() {
        onConsumer(System.out::println);
    }

}
