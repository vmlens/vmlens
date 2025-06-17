package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;


public class VolatileArrayAccessEventGen  {

    protected int     threadIndex;
    protected int     bytecodePosition;
    protected int     arrayIndex;
    protected int     methodCounter;
    protected int     methodId;
    protected int     operation;
    protected long     objectHashCode;
    protected int     loopId;
    protected int     runId;
    protected int     runPosition;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    VolatileArrayAccessEventGen that = (VolatileArrayAccessEventGen) o;
    if ( threadIndex != that.threadIndex) return false;
    if ( bytecodePosition != that.bytecodePosition) return false;
    if ( arrayIndex != that.arrayIndex) return false;
    if ( methodCounter != that.methodCounter) return false;
    if ( methodId != that.methodId) return false;
    if ( operation != that.operation) return false;
    if ( objectHashCode != that.objectHashCode) return false;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    if ( runPosition != that.runPosition) return false;
    return true;
}

@Override
public String toString() {
    return "VolatileArrayAccessEventGen{" +
    "threadIndex=" + threadIndex +
    "bytecodePosition=" + bytecodePosition +
    "arrayIndex=" + arrayIndex +
    "methodCounter=" + methodCounter +
    "methodId=" + methodId +
    "operation=" + operation +
    "objectHashCode=" + objectHashCode +
    "loopId=" + loopId +
    "runId=" + runId +
    "runPosition=" + runPosition +
    '}';
}



 public void serialize(StreamRepository streamRepository) throws Exception {
     serialize( streamRepository.interleave.
                     getByteBuffer(new LoopIdAndRunId(loopId,runId),  45, EventConstants.MAX_ARRAY_SIZE * 1000));

 }

public void serialize(ByteBuffer buffer) throws Exception {
buffer.put( (byte)  7 );
     buffer.putInt( threadIndex ); 
     buffer.putInt( bytecodePosition ); 
     buffer.putInt( arrayIndex ); 
     buffer.putInt( methodCounter ); 
     buffer.putInt( methodId ); 
     buffer.putInt( operation ); 
      buffer.putLong( objectHashCode );  
     buffer.putInt( loopId ); 
     buffer.putInt( runId ); 
     buffer.putInt( runPosition ); 
}


}