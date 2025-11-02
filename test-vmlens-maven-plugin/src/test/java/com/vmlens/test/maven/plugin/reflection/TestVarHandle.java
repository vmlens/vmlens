package com.vmlens.test.maven.plugin.reflection;

import com.vmlens.api.AllInterleavings;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestVarHandle {

    public volatile int j = 0;

    // A VarHandle for the field 'counter'
    private static final VarHandle J_HANDLE;

    static {
        try {
            J_HANDLE = MethodHandles.lookup()
                    .findVarHandle(TestVarHandle.class, "j", int.class);
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @Ignore
    @Test
    public void testReadWrite() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(0);
        expectedSet.add(5);

        Set<Integer> actual = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testVarHandle")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                Thread first = new Thread() {
                    @Override
                    public void run() {
                        J_HANDLE.setVolatile(TestVarHandle.this,5);
                    }
                };
                first.start();
                int i = (Integer) J_HANDLE.get(this);
                first.join();
                actual.add(i);
            }
            assertThat(actual,is(expectedSet));
        }
    }

}
