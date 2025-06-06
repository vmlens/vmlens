package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;


public class ThreadJoinedEventGen  {

    protected int     threadIndex;
    protected int     bytecodePosition;
    protected int     methodId;
    protected int     joinedThreadIndex;
    protected int     methodCounter;
    protected int     loopId;
    protected int     runId;
    protected int     runPosition;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ThreadJoinedEventGen that = (ThreadJoinedEventGen) o;
    if ( threadIndex != that.threadIndex) return false;
    if ( bytecodePosition != that.bytecodePosition) return false;
    if ( methodId != that.methodId) return false;
    if ( joinedThreadIndex != that.joinedThreadIndex) return false;
    if ( methodCounter != that.methodCounter) return false;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    if ( runPosition != that.runPosition) return false;
    return true;
}

@Override
public String toString() {
    return "ThreadJoinedEventGen{" +
    "threadIndex=" + threadIndex +
    "bytecodePosition=" + bytecodePosition +
    "methodId=" + methodId +
    "joinedThreadIndex=" + joinedThreadIndex +
    "methodCounter=" + methodCounter +
    "loopId=" + loopId +
    "runId=" + runId +
    "runPosition=" + runPosition +
    '}';
}



 public void serialize(StreamRepository streamRepository) throws Exception {
     serialize( streamRepository.interleave.
                     getByteBuffer(new LoopIdAndRunId(loopId,runId),  33, EventConstants.MAX_ARRAY_SIZE * 1000));

 }

public void serialize(ByteBuffer buffer) throws Exception {
buffer.put( (byte)  17 );
     buffer.putInt( threadIndex ); 
     buffer.putInt( bytecodePosition ); 
     buffer.putInt( methodId ); 
     buffer.putInt( joinedThreadIndex ); 
     buffer.putInt( methodCounter ); 
     buffer.putInt( loopId ); 
     buffer.putInt( runId ); 
     buffer.putInt( runPosition ); 
}


}