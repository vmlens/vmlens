package com.vmlens.inttest.nonvolatile;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashMapTest {

    @Test
    public void testUpdate() throws InterruptedException {
        Map map = new HashMap();

        AllInterleavings testUpdate = new AllInterleavings("hashMapTest");

        while (testUpdate.hasNext()) {
            Thread first = new Thread() {
                @Override
                public void run() {
                    update(map);;
                }
            };
            first.start();
            update(map);
            first.join();
        }
    }

    private void update(Map map) {
        map.put("test", "value");
    }

}
