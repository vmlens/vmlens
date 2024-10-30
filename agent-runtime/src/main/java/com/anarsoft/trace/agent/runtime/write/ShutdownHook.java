package com.anarsoft.trace.agent.runtime.write;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.ParallelizeBridgeForCallbackImpl;

public class ShutdownHook extends Thread {

    private final WriteEventToFile writeEventToFile;

    public ShutdownHook(WriteEventToFile writeEventToFile) {
        super(ThreadLocalForParallelize.ANARSOFT_THREAD_NAME);
        this.writeEventToFile = writeEventToFile;
    }

    public static synchronized void addShutdownHook(WriteEventToFile writeEventToFile) {
        ShutdownHook shutdownHook = new ShutdownHook(writeEventToFile);
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    public void stopProcessing() {
        try {
            ParallelizeBridgeForCallbackImpl.eventQueue.offer(new PoisonedEvent());
            writeEventToFile.waitForPoisonedEventReceived();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void run() {

        stopProcessing();
	}
}
