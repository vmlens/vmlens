package com.vmlens.inttest.projects.jpos;


import com.vmlens.api.AllInterleavings;
import org.jpos.q2.QBeanSupport;
import org.junit.jupiter.api.Test;

public class QBeanSupportConcurrentTest {

    @Test
    public void testResetAndPause() throws Throwable {
        try (AllInterleavings allInterleavings = new AllInterleavings("qBeanSupportConcurrentTest")) {
            while (allInterleavings.hasNext()) {
                QBeanSupport qbeanSupport = new QBeanSupport();
                qbeanSupport.setState(0);
                Thread first = new Thread(() -> {
                    qbeanSupport.start();
                });
                first.start();
                qbeanSupport.running();
                first.join();
            }
        }
    }

}
