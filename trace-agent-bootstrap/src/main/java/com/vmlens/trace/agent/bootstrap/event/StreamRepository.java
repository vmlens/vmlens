package com.vmlens.trace.agent.bootstrap.event;

import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

public class StreamRepository {
public final StreamWrapperWithoutSlidingWindow threadName;
    public final StreamWrapperWithoutSlidingWindow description;
    public final StreamWrapperWithoutSlidingWindow stackTrace;
    public final StreamWrapperWithoutSlidingWindow agentLog;
    public final StreamWrapperWithoutSlidingWindow className;
    public final StreamWrapperWithSlidingWindow firstWrite;
    public final StreamWrapperWithSlidingWindow method;
    public final StreamWrapperWithSlidingWindow syncActions;
    public final StreamWrapperWithSlidingWindow field;
    public final StreamWrapperWithSlidingWindow monitor;
    public final StreamWrapperWithSlidingWindow directMemory;
    public final StreamWrapperWithSlidingWindow interleave;

    private final TLinkedList<AbstractStreamWrapper> streamList = new TLinkedList<AbstractStreamWrapper>();

    public StreamRepository(String eventDir) {
        this.threadName = new StreamWrapperWithoutSlidingWindow(eventDir, "threadName", streamList);
        this.description = new StreamWrapperWithoutSlidingWindow(eventDir, "description", streamList);
        this.stackTrace = new StreamWrapperWithoutSlidingWindow(eventDir, "stackTrace", streamList);
        this.agentLog = new StreamWrapperWithoutSlidingWindow(eventDir, "agentLog", streamList);
        this.className = new StreamWrapperWithoutSlidingWindow(eventDir, "className", streamList);

        this.firstWrite = new StreamWrapperWithSlidingWindow(eventDir, "firstWrite", streamList);
        this.method = new StreamWrapperWithSlidingWindow(eventDir, "method", streamList);
        this.syncActions = new StreamWrapperWithSlidingWindow(eventDir, "syncActions", streamList);
        this.field = new StreamWrapperWithSlidingWindow(eventDir, "field", streamList);
        this.monitor = new StreamWrapperWithSlidingWindow(eventDir, "monitor", streamList);
        this.directMemory = new StreamWrapperWithSlidingWindow(eventDir, "directMemory", streamList);

        this.interleave = new StreamWrapperWithSlidingWindow(eventDir, "interleave", streamList);
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
