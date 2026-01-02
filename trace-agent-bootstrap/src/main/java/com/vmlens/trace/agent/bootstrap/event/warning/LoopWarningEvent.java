package com.vmlens.trace.agent.bootstrap.event.warning;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.gen.LoopWarningEventGen;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

public class LoopWarningEvent extends LoopWarningEventGen implements SerializableEvent  {

    public static final int TEST_BLOCKED = 1;
    public static final int SYNC_ACTION_LOOP = 2;
    public static final int NON_VOLATILE_LOOP = 3;
    public static final int NON_YET_IMPLEMENTED = 4;
    public static final int MAXIMUM_ITERATIONS_REACHED = 5;
    public static final int CYCLES_REMOVED = 6;

    public static LoopWarningEvent testBlocked() {
        return new LoopWarningEvent(TEST_BLOCKED, 0);
    }

    public static LoopWarningEvent syncActionLoop() {
        return new LoopWarningEvent(SYNC_ACTION_LOOP, 0);
    }

    public static LoopWarningEvent nonVolatileLoop() {
        return new LoopWarningEvent(NON_VOLATILE_LOOP, 0);
    }

    public static LoopWarningEvent nonYetImplemented(int messageParam) {
        return new LoopWarningEvent(NON_YET_IMPLEMENTED, messageParam);
    }

    public static LoopWarningEvent maximumIterationsReached(int orderTreeLength) {
        return new LoopWarningEvent(MAXIMUM_ITERATIONS_REACHED, orderTreeLength);
    }

    public static LoopWarningEvent cyclesRemoved(int messageParam) {
        return new LoopWarningEvent(CYCLES_REMOVED, messageParam);
    }

    private LoopWarningEvent( int messageId, int messageParam) {
        this.messageId = messageId;
        this.messageParam = messageParam;
    }


    public int loopId() {
        return loopId;
    }


    public int runId() {
        return runId;
    }

    public void setLoopId(int loopId) {
        this.loopId = loopId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    @Override
    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(getStream(streamRepository).getStream());
    }
}
