package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.StaticMonitorRepository;
import com.vmlens.trace.agent.bootstrap.callback.state.MonitorIdAndOrder;
import com.vmlens.trace.agent.bootstrap.callback.synchronizedStatement.SynchronizedStatementCallbackImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import gnu.trove.map.hash.TIntIntHashMap;

public class SynchronizedStatementCallback {

    private static volatile SynchronizedStatementCallbackImpl impl = new SynchronizedStatementCallbackImpl();
	public static void waitCall(Object in, int methodId) throws InterruptedException {
        impl.waitCall(in, methodId);
    }
	public static void waitCall(Object in, long timeout, int methodId) throws InterruptedException {
        impl.waitCall(in, timeout, methodId);
    }
	public static void waitCall(Object in, long timeout, int nanos, int methodId) throws InterruptedException {
        impl.waitCall(in, timeout, nanos, methodId);
    }
	public static void synchronizedMethod(Object objectKey, int methodId) {
        impl.synchronizedMethod(objectKey, methodId);
    }
	public static void staticSynchronizedMethod(int id, int methodId) {
        impl.staticSynchronizedMethod(id, methodId);
    }
	public static void synchronizedMethodExit(Object objectKey, int methodId) {
        impl.synchronizedMethodExit(objectKey, methodId);
    }

    public static void staticSynchronizedMethodExit(int id, int methodId) {
        impl.staticSynchronizedMethodExit(id, methodId);
    }

    public static void monitorEnter(Object objectKey, int methodId, int position) {
        impl.monitorEnter(objectKey, methodId, position);
    }

    public static void monitorExit(Object objectKey, int methodId, int position) {
        impl.monitorExit(objectKey, methodId, position);
    }

    public static void setImpl(SynchronizedStatementCallbackImpl impl) {
        SynchronizedStatementCallback.impl = impl;
    }
}
