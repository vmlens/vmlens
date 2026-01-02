package com.vmlens.inttest;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.AllInterleavingsBuilder;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestRecord {

    private volatile SimpleRecord record;

    @Test
    public void testRecord() throws InterruptedException {
        Set<SimpleRecord> expectedSet = new HashSet<>();
        expectedSet.add(new SimpleRecord("test", 4));
        expectedSet.add(new SimpleRecord("testTwo", 12));

        Set<SimpleRecord> actual = new HashSet<>();

        try (AllInterleavings allInterleavings = new AllInterleavingsBuilder()
                .build("testRecord")) {
            while (allInterleavings.hasNext()) {
                record = null;
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        record = new SimpleRecord("test", 4);
                    }
                };
                first.start();
                record = new SimpleRecord("testTwo", 12);
                first.join();
                actual.add(record);
            }
        }
        assertThat(actual,is(expectedSet));
    }


}
