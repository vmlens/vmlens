package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleavings;


public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        new MainClass().testUpdate();
    }

    private int j = 0;

    public void testUpdate() throws InterruptedException {
        try(AllInterleavings allInterleavins = new AllInterleavings("testNonVolatileField")) {
            while (allInterleavins.hasNext()) {
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
