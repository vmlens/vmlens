package com.vmlens.report.description;

import com.vmlens.report.container.Container;

import java.util.Map;

public class ContainerMapAdapter<KEY, ELEMENT extends Container> {

    private final Map<KEY, ELEMENT> map;

    public ContainerMapAdapter(Map<KEY, ELEMENT> map) {
        this.map = map;
    }

    public String getName(KEY key) {
        if (!map.containsKey(key)) {
            return notFound(key);
        }

        String result = map.get(key).getName();

        if (result == null) {
            return notFound(key);
        }

        return result;
    }

    private String notFound(KEY key) {
        return String.format("not found (%s)", key);
    }
}
