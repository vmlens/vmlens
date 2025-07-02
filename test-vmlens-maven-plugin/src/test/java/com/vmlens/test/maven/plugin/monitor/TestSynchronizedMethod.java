package com.vmlens.test.maven.plugin.monitor;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestSynchronizedMethod {

    @Test
    public void testUpdate() throws InterruptedException {
        Map map = new HashMap();
        AllInterleavings testUpdate = new AllInterleavings("testSynchronizedMethod");
        while (testUpdate.hasNext()) {
            Thread first = new Thread() {
                @Override
                public void run() {
                    update(map);
                }
            };
            first.start();
            update(map);
            first.join();
        }
    }

    private synchronized void update(Map map) {
        map.put("test", "value");
    }

}
