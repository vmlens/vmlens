package com.vmlens.test.maven.plugin.monitor;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestSynchronizedMethodAndVolatileField {

    private volatile int j = 0;

    @Test
    public void testUpdate() throws InterruptedException {
        Map map = new HashMap();
        AllInterleavings testUpdate = new AllInterleavings("testSynchronizedMethodAndVolatileField");
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
            first.join();
            j++;
        }
    }

    private synchronized void update(Map map) {
        map.put("test", "value");
    }

}
