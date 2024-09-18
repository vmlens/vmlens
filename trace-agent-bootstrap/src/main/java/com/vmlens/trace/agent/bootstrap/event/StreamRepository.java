package com.vmlens.trace.agent.bootstrap.event;

import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

public class StreamRepository {
    public final StreamWrapperWithoutLoopIdAndRunId threadName;
    public final StreamWrapperWithoutLoopIdAndRunId description;
    public final StreamWrapperWithoutLoopIdAndRunId stackTrace;
    public final StreamWrapperWithoutLoopIdAndRunId agentLog;
    public final StreamWrapperWithoutLoopIdAndRunId className;
    public final StreamWrapperWithLoopIdAndRunId firstWrite;
    public final StreamWrapperWithLoopIdAndRunId method;
    public final StreamWrapperWithLoopIdAndRunId syncActions;
    public final StreamWrapperWithLoopIdAndRunId field;
    public final StreamWrapperWithLoopIdAndRunId monitor;
    public final StreamWrapperWithLoopIdAndRunId directMemory;
    public final StreamWrapperWithLoopIdAndRunId interleave;

    private final TLinkedList<AbstractStreamWrapper> streamList = new TLinkedList<AbstractStreamWrapper>();

    public StreamRepository(String eventDir) {
        this.threadName = new StreamWrapperWithoutLoopIdAndRunId(eventDir, "threadName", streamList);
        this.description = new StreamWrapperWithoutLoopIdAndRunId(eventDir, "description", streamList);
        this.stackTrace = new StreamWrapperWithoutLoopIdAndRunId(eventDir, "stackTrace", streamList);
        this.agentLog = new StreamWrapperWithoutLoopIdAndRunId(eventDir, "agentLog", streamList);
        this.className = new StreamWrapperWithoutLoopIdAndRunId(eventDir, "className", streamList);

        this.firstWrite = new StreamWrapperWithLoopIdAndRunId(eventDir, "firstWrite", streamList);
        this.method = new StreamWrapperWithLoopIdAndRunId(eventDir, "method", streamList);
        this.syncActions = new StreamWrapperWithLoopIdAndRunId(eventDir, "syncActions", streamList);
        this.field = new StreamWrapperWithLoopIdAndRunId(eventDir, "field", streamList);
        this.monitor = new StreamWrapperWithLoopIdAndRunId(eventDir, "monitor", streamList);
        this.directMemory = new StreamWrapperWithLoopIdAndRunId(eventDir, "directMemory", streamList);

        this.interleave = new StreamWrapperWithLoopIdAndRunId(eventDir, "interleave", streamList);
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
}
