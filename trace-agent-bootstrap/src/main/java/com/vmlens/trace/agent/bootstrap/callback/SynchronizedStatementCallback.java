package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.StaticMonitorRepository;
import com.vmlens.trace.agent.bootstrap.callback.state.MonitorIdAndOrder;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import gnu.trove.map.hash.TIntIntHashMap;

public class SynchronizedStatementCallback {

	private static final AnarsoftWeakHashMap<MonitorIdAndOrder> objectToOrder = new AnarsoftWeakHashMap<MonitorIdAndOrder>();
	private static final TIntIntHashMap staticIdToOrder = new TIntIntHashMap();

	public static void waitCall(Object in, int methodId) throws InterruptedException {
		try {
			monitorExit(in, methodId, -2);
			in.wait();
		} finally {
			monitorEnter(in, methodId, -1);
		}

	}

	public static void waitCall(Object in, long timeout, int methodId) throws InterruptedException {
		try {
			monitorExit(in, methodId, -2);
			in.wait(timeout);
		} finally {
			monitorEnter(in, methodId, -1);
		}

	}

	public static void waitCall(Object in, long timeout, int nanos, int methodId) throws InterruptedException {
		try {
			monitorExit(in, methodId, -2);
			in.wait(timeout, nanos);
		} finally {
			monitorEnter(in, methodId, -1);
		}

	}

	public static void synchronizedMethod(Object objectKey, int methodId) {
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
			// callbackStatePerThread.monitorCount++;
			monitorEnterInternal(objectKey, methodId, 0);

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void staticSynchronizedMethod(int id, int methodId) {
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
			// callbackStatePerThread.monitorCount++;
			int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);

			if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {

				monitorEnterStaticInternal(id, methodId, callbackStatePerThread, 0, slidingWindowId);

			}

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void synchronizedMethodExit(Object objectKey, int methodId) {
		try {
			monitorExit(objectKey, methodId, -1);

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void staticSynchronizedMethodExit(int id, int methodId) {
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
			// callbackStatePerThread.monitorCount--;

			int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);

			if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {
				monitorExitStatic_internal(id, methodId, callbackStatePerThread, slidingWindowId);

			}

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void monitorEnter(Object objectKey, int methodId, int position) {
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
			// callbackStatePerThread.monitorCount++;
			monitorEnterInternal(objectKey, methodId, position);

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void monitorExit(Object objectKey, int methodId, int position) {
		try {
			CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
			// callbackStatePerThread.monitorCount--;

			int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);

			if (CallbackState.isSlidingWindowTrace(slidingWindowId)) {

				if (objectKey instanceof java.lang.Class) {
					String className = ((java.lang.Class) objectKey).getName().replace('.', '/');
					int id = StaticMonitorRepository.getOrCreate(className);
					monitorExitStatic_internal(id, methodId, callbackStatePerThread, slidingWindowId);
					return;
				}

				int order = 0;
				MonitorIdAndOrder current = null;

				synchronized (objectToOrder) {
					// pruneKeys();

					current = objectToOrder.get(objectKey);

					if (current == null) {
						current = new MonitorIdAndOrder();
						current.id = MonitorIdAndOrder.getNewId();
					}

					order = current.order;
					current.order = current.order + 1;

					objectToOrder.put(objectKey, current);
				}

				callbackStatePerThread.programCount++;

				callbackStatePerThread.sendEvent.writeMonitorExitEventGen(slidingWindowId,
						callbackStatePerThread.programCount, order, current.id, callbackStatePerThread.methodCount,
						methodId,position);

				callbackStatePerThread.programCount++;

                ParallelizeFacade.beforeMonitorExit(callbackStatePerThread, current.id, methodId, position);

			}

		} catch (Throwable e) {
			AgentLogCallback.logException(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	private static void monitorEnterInternal(Object objectKey, int methodId, int position) {

		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

		int slidingWindowId = CallbackState.traceSyncStatements(callbackStatePerThread);

		if (!CallbackState.isSlidingWindowTrace(slidingWindowId)) {
			// callbackStatePerThread.needsToWaitAfterMethod++;
			return;

		}

		if (objectKey instanceof java.lang.Class) {
			String className = ((java.lang.Class) objectKey).getName().replace('.', '/');
			int id = StaticMonitorRepository.getOrCreate(className);
			monitorEnterStaticInternal(id, methodId, callbackStatePerThread, position, slidingWindowId);
			return;
		}

		callbackStatePerThread.programCount++;

		long currentThreadId = callbackStatePerThread.threadId;
		int currentProgramCounter = callbackStatePerThread.programCount;

		// Integer orderInteger = objectToOrder.get(objectKey);

		int order = 0;

		MonitorIdAndOrder current = null;

		synchronized (objectToOrder) {
			// pruneKeys();

			current = objectToOrder.get(objectKey);

			if (current == null) {
				current = new MonitorIdAndOrder();
				current.id = MonitorIdAndOrder.getNewId();
			}

			order = current.order;

			current.order = current.order + 1;

			objectToOrder.put(objectKey, current);
		}

		callbackStatePerThread.sendEvent.writeMonitorEnterEventGen(slidingWindowId, currentProgramCounter, order,
				current.id, callbackStatePerThread.methodCount, methodId, position);

		callbackStatePerThread.programCount++;

        ParallelizeFacade.onMonitorEnter(callbackStatePerThread, current.id);

	}

	private static void monitorEnterStaticInternal(int id, int methodId, CallbackStatePerThread callbackStatePerThread,
			int position, int slidingWindowId) {

		callbackStatePerThread.programCount++;

		int currentProgramCounter = callbackStatePerThread.programCount;

		int order = 0;

		synchronized (staticIdToOrder) {

			if (staticIdToOrder.contains(id)) {
				order = staticIdToOrder.get(id);
			}

			staticIdToOrder.put(id, order + 1);

		}

		if (id * -1 > 0) {
			System.out.println("wrong id " + id);
		}

		callbackStatePerThread.sendEvent.writeMonitorEnterEventGen(slidingWindowId, currentProgramCounter, order,
				id * -1, callbackStatePerThread.methodCount, methodId, position);

		callbackStatePerThread.programCount++;

        ParallelizeFacade.onMonitorEnter(callbackStatePerThread, id * -1);

	}

	private static void monitorExitStatic_internal(int id, int methodId, CallbackStatePerThread callbackStatePerThread,
			int slidingWindowId) {

        ParallelizeFacade.beforeMonitorExitStatic(callbackStatePerThread, slidingWindowId, methodId);

		int order = 0;

		synchronized (staticIdToOrder) {

			if (staticIdToOrder.contains(id)) {
				order = staticIdToOrder.get(id);
			}

			staticIdToOrder.put(id, order + 1);

		}

		if (id * -1 > 0) {
			System.out.println("wrong id " + id);
		}

		// public MonitorExitEventBootstrap(long threadId, int programCounter,int
		// methodCounter,int order,int monitorId) {

		callbackStatePerThread.programCount++;

		callbackStatePerThread.sendEvent.writeMonitorExitEventGen(slidingWindowId, callbackStatePerThread.programCount,
				order, id * -1, callbackStatePerThread.methodCount, methodId,0);

		callbackStatePerThread.programCount++;
	}

}
