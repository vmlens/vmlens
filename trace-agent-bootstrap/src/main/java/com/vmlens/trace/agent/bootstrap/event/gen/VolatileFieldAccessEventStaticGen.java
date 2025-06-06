package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;


public class VolatileFieldAccessEventStaticGen  {

    protected int     threadIndex;
    protected int     bytecodePosition;
    protected int     fieldId;
    protected int     methodCounter;
    protected int     methodId;
    protected int     operation;
    protected int     loopId;
    protected int     runId;
    protected int     runPosition;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    VolatileFieldAccessEventStaticGen that = (VolatileFieldAccessEventStaticGen) o;
    if ( threadIndex != that.threadIndex) return false;
    if ( bytecodePosition != that.bytecodePosition) return false;
    if ( fieldId != that.fieldId) return false;
    if ( methodCounter != that.methodCounter) return false;
    if ( methodId != that.methodId) return false;
    if ( operation != that.operation) return false;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    if ( runPosition != that.runPosition) return false;
    return true;
}

@Override
public String toString() {
    return "VolatileFieldAccessEventStaticGen{" +
    "threadIndex=" + threadIndex +
    "bytecodePosition=" + bytecodePosition +
    "fieldId=" + fieldId +
    "methodCounter=" + methodCounter +
    "methodId=" + methodId +
    "interleaveoperation=" + operation +
    "loopId=" + loopId +
    "runId=" + runId +
    "runPosition=" + runPosition +
    '}';
}



 public void serialize(StreamRepository streamRepository) throws Exception {
     serialize( streamRepository.interleave.
                     getByteBuffer(new LoopIdAndRunId(loopId,runId),  37, EventConstants.MAX_ARRAY_SIZE * 1000));

 }

public void serialize(ByteBuffer buffer) throws Exception {
buffer.put( (byte)  4 );
     buffer.putInt( threadIndex ); 
     buffer.putInt( bytecodePosition ); 
     buffer.putInt( fieldId ); 
     buffer.putInt( methodCounter ); 
     buffer.putInt( methodId ); 
     buffer.putInt( operation ); 
     buffer.putInt( loopId ); 
     buffer.putInt( runId ); 
     buffer.putInt( runPosition ); 
}


}