package com.vmlens.trace.agent.bootstrap.callback;

/**
 * Used to do not trace inside classloader
 */
public class StackTraceBasedFilterCallback {

    public static void onMethodExitDoNotTrace() {
        CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;
    }

    public static void onMethodEnterDoNotTrace() {
        CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;
    }

    public static void onMethodExitDoTrace() {
        CallbackState.callbackStatePerThread.get().stackTraceBasedDoTrace--;
    }

    public static void onMethodEnterDoTrace() {
        CallbackState.callbackStatePerThread.get().stackTraceBasedDoTrace++;
    }
}

