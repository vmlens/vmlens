package com.vmlens.inttest.nonvolatile.innerclass;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

public class InnerClassTest {

    @Test
    public void innerChild() throws InterruptedException {
        try(AllInterleavings allInterleavings = new AllInterleavings("innerChild")) {
            while (allInterleavings.hasNext()) {
                ParentInnerClass.ParentInner innerChild = new ChildInnerClass.InnerChild();
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        innerChild.get();
                    }
                };
                first.start();
                innerChild.set();
                first.join();
            }
        }
    }

}
