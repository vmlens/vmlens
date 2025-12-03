package com.vmlens.test.guineapig;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class NewUpdater {

    public static AtomicIntegerFieldUpdater newUpdater(Class cl, String name) {
        return AtomicIntegerFieldUpdater.newUpdater(cl, name);
    }

}
