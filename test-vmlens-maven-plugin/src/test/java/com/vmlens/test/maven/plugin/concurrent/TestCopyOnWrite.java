package com.vmlens.test.maven.plugin.concurrent;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Supplier;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;


public class TestCopyOnWrite {

    @Test
    public void testCount() throws InterruptedException {
        runTest("copyOnWriteArrayList", () -> new CopyOnWriteArrayList<>());
        runTest("copyOnWriteArraySet", () -> new CopyOnWriteArraySet<>());
    }

    private void runTest(String nameOfTest, Supplier<Collection<String>> createCollection) throws InterruptedException {
        int count = 0;
        try(AllInterleavings allInterleaving = new AllInterleavings(nameOfTest)) {
            while (allInterleaving.hasNext()) {
                Collection<String> copyOnWrite = createCollection.get();
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        copyOnWrite.add("first");
                    }
                };
                first.start();
                copyOnWrite.add("second");
                first.join();
                count++;
            }
            assertThat(count,greaterThan(2));
        }
    }

}
