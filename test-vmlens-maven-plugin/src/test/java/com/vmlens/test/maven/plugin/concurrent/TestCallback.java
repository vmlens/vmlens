package com.vmlens.test.maven.plugin.concurrent;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCallback {

    @Test
    public void callback() throws InterruptedException {
        final String firstKey = "aadswedxx";
        final String secondKey = "xxdxfss";

        try(AllInterleavings allInterleavings = new AllInterleavings("concurrent.callback")) {
            while (allInterleavings.hasNext()) {
                // Given
                final AtomicInteger counter = new AtomicInteger();
                ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
                map.put(firstKey, "");
                map.put(secondKey, "");
                // When
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        map.compute(firstKey , ( key , value ) ->  "" + counter.incrementAndGet()   );
                    }
                };
                first.start();
                map.compute(secondKey , ( key , value ) ->  "" + counter.incrementAndGet()  );
                first.join();
            }
        }
    }

}
