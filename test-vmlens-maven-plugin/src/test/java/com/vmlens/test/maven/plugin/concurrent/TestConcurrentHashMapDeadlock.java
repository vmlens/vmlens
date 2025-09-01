package com.vmlens.test.maven.plugin.concurrent;



import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMapDeadlock {

    @Test
    public void readWrite() throws InterruptedException {
        final String firstKey = "aadswedxx";
        final String secondKey = "xxdxfss";

        try(AllInterleavings allInterleavings = new AllInterleavings("concurrent.deadlock")) {
            while (allInterleavings.hasNext()) {
                // Given
                ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
                map.put(firstKey, "");
                map.put(secondKey, "");
                // When
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        map.compute(firstKey , ( key , value ) ->  map.put(secondKey , "Hallo")   );
                    }
                };
                first.start();
                map.compute(secondKey , ( key , value ) ->  map.put(firstKey , "Hallo")   );
                first.join();
            }
        }
    }

}
