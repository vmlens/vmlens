package com.vmlens.nottraced.agent.inttest;

import java.lang.reflect.InvocationTargetException;

public class ConstructorCallTest extends AbstractIntTest  {

    public void constructorParent() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        runTest("ConstructorParent");
    }

    public void constructorChild() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        runTest("ConstructorChild");
    }

    public void constructorWithField() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        runTest("ConstructorWithField");
    }

}
