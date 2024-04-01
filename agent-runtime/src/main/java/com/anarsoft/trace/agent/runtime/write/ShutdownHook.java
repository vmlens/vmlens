package com.anarsoft.trace.agent.runtime.write;

import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThreadForParallelize;

public class ShutdownHook extends Thread {
    public ShutdownHook() {
        super(CallbackStatePerThreadForParallelize.ANARSOFT_THREAD_NAME);
    }

    public static synchronized void addShutdownHook() {
        ShutdownHook shutdownHook = new ShutdownHook();
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    public void stopProcessing() {
        try {
            CallbackState.eventQueue.offer(new PoisonedEvent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void run() {

        stopProcessing();
	}
}
