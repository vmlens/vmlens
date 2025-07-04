package com.vmlens.inttest.interleave;

import java.lang.reflect.Field;

public class ReflectionTest {

    public void test() throws NoSuchFieldException, IllegalAccessException {
        Field f = this.getClass().getField("test");
        f.get(this);
    }


}
