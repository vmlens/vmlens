package com.vmlens.test.maven.plugin.reflection;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestAtomicReferenceFieldUpdater {

    public volatile String j = "";

    @Test
    public void testReadWrite() throws InterruptedException {
        AtomicReferenceFieldUpdater<TestAtomicReferenceFieldUpdater,String> fieldUpdater =
                AtomicReferenceFieldUpdater.newUpdater(TestAtomicReferenceFieldUpdater.class, String.class,"j");

        Set<String> expectedSet = new HashSet<>();
        expectedSet.add("");
        expectedSet.add("test");

        Set<String> actual = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testAtomicReferenceFieldUpdater")) {
            while (allInterleavings.hasNext()) {
                j = "";
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        fieldUpdater.set(TestAtomicReferenceFieldUpdater.this,"test");
                    }
                };
                first.start();
                String i = fieldUpdater.get(this);
                first.join();
                actual.add(i);
            }
            assertThat(actual,is(expectedSet));
        }
    }


}
