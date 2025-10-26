package com.vmlens.inttest.nonvolatile;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.AllInterleavingsBuilder;
import org.junit.Test;

public class TestIntentionalDataRace {

    private int j = 0;
    @Test
    public void testUpdate() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavingsBuilder()
                .withIntentionalDataRace("com.vmlens.inttest.nonvolatile.TestIntentionalDataRace" , "j")
                .build("intentionalDataRace")) {
            while (allInterleavings.hasNext()) {
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

}
