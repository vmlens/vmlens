package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventVisitor;

import java.nio.ByteBuffer;

public class RuntimeEventGuineaPig implements RuntimeEvent {

    private int threadIndex;
    private int loopId;
    private int runId;
    private int runPosition;

    public int threadIndex() {
        return threadIndex;
    }

    @Override
    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public int loopId() {
        return loopId;
    }

    @Override
    public void setLoopId(int loopId) {
        this.loopId = loopId;
    }

    public int runId() {
        return runId;
    }

    @Override
    public void setRunId(int runId) {
        this.runId = runId;
    }

    public int runPosition() {
        return runPosition;
    }

    @Override
    public void setRunPosition(int runPosition) {
        this.runPosition = runPosition;
    }

    @Override
    public void serialize(StreamRepository streamRepository) throws Exception {

    }

    @Override
    public void serialize(ByteBuffer buffer) throws Exception {

    }

    @Override
    public void setMethodCounter(int methodCounter) {

    }

    @Override
    public void accept(RuntimeEventVisitor visitor) {

    }
}
