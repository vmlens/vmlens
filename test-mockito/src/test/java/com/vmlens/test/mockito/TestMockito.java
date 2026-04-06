package com.vmlens.test.mockito;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestMockito {

    private volatile int j = 0;

    @Test
    public void testInside() {

        try(AllInterleavings allInterleavings = new AllInterleavings("testInside")) {
            while (allInterleavings.hasNext()) {
                Map<String,Object> map = mock(Map.class);
                when(map.get(anyString())).thenReturn("test");

                runParallel(  () -> {map.put("",""); } , () -> {map.get("");}  );

            }
        }
    }

    @Test
    public void testOutside() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);

        Set<Integer> actual = new HashSet<>();
        List mocked = mock(List.class);
        try(AllInterleavings allInterleavings = new AllInterleavings("testOutside")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                Thread first = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        j++;
                    }
                });
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
