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
                Thread first = new Thread(() -> {
                    qbeanSupport.start();
                });
                qbeanSupport.running();
                first.join();

            }
        }
    }

}
