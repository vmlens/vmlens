package com.vmlens.inttest.nonvolatile;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {



    @Test
    public void testUpdate() throws InterruptedException {
        Set set = new HashSet();

        AllInterleavings testUpdate = new AllInterleavings("hashSetTest");

        while (testUpdate.hasNext()) {
            Thread first = new Thread() {
                @Override
                public void run() {
                    update(set);;
                }
            };
            first.start();
            update(set);
            first.join();
        }
    }

    private void update(Set set) {
        set.add("test");
    }

}
