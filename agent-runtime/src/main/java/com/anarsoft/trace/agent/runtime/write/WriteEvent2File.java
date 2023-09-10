package com.anarsoft.trace.agent.runtime.write;


import com.anarsoft.trace.agent.runtime.ShutdownHook;
import com.anarsoft.trace.agent.runtime.process.AgentController;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.event.NewSlidingWindowId;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.StaticEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.threadQueue.EventSink;
import com.vmlens.trace.agent.bootstrap.util.Constants;

import java.io.DataOutputStream;


public class WriteEvent2File
        implements EventSink {
    public static volatile int lastWrittenSlidingWindowId = -1;
    private final String eventDir;
    private final StreamRepository streamRepository;
    private final AgentController agentController;
    private final Object SLIDING_WINDOW_ID_LOCK = new Object();
    private boolean shutdownHookAdded = false;
    private long lastTimeFlush = 0L;
    private volatile int internalQueuesFull;
    private int slidingWindowIdFromWriteCount = 1;


    public WriteEvent2File(String eventDir, StreamRepository streamRepository, AgentController agentController) {
        super();
        this.eventDir = eventDir;
        this.streamRepository = streamRepository;
        this.agentController = agentController;

    }

    private void testAndAddShutdownHook() {
        if (!this.shutdownHookAdded) {
            this.shutdownHookAdded = true;
            ShutdownHook.addShutdownHook(eventDir, agentController);
        }
    }

    public void close(int emptyQueueCount, int stoppedCount) {
        try {
            DataOutputStream stream = streamRepository.agentLog.getStream();
            stream.writeUTF("emptyQueueCount:" + emptyQueueCount + ",stoppedCount:" + stoppedCount);
            stream.flush();
            streamRepository.close();
            agentController.writeStopState(lastWrittenSlidingWindowId);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void flushOnIdle() throws Exception {
        if (System.currentTimeMillis() > (1000 + lastTimeFlush) && CallbackState.slidingWindow > -1) {
            lastTimeFlush = System.currentTimeMillis();
            streamRepository.flush();
            lastWrittenSlidingWindowId = CallbackState.slidingWindow;
        }

    }

    @Override
    public void consume(Object in) {
        testAndAddShutdownHook();

        try {
            if (in instanceof NewSlidingWindowId) {
                synchronized (SLIDING_WINDOW_ID_LOCK) {
                    lastWrittenSlidingWindowId = CallbackState.slidingWindow;
                    CallbackState.slidingWindow++;
                }
            } else if (in instanceof StaticEvent) {
                StaticEvent staticEvent = (StaticEvent) in;
                staticEvent.serialize(streamRepository);
            } else if (in instanceof RuntimeEvent) {
                RuntimeEvent runtimeEvent = (RuntimeEvent) in;
                runtimeEvent.serialize(streamRepository);
            } else {
                throw new RuntimeException("unknown " + in.getClass());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getSlidingWindowId(int writtenEventCount) {

        int currentSlidingWindowId = CallbackState.slidingWindow;

        if (currentSlidingWindowId == -1) {
            return currentSlidingWindowId;
        }

        if (writtenEventCount < slidingWindowIdFromWriteCount * Constants.NEW_SLIDING_WINDOW_EVERY_N_EVENTS) {
            return currentSlidingWindowId;
        }

        slidingWindowIdFromWriteCount++;
        currentSlidingWindowId++;
        CallbackState.slidingWindow++;

        return currentSlidingWindowId;
    }

    @Override
    public void onStop() {

        synchronized (SLIDING_WINDOW_ID_LOCK) {

            if (CallbackState.slidingWindow > -1) {
                lastWrittenSlidingWindowId = CallbackState.slidingWindow;
            }
            CallbackState.slidingWindow = -1;
        }
    }

    @Override
    public void onWait() {
        try {
            flushOnIdle();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
