package com.vmlens.test.maven.plugin.reflection;

import com.vmlens.api.AllInterleavings;
import org.junit.Ignore;
import org.junit.Test;


import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestReflectionField {

    public volatile int j = 0;

    @Test
    @Ignore
    public void testReadWrite() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);
        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testReflectionField")) {
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
            Field field = this.getClass().getField("j");
            Integer value = (Integer) field.get(this);
            field.set(this,value + 1);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
