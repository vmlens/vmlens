package de.cxcd;

import com.vmlens.api.AllInterleaving;
import org.junit.Test;

public class VolatileFieldCT {
    private volatile int j = 0;

    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleaving testUpdate = new AllInterleaving("otherPackage");

        while (testUpdate.hasNext()) {
            j = 0;
            Thread first = new Thread() {
                @Override
                public void run() {
                    j++;
                }
            };
            first.start();
            j++;
            first.join();
        }
    }


}
