package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;


public class FieldAccessEventStaticGen  {

    protected int     threadIndex;
    protected int     fieldId;
    protected int     methodCounter;
    protected int     operation;
    protected int     methodId;
    protected int     loopId;
    protected int     runId;
    protected int     runPosition;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FieldAccessEventStaticGen that = (FieldAccessEventStaticGen) o;
    if ( threadIndex != that.threadIndex) return false;
    if ( fieldId != that.fieldId) return false;
    if ( methodCounter != that.methodCounter) return false;
    if ( operation != that.operation) return false;
    if ( methodId != that.methodId) return false;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    if ( runPosition != that.runPosition) return false;
    return true;
}

@Override
public String toString() {
    return "FieldAccessEventStaticGen{" +
    "threadIndex=" + threadIndex +
    "fieldId=" + fieldId +
    "methodCounter=" + methodCounter +
    "interleaveoperation=" + operation +
    "methodId=" + methodId +
    "loopId=" + loopId +
    "runId=" + runId +
    "runPosition=" + runPosition +
    '}';
}



 public void serialize(StreamRepository streamRepository) throws Exception {
     serialize( streamRepository.nonVolatile.
                     getByteBuffer(new LoopIdAndRunId(loopId,runId),  33, EventConstants.MAX_ARRAY_SIZE * 1000));

 }

public void serialize(ByteBuffer buffer) throws Exception {
buffer.put( (byte)  2 );
     buffer.putInt( threadIndex ); 
     buffer.putInt( fieldId ); 
     buffer.putInt( methodCounter ); 
     buffer.putInt( operation ); 
     buffer.putInt( methodId ); 
     buffer.putInt( loopId ); 
     buffer.putInt( runId ); 
     buffer.putInt( runPosition ); 
}


}