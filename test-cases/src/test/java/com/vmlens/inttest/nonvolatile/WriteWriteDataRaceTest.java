package com.vmlens.inttest.nonvolatile;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

public class WriteWriteDataRaceTest {

    private int j = 0;

    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("writeWriteDataRace");
        while (testUpdate.hasNext()) {
            Thread first = new Thread() {
                @Override
                public void run() {
                    j = 0;
                }
            };
            first.start();
            j = 0;
            first.join();
        }
    }

}
