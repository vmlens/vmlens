package com.vmlens.trace.agent.bootstrap.event.gen;

import java.io.DataOutputStream;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId;

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
    "operation=" + operation +
    "methodId=" + methodId +
    "loopId=" + loopId +
    "runId=" + runId +
    "runPosition=" + runPosition +
    '}';
}



 public StreamWrapperWithLoopIdAndRunId getStream(StreamRepository streamRepository)  {
     return streamRepository.nonVolatile;
 }

public void serialize(DataOutputStream buffer) throws Exception {
buffer.write( (byte)  2 );
     buffer.writeInt( threadIndex ); 
     buffer.writeInt( fieldId ); 
     buffer.writeInt( methodCounter ); 
     buffer.writeInt( operation ); 
     buffer.writeInt( methodId ); 
     buffer.writeInt( loopId ); 
     buffer.writeInt( runId ); 
     buffer.writeInt( runPosition ); 
}


}