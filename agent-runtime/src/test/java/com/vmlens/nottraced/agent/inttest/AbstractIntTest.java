package com.vmlens.nottraced.agent.inttest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AbstractIntTest {

    protected void runTest(String className) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        ClassLoader cl = new ClassLoaderForTransformation(this.getClass().getClassLoader());
        Object objectUnderTest = cl.loadClass("com.vmlens.test.guineapig." + className).newInstance();
        for (Method m : objectUnderTest.getClass().getMethods()) {
            if (m.getName().equals("update")) {
                m.invoke(objectUnderTest);
            }
        }
    }

}
