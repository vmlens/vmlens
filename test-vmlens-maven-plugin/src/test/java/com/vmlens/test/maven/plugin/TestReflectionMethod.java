package com.vmlens.test.maven.plugin;

import com.vmlens.api.AllInterleavings;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestReflectionMethod {

    private volatile int j = 0;

    @Test
    public void testReadWrite() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);
        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testReflectionMethod")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        incrementWitchReflection();
                    }
                };
                first.start();
                incrementWitchReflection();
                first.join();
                countSet.add(j);
            }
            assertThat(countSet,is(expectedSet));
        }
    }

    private void incrementWitchReflection() {
        try {
            Method method = this.getClass().getMethod("increment");
            method.invoke(this);

        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public void increment() {
        j++;
    }


}
