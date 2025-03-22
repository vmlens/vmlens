package com.vmlens.test.guineapig;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CallToConcurrentHashMap {

    public void call() {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.get("test");
    }

}
