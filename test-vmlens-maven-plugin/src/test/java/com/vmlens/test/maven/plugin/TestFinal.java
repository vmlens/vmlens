package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

public class TestFinal {

    private final int i = 0;

    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("testFinal");

        while (testUpdate.hasNext()) {
            new TestFinal();
        }
    }


}
