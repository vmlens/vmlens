package com.vmlens.test.maven.plugin.concurrent;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestConcurrentMap {

    @Test
    public void testConcurrentHashMap() throws InterruptedException {
        runTest(ConcurrentHashMap::new,"concurrentHashMap");
    }

    @Test
    public void testConcurrentSkipListMap() throws InterruptedException {
        runTest(ConcurrentSkipListMap::new,"concurrentSkipListMap");
    }

    private void runTest(Supplier<ConcurrentMap<String,Integer>> createMap, String name) throws InterruptedException {
        testPut(createMap,name + "Put");
    }

    private void testPut(Supplier<ConcurrentMap<String,Integer>> createMap, String name) throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);

        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings(name)) {
            while (allInterleavings.hasNext()) {
                final ConcurrentMap<String,Integer>  map = createMap.get();
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

    private void testIterator(Supplier<ConcurrentMap<String,Integer>> createMap, String name) throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);

        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings(name)) {
            while (allInterleavings.hasNext()) {
                final ConcurrentMap<String,Integer>  map = createMap.get();
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        map.put("number", 6);
                    }
                };
                first.start();

                map.values().iterator();

                first.join();
                countSet.add(map.get("threadCount"));
            }
            assertThat(countSet,is(expectedSet));
        }
    }

}
