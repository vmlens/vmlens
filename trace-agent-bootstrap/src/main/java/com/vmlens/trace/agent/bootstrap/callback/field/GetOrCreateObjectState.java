package com.vmlens.trace.agent.bootstrap.callback.field;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;


public class GetOrCreateObjectState {

    private static final sun.misc.Unsafe UNSAFE;

    static {
        try {
            Constructor<sun.misc.Unsafe> unsafeConstructor = sun.misc.Unsafe.class.getDeclaredConstructor();
            unsafeConstructor.setAccessible(true);
            UNSAFE = unsafeConstructor.newInstance();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ObjectState getOrCreate(Object obj) {
        long offset = getFieldOffset(obj.getClass());
        ObjectState current = get(obj, offset);

        if (current != null) {
            return current;
        }

        current = new ObjectState();

        if (setObjectStateVolatileIfNotThere(obj, offset, current)) {
            return current;
        }

        return get(obj, offset);
    }


    private long getFieldOffset(Class cl) {
        return getFieldOffset(cl, "_pAnarsoft_");
    }

    private long getFieldOffset(Class cl, String name) {
        try {
            Field f = cl.getDeclaredField(name);
            return UNSAFE.objectFieldOffset(f);

        } catch (NoSuchFieldException e) {
            return -1;
        } catch (SecurityException e) {
            return -1;
        }
    }


    private ObjectState get(Object obj, long offset) {
        return (ObjectState) UNSAFE.getObjectVolatile(obj, offset);
    }

    private boolean setObjectStateVolatileIfNotThere(Object obj, long offset, ObjectState newModeStateObject) {
        return UNSAFE.compareAndSwapObject(obj, offset, null, newModeStateObject);
    }


}
