package com.vmlens.nottraced.agent.write;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import static com.vmlens.trace.agent.bootstrap.event.queue.EventQueueSingleton.eventQueue;
import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.incrementInsideVMLens;

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
        incrementInsideVMLens();

        long timerForDeadlockDetection = System.currentTimeMillis();
        long timerForDemonThreadDetection = System.currentTimeMillis();
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        boolean deadlockLogged = false;

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
                    if ((timerForDemonThreadDetection + 500) < System.currentTimeMillis()) {
                        timerForDemonThreadDetection = System.currentTimeMillis();
                        long[] threadIds = bean.getAllThreadIds();
                        ThreadInfo[] infos = bean.getThreadInfo(threadIds);
                        boolean nonDemonThreadActive = false;
                        for (ThreadInfo info : infos) {
                            /*  If a thread of the given ID is a virtual thread, is not alive, or does not exist,
                             *  the corresponding element in the returned array will contain null.
                             */
                            if(info != null) {
                                if(! info.isDaemon() &&
                                        info.getThreadState() != Thread.State.TERMINATED &&
                                        ! ThreadLocalForParallelize.ANARSOFT_THREAD_NAME.equals(info.getThreadName()) &&
                                        ! info.getThreadName().startsWith("DestroyJavaVM"))  {
                                    nonDemonThreadActive = true;
                                }
                            }
                        }
                        if(! nonDemonThreadActive) {
                            close();
                            process = false;
                            setPoisonedEventReceived();
                        }
                    }
                }

                if (!deadlockLogged) {
                    if ((timerForDeadlockDetection + 500) < System.currentTimeMillis()) {
                        timerForDeadlockDetection = System.currentTimeMillis();
                        long[] threadIds = bean.findDeadlockedThreads();
                        if (threadIds != null) {
                            ThreadInfo[] infos = bean.getThreadInfo(threadIds, Integer.MAX_VALUE);
                            for (ThreadInfo info : infos) {
                                System.err.println("Deadlock detected on thread: " + info.getThreadName());
                                for (StackTraceElement element : info.getStackTrace()) {
                                    System.err.println("   " + element.toString());
                                }
                            }
                            deadlockLogged = true;
                        }
                    }
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
            }
        }
    }
}
