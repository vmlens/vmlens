package com.vmlens.trace.agent.bootstrap.event.stream;

import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

public class StreamRepository {

    public static final String THREAD_AND_LOOP_DESCRIPTION = "threadandloop";
    public static final String DESCRIPTION = "description";
    public static final String AGENTLOG = "agentlog";
    public static final String METHOD_EVENTS = "method";
    public static final String INTERLEAVE = "interleave";
    public static final String NON_VOLATILE = "nonvolatile";
    public static final String DIRECT_MEMORY = "directmemory";
    public static final String CONTROL = "control";
    public static final String LOOP_CONTROL = "loopcontrol";
    public static final String AUTOMATIC_TEST = "automatictest";

    public final StreamWrapperWithoutLoopIdAndRunId threadName;
    public final StreamWrapperWithoutLoopIdAndRunId description;
    public final StreamWrapperWithoutLoopIdAndRunId agentLog;
    public final StreamWrapperWithoutLoopIdAndRunId loopControl;
 

    public final StreamWrapperWithLoopIdAndRunId method;
    public final StreamWrapperWithLoopIdAndRunId interleave;
    public final StreamWrapperWithLoopIdAndRunId nonVolatile;
    public final StreamWrapperWithLoopIdAndRunId directMemory;
    public final StreamWrapperWithLoopIdAndRunId control;
    public final StreamWrapperWithLoopIdAndRunId automaticTest;

    private final TLinkedList<AbstractStreamWrapper> streamList = new TLinkedList<AbstractStreamWrapper>();

    public StreamRepository(String eventDir) {
        this.threadName = create(eventDir, THREAD_AND_LOOP_DESCRIPTION, streamList);
        this.description = create(eventDir, DESCRIPTION, streamList);
        this.agentLog = create(eventDir, AGENTLOG, streamList);
        this.loopControl = create(eventDir, LOOP_CONTROL, streamList);
        this.automaticTest = createWithLoopIdAndRunId(eventDir, AUTOMATIC_TEST, streamList);

        this.method = createWithLoopIdAndRunId(eventDir, METHOD_EVENTS, streamList);
        this.interleave = createWithLoopIdAndRunId(eventDir, INTERLEAVE, streamList);
        this.nonVolatile = createWithLoopIdAndRunId(eventDir, NON_VOLATILE, streamList);
        this.directMemory = createWithLoopIdAndRunId(eventDir, DIRECT_MEMORY, streamList);
        this.control = createWithLoopIdAndRunId(eventDir, CONTROL, streamList);
    }

    public void close() throws Exception {
        Iterator<AbstractStreamWrapper> iterator = streamList.iterator();
        while (iterator.hasNext()) {
            iterator.next().close();
        }
    }

    private static StreamWrapperWithLoopIdAndRunId
    createWithLoopIdAndRunId(String eventDir, String name,
                             TLinkedList<AbstractStreamWrapper> streamList) {
        StreamWrapperWithLoopIdAndRunId stream = new StreamWrapperWithLoopIdAndRunId(eventDir, name);
        streamList.add(stream);
        return stream;
    }

    private static StreamWrapperWithoutLoopIdAndRunId
    create(String eventDir, String name,
           TLinkedList<AbstractStreamWrapper> streamList) {
        StreamWrapperWithoutLoopIdAndRunId stream = new StreamWrapperWithoutLoopIdAndRunId(eventDir, name);
        streamList.add(stream);
        return stream;
    }
}
