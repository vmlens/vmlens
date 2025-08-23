package com.vmlens.test.guineapig;

public class LoadClassExample {


    public static void call() {
        callback(vmlensGeneratedLoadClass());
    }

    private static Class vmlensGeneratedLoadClass() {
        try{
            return Class.forName("com.vmlens.test.guineapig.LoadClassExample");
        }
        catch(ClassNotFoundException exp) {
            return null;
        }
    }

    public static void callback(Class cl) {

    }

}
