package com.vmlens.trace.agent.bootstrap.callback.obj.gen;

import com.vmlens.trace.agent.bootstrap.callback.obj.*;

import java.util.*;

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;

/* templates/MapTemplateCallback.mustache */

public class MapCallbackGen extends MapCallback {
    public static java.lang.Object put(Map obj, java.lang.Object a0, java.lang.Object a1, int methodId) {
        java.lang.Object result = obj.put(a0, a1);

        if (obj instanceof java.util.HashMap) {
            access(obj, MemoryAccessType.IS_READ_WRITE, methodId);
        } else {

        }
        return result;
    }

    public static void putAll(Map obj, java.util.Map a0, int methodId) {
        obj.putAll(a0);

        if (obj instanceof java.util.HashMap) {
            access(obj, MemoryAccessType.IS_READ_WRITE, methodId);
        } else {

        }

    }

    public static java.lang.Object remove(Map obj, java.lang.Object a0, int methodId) {
        java.lang.Object result = obj.remove(a0);

        if (obj instanceof java.util.HashMap) {
            access(obj, MemoryAccessType.IS_READ_WRITE, methodId);
        } else {

        }
        return result;
    }

    public static void clear(Map obj, int methodId) {
        obj.clear();

        if (obj instanceof java.util.HashMap) {
            access(obj, MemoryAccessType.IS_READ_WRITE, methodId);
        } else {

        }

    }

    public static int size(Map obj, int methodId) {
        int result = obj.size();

        if (obj instanceof java.util.HashMap) {
            access(obj, MemoryAccessType.IS_READ, methodId);
        } else {

        }
        return result;
    }

    public static boolean isEmpty(Map obj, int methodId) {
        boolean result = obj.isEmpty();

        if (obj instanceof java.util.HashMap) {
            access(obj, MemoryAccessType.IS_READ, methodId);
        } else {

        }
        return result;
    }

    public static java.lang.Object get(Map obj, java.lang.Object a0, int methodId) {
        java.lang.Object result = obj.get(a0);

        if (obj instanceof java.util.HashMap) {
            access(obj, MemoryAccessType.IS_READ, methodId);
        } else {

        }
        return result;
    }

    public static boolean containsKey(Map obj, java.lang.Object a0, int methodId) {
        boolean result = obj.containsKey(a0);

        if (obj instanceof java.util.HashMap) {
            access(obj, MemoryAccessType.IS_READ, methodId);
        } else {

        }
        return result;
    }

    public static boolean containsValue(Map obj, java.lang.Object a0, int methodId) {
        boolean result = obj.containsValue(a0);

        if (obj instanceof java.util.HashMap) {
            access(obj, MemoryAccessType.IS_READ, methodId);
        } else {

        }
        return result;
    }

    public static java.util.Set entrySet(Map obj, int methodId) {
        java.util.Set result = obj.entrySet();
        createDelegate(result, obj);
        return result;
    }

    public static java.util.Set keySet(Map obj, int methodId) {
        java.util.Set result = obj.keySet();
        createDelegate(result, obj);
        return result;
    }
}
 