package com.anarsoft.trace.agent.runtime.write;

import com.anarsoft.trace.agent.runtime.process.AgentController;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

public class WriteEventToFile implements Runnable {
    private final StreamRepository streamRepository;
    private final AgentController agentController;
    private boolean shutdownHookAdded = false;

    private WriteEventToFile(StreamRepository streamRepository, AgentController agentController) {
        super();
        this.streamRepository = streamRepository;
        this.agentController = agentController;
    }

    public static void startWriteEventToFileThread(String eventDir, AgentController agentController) {
        StreamRepository streamRepository = new StreamRepository(eventDir);
        new WriteEventToFileThreadFactory().newThread(new WriteEventToFile(streamRepository, agentController)).start();
    }

    private void testAndAddShutdownHook() {
        if (!this.shutdownHookAdded) {
            this.shutdownHookAdded = true;
            ShutdownHook.addShutdownHook();
        }
    }

    public void close() {
        try {
            streamRepository.close();
            agentController.writeStopState(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        testAndAddShutdownHook();
        boolean process = true;
        while (process) {
            try {
                Object in = CallbackState.eventQueue.queue().poll();
                if (in != null) {
                    if (in instanceof PoisonedEvent) {
                        process = false;
                    } else if (in instanceof SerializableEvent) {
                        SerializableEvent staticEvent = (SerializableEvent) in;
                        staticEvent.serialize(streamRepository);
                    } else {
                        throw new RuntimeException("unknown " + in.getClass());
                    }
                } else {
                    Thread.yield();
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
            close();
        }
    }
}
