package com.vmlens.inttest.nonvolatile.protectedfield;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

public class ChildWithProtectedFieldTest {

    @Test
    public void testUpdate() throws InterruptedException {
        AllInterleavings testUpdate = new AllInterleavings("childWithProtectedFieldTest");
        while (testUpdate.hasNext()) {
            ChildWithProtectedField childWithProtectedField= new ChildWithProtectedField();
            Thread first = new Thread() {
                @Override
                public void run() {
                    childWithProtectedField.i = 0;
                }
            };
            first.start();
            int j = childWithProtectedField.i;
            first.join();
        }
    }

}
