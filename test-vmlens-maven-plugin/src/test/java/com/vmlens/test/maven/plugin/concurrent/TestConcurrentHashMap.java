package com.vmlens.test.maven.plugin.concurrent;

import com.vmlens.api.AllInterleaving;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestConcurrentHashMap {

    @Test
    public void testUpdate() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);

        Set<Integer> countSet = new HashSet<>();
        try(AllInterleaving allInterleaving = new AllInterleaving("concurrentHashMapCT")) {
            while (allInterleaving.hasNext()) {
                final ConcurrentHashMap<String,Integer>  map = new ConcurrentHashMap<>();
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        Integer value = map.get("threadCount");
                        if(value == null) {
                            map.put("threadCount",1);
                        } else {
                            map.put("threadCount",value + 1);
                        }
                    }
                };
                first.start();
                Integer value = map.get("threadCount");
                if(value == null) {
                    map.put("threadCount",1);
                } else {
                    map.put("threadCount",value + 1);
                }
                first.join();
                countSet.add(map.get("threadCount"));
            }
            assertThat(countSet,is(expectedSet));
        }

    }

}
