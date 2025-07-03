package com.vmlens.trace.agent.bootstrap.event.gen;

import java.io.DataOutputStream;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId;

public class ConditionWaitEnterEventGen  {

    protected int     threadIndex;
    protected int     methodCounter;
    protected long     objectHashCode;
    protected int     bytecodePosition;
    protected int     methodId;
    protected int     lockKeyCategory;
    protected int     loopId;
    protected int     runId;
    protected int     runPosition;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ConditionWaitEnterEventGen that = (ConditionWaitEnterEventGen) o;
    if ( threadIndex != that.threadIndex) return false;
    if ( methodCounter != that.methodCounter) return false;
    if ( objectHashCode != that.objectHashCode) return false;
    if ( bytecodePosition != that.bytecodePosition) return false;
    if ( methodId != that.methodId) return false;
    if ( lockKeyCategory != that.lockKeyCategory) return false;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    if ( runPosition != that.runPosition) return false;
    return true;
}

@Override
public String toString() {
    return "ConditionWaitEnterEventGen{" +
    "threadIndex=" + threadIndex +
    "methodCounter=" + methodCounter +
    "objectHashCode=" + objectHashCode +
    "bytecodePosition=" + bytecodePosition +
    "methodId=" + methodId +
    "lockKeyCategory=" + lockKeyCategory +
    "loopId=" + loopId +
    "runId=" + runId +
    "runPosition=" + runPosition +
    '}';
}



 public StreamWrapperWithLoopIdAndRunId getStream(StreamRepository streamRepository)  {
     return streamRepository.interleave;
 }

public void serialize(DataOutputStream buffer) throws Exception {
buffer.write( (byte)  16 );
     buffer.writeInt( threadIndex ); 
     buffer.writeInt( methodCounter ); 
      buffer.writeLong( objectHashCode );  
     buffer.writeInt( bytecodePosition ); 
     buffer.writeInt( methodId ); 
     buffer.writeInt( lockKeyCategory ); 
     buffer.writeInt( loopId ); 
     buffer.writeInt( runId ); 
     buffer.writeInt( runPosition ); 
}


}