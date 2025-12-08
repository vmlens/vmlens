package com.vmlens.api;

import java.util.concurrent.ConcurrentHashMap;

public class TestResultMap<KEY,VALUE> {

    private final ConcurrentHashMap<KEY,VALUE> map = new ConcurrentHashMap<>();

    public VALUE put(KEY key, VALUE value) {
        return map.put(key, value);
    }

    public VALUE get(KEY key) {
        return map.get(key);
    }
}
