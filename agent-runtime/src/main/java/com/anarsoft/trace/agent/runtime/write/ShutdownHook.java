package com.anarsoft.trace.agent.runtime.write;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

import static com.vmlens.trace.agent.bootstrap.event.queue.EventQueueSingleton.eventQueue;

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
            eventQueue.offer(new PoisonedEvent());
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
