package com.vmlens.test.guineapig;

public class ThreadPoolExecutorGuineaPig {

    public void update() {
        execute(null);
        shutdown();
    }

    public void execute(Runnable command) {
        if(call(this,command)) {
            return;
        }

        if (command == null) {
            throw new NullPointerException();
        }
    }

    public void shutdown() {

    }

    public void notTraced() {

    }


    private static boolean call(Object a , Object n) {
        return false;
    }
}