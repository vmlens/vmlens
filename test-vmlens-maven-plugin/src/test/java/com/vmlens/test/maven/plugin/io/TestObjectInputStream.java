package com.vmlens.test.maven.plugin.io;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestObjectInputStream {

    private String firstString;

    @Test
    public void testRead() throws InterruptedException, IOException {
        Set<String> expectedSet = new HashSet<>();
        expectedSet.add("abcd");
        expectedSet.add("cdab");

        Set<String> actualSet = new HashSet<>();
        try(AllInterleavings allInterleaving = new AllInterleavings("testObjectInputStream")) {
            while (allInterleaving.hasNext()) {
                firstString = null;
                LineNumberReader stream = new LineNumberReader(new StringReader("ab\ncd"));
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        try {
                            firstString = stream.readLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                };
                first.start();
                String secondString = stream.readLine();
                first.join();
                actualSet.add(firstString+secondString);
            }
            assertThat(actualSet,is(expectedSet));
        }
    }

}
