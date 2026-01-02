package com.vmlens.trace.agent.bootstrap.event.gen;

import java.io.DataOutputStream;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId;

public class FieldAccessEventGen  {

    protected int     threadIndex;
    protected int     fieldId;
    protected int     methodCounter;
    protected int     operation;
    protected int     methodId;
    protected long     objectHashCode;
    protected int     loopId;
    protected int     runId;
    protected int     runPosition;
    protected int     dominatorTreeCounter;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FieldAccessEventGen that = (FieldAccessEventGen) o;
    if ( threadIndex != that.threadIndex) return false;
    if ( fieldId != that.fieldId) return false;
    if ( methodCounter != that.methodCounter) return false;
    if ( operation != that.operation) return false;
    if ( methodId != that.methodId) return false;
    if ( objectHashCode != that.objectHashCode) return false;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    if ( runPosition != that.runPosition) return false;
    if ( dominatorTreeCounter != that.dominatorTreeCounter) return false;
    return true;
}

@Override
public String toString() {
    return "FieldAccessEventGen{" +
    "threadIndex=" + threadIndex +
    "fieldId=" + fieldId +
    "methodCounter=" + methodCounter +
    "operation=" + operation +
    "methodId=" + methodId +
    "objectHashCode=" + objectHashCode +
    "loopId=" + loopId +
    "runId=" + runId +
    "runPosition=" + runPosition +
    "dominatorTreeCounter=" + dominatorTreeCounter +
    '}';
}



 public StreamWrapperWithLoopIdAndRunId getStream(StreamRepository streamRepository)  {
     return streamRepository.nonVolatile;
 }

public void serialize(DataOutputStream buffer) throws Exception {
buffer.write( (byte)  1 );
     buffer.writeInt( threadIndex ); 
     buffer.writeInt( fieldId ); 
     buffer.writeInt( methodCounter ); 
     buffer.writeInt( operation ); 
     buffer.writeInt( methodId ); 
      buffer.writeLong( objectHashCode );  
     buffer.writeInt( loopId ); 
     buffer.writeInt( runId ); 
     buffer.writeInt( runPosition ); 
     buffer.writeInt( dominatorTreeCounter ); 
}


}