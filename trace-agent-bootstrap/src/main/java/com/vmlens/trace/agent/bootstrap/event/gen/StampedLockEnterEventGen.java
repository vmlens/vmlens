package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class StampedLockEnterEventGen {
    protected int slidingWindowId;
    protected long threadId;
    protected int programCounter;
    protected int order;
    protected int monitorId;
    protected int methodCounter;
    protected boolean isShared;
    protected int lockTyp;
    protected int stampedLockMethodId;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    public void serialize(StreamRepository streamRepository) throws Exception {
        ByteBuffer buffer = streamRepository.syncActions.getByteBuffer(slidingWindowId, 46, EventConstants.MAX_ARRAY_SIZE * 1000);
        buffer.put((byte) 12);
        buffer.putLong(threadId);
        ;
        buffer.putInt(programCounter);
        ;
        buffer.putInt(order);
        ;
        buffer.putInt(monitorId);
        ;
        buffer.putInt(methodCounter);
        ;
        buffer.put((byte) (isShared ? 1 : 0));
        ;
        buffer.putInt(lockTyp);
        ;
        buffer.putInt(stampedLockMethodId);
        ;
        buffer.putInt(loopId);
        ;
        buffer.putInt(runId);
        ;
        buffer.putInt(runPosition);
        ;
    }
}