package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleaving;
import org.junit.Test;

public class TestFinal {

    private final int i = 0;

    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleaving testUpdate = new AllInterleaving("testFinal");

        while (testUpdate.hasNext()) {
            new TestFinal();
        }
    }


}
