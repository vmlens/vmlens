package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class TestMockito {

    private volatile int j = 0;

    @Test
    public void testReadWrite() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);

        Set<Integer> actual = new HashSet<>();
        List mocked = mock(List.class);
        try(AllInterleavings allInterleavings = new AllInterleavings("testMockito")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                Thread first = new Thread(() -> j++);
                first.start();
                mocked.add("String");
                j++;
                first.join();
                actual.add(j);
            }
            assertThat(actual,is(expectedSet));
        }
    }

}
