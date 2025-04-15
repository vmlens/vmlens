package com.anarsoft.trace.agent.runtime.write;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

import static com.vmlens.trace.agent.bootstrap.event.queue.EventQueueSingleton.eventQueue;
import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.startProcess;

public class WriteEventToFile implements Runnable {
    private final StreamRepository streamRepository;
    private boolean shutdownHookAdded = false;
    private final Object poisonedEventReceivedMonitor = new Object();
    private boolean poisonedEventReceived = false;

    private WriteEventToFile(StreamRepository streamRepository) {
        super();
        this.streamRepository = streamRepository;
    }

    public static void startWriteEventToFileThread(String eventDir) {
        StreamRepository streamRepository = new StreamRepository(eventDir);
        new WriteEventToFileThreadFactory().newThread(new WriteEventToFile(streamRepository)).start();
    }

    private void testAndAddShutdownHook() {
        if (!this.shutdownHookAdded) {
            this.shutdownHookAdded = true;
            ShutdownHook.addShutdownHook(this);
        }
    }

    public void close() {
        try {
            streamRepository.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // we never want tracing in this thread
        startProcess();

        testAndAddShutdownHook();
        boolean process = true;
        while (process) {
            try {
                SerializableEvent in = eventQueue.take();
                if (in != null) {
                    if (in instanceof PoisonedEvent) {
                        close();
                        process = false;
                        setPoisonedEventReceived();
                    } else {
                        in.serialize(streamRepository);
                    }
                } else {
                    Thread.yield();
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
    private void setPoisonedEventReceived() {
        synchronized (poisonedEventReceivedMonitor) {
            poisonedEventReceived = true;
            poisonedEventReceivedMonitor.notifyAll();
        }
    }
    public void waitForPoisonedEventReceived() throws InterruptedException {
        synchronized (poisonedEventReceivedMonitor) {
            while (!poisonedEventReceived) {
                poisonedEventReceivedMonitor.wait();
                ;
            }
        }
    }
}
