package com.anarsoft.trace.agent.runtime;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestAgentClassFileTransformer {

    @Test
    public void transformTestTwoVolatileFields() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        ClassLoader cl = new ClassLoaderForTransformation(null);
        Object testTwoVolatileFields = cl.loadClass("com.vmlens.test.volatileFields.fixed.TestTwoVolatileFields").newInstance();
        for (Method m : testTwoVolatileFields.getClass().getMethods()) {
            if (m.getName().equals("testUpdate")) {
                m.invoke(testTwoVolatileFields);
            }
        }
    }
}
