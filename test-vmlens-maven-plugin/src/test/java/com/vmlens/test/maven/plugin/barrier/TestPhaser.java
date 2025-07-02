package com.vmlens.test.maven.plugin.barrier;

import com.vmlens.api.AllInterleavings;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.Phaser;

import static com.vmlens.api.Runner.runParallelWithException;

public class TestPhaser {

    private int i = 0;

    @Test
    @Ignore
    public void testBlocking() throws Throwable {
        try(AllInterleavings allInterleavings = new AllInterleavings("testPhaserBlocking")) {
            while (allInterleavings.hasNext()) {
                Phaser phaser = new Phaser(2);
                runParallelWithException(
                        () -> {
                            i = 9;
                            phaser.arriveAndAwaitAdvance();
                        } ,
                        () -> {
                            phaser.arriveAndAwaitAdvance();
                            int x = i;
                        }
                );
            }
        }
    }

}
