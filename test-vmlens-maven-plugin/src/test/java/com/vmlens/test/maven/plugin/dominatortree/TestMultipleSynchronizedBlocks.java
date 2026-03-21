package com.vmlens.test.maven.plugin.dominatortree;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.vmlens.api.AllInterleavings.doNotTrace;
import static com.vmlens.api.Runner.runParallel;
public class TestMultipleSynchronizedBlocks {

    private final Map<Integer,Object> lockMaps = new ConcurrentHashMap<>();

    @Test
    public void testSynchronizedBlocks() {
        for( int i = 0; i < 15; i++ ) {
            lockMaps.put(i,new Object());
        }
        try(AllInterleavings allInterleavings = new AllInterleavings("dominatorTreeMultipleSynchronizedBlocks")) {
            while (allInterleavings.hasNext()) {
                runParallel(  () -> { recursive(9); } , () -> { recursive(9); }  );
            }
        }
    }

    public void recursive(int depth) {
        LockHolder holder = new LockHolder();
        doNotTrace(() -> {
            Object obj = lockMaps.get(depth);
            holder.setLock(obj);
        });
        synchronized (holder.lock()) {
            if(depth==0) {
                return;
            }
            recursive(depth-1);
        }
    }

}
