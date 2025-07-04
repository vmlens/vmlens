package com.vmlens.inttest.interleave;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AllFieldsTracedIntTest {

    private volatile int j = 0;

    @Test
    public void testUpdate() throws InterruptedException {
        Map map = new HashMap();
        AllInterleavings testUpdate = new AllInterleavings("allFieldsTracedIntTest");
        while (testUpdate.hasNext()) {
            Thread first = new Thread() {
                @Override
                public void run() {
                    update(map);
                }
            };
            j++;
            first.start();
            update(map);
            j  = 0;
            first.join();
            j++;
        }
    }

    private synchronized void update(Map map) {
        map.put("test", "value");
    }

}
