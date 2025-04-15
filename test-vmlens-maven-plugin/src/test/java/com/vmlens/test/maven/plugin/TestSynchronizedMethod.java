package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleaving;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestSynchronizedMethod {

    @Test
    public void testUpdate() throws InterruptedException {
        Map map = new HashMap();
        AllInterleaving testUpdate = new AllInterleaving("testSynchronizedMethod");
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
