package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;


public class LockExitEventGen  {

    protected int     threadIndex;
    protected int     methodCounter;
    protected long     objectHashCode;
    protected int     lockType;
    protected int     bytecodePosition;
    protected int     methodId;
    protected int     loopId;
    protected int     runId;
    protected int     runPosition;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    LockExitEventGen that = (LockExitEventGen) o;
    if ( threadIndex != that.threadIndex) return false;
    if ( methodCounter != that.methodCounter) return false;
    if ( objectHashCode != that.objectHashCode) return false;
    if ( lockType != that.lockType) return false;
    if ( bytecodePosition != that.bytecodePosition) return false;
    if ( methodId != that.methodId) return false;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    if ( runPosition != that.runPosition) return false;
    return true;
}

@Override
public String toString() {
    return "LockExitEventGen{" +
    "threadIndex=" + threadIndex +
    "methodCounter=" + methodCounter +
    "objectHashCode=" + objectHashCode +
    "lockType=" + lockType +
    "bytecodePosition=" + bytecodePosition +
    "methodId=" + methodId +
    "loopId=" + loopId +
    "runId=" + runId +
    "runPosition=" + runPosition +
    '}';
}



 public void serialize(StreamRepository streamRepository) throws Exception {
     serialize( streamRepository.interleave.
                     getByteBuffer(new LoopIdAndRunId(loopId,runId),  41, EventConstants.MAX_ARRAY_SIZE * 1000));

 }

public void serialize(ByteBuffer buffer) throws Exception {
buffer.put( (byte)  11 );
     buffer.putInt( threadIndex ); 
     buffer.putInt( methodCounter ); 
      buffer.putLong( objectHashCode );  
     buffer.putInt( lockType ); 
     buffer.putInt( bytecodePosition ); 
     buffer.putInt( methodId ); 
     buffer.putInt( loopId ); 
     buffer.putInt( runId ); 
     buffer.putInt( runPosition ); 
}


}