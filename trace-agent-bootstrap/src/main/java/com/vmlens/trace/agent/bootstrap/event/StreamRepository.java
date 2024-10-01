package com.vmlens.trace.agent.bootstrap.event;

import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

public class StreamRepository {

    public static final String THREAD_AND_LOOP_DESCRIPTION = "threadandloop";
    public static final String DESCRIPTION = "description";
    public static final String AGENTLOG = "agentlog";
    public static final String METHOD_EVENTS = "method";
    public static final String SYNC_ACTIONS = "syncactions";
    public static final String FIELD_EVENTS = "field";
    public static final String DIRECT_MEMORY = "directmemory";
    public static final String INTERLEAVE = "interleave";


    public final StreamWrapperWithoutLoopIdAndRunId threadName;
    public final StreamWrapperWithoutLoopIdAndRunId description;
    public final StreamWrapperWithoutLoopIdAndRunId agentLog;
    public final StreamWrapperWithLoopIdAndRunId method;
    public final StreamWrapperWithLoopIdAndRunId syncActions;
    public final StreamWrapperWithLoopIdAndRunId field;
    public final StreamWrapperWithLoopIdAndRunId directMemory;
    public final StreamWrapperWithLoopIdAndRunId interleave;

    private final TLinkedList<AbstractStreamWrapper> streamList = new TLinkedList<AbstractStreamWrapper>();

    public StreamRepository(String eventDir) {
        this.threadName = create(eventDir, THREAD_AND_LOOP_DESCRIPTION, streamList);
        this.description = create(eventDir, DESCRIPTION, streamList);
        this.agentLog = create(eventDir, AGENTLOG, streamList);

        this.method = createWithLoopIdAndRunId(eventDir, METHOD_EVENTS, streamList);
        this.syncActions = createWithLoopIdAndRunId(eventDir, SYNC_ACTIONS, streamList);
        this.field = createWithLoopIdAndRunId(eventDir, FIELD_EVENTS, streamList);
        this.directMemory = createWithLoopIdAndRunId(eventDir, DIRECT_MEMORY, streamList);
        this.interleave = createWithLoopIdAndRunId(eventDir, INTERLEAVE, streamList);
    }

    public void flush() throws Exception {
        Iterator<AbstractStreamWrapper> iterator = streamList.iterator();
        while (iterator.hasNext()) {
            iterator.next().flush();
        }
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
