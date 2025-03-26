package com.vmlens.trace.agent.bootstrap.interleave.inttest.util;

import java.util.HashMap;
import java.util.Map;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

public class ThreadIndexToPosition {

    private final Map<Integer,Integer> map = new HashMap<>();

    public Position next(int index) {
        int current = 0;
        if( map.containsKey(index)) {
            current = map.get(index);
            current++;
        }
        map.put(index,current);
        return new Position(index,current);
    }

}
