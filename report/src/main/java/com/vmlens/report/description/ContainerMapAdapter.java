package com.vmlens.report.description;

import com.vmlens.report.container.Container;

import java.util.Map;

public class ContainerMapAdapter<KEY, ELEMENT extends Container> {

    private final Map<KEY, ELEMENT> map;

    public ContainerMapAdapter(Map<KEY, ELEMENT> map) {
        this.map = map;
    }

    public String getName(KEY key) {
        return map.get(key).getName();
    }

}
