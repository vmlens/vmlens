package com.vmlens.trace.agent.bootstrap.event.gen;

import java.io.DataOutputStream;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId;

public class AtomicNonBlockingEventGen  {

    protected int     threadIndex;
    protected int     bytecodePosition;
    protected int     methodCounter;
    protected int     methodId;
    protected int     operation;
    protected long     objectHashCode;
    protected int     atomicMethodId;
    protected int     loopId;
    protected int     runId;
    protected int     runPosition;
    protected int     dominatorTreeCounter;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AtomicNonBlockingEventGen that = (AtomicNonBlockingEventGen) o;
    if ( threadIndex != that.threadIndex) return false;
    if ( bytecodePosition != that.bytecodePosition) return false;
    if ( methodCounter != that.methodCounter) return false;
    if ( methodId != that.methodId) return false;
    if ( operation != that.operation) return false;
    if ( objectHashCode != that.objectHashCode) return false;
    if ( atomicMethodId != that.atomicMethodId) return false;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    if ( runPosition != that.runPosition) return false;
    if ( dominatorTreeCounter != that.dominatorTreeCounter) return false;
    return true;
}

@Override
public String toString() {
    return "AtomicNonBlockingEventGen{" +
    "threadIndex=" + threadIndex +
    "bytecodePosition=" + bytecodePosition +
    "methodCounter=" + methodCounter +
    "methodId=" + methodId +
    "operation=" + operation +
    "objectHashCode=" + objectHashCode +
    "atomicMethodId=" + atomicMethodId +
    "loopId=" + loopId +
    "runId=" + runId +
    "runPosition=" + runPosition +
    "dominatorTreeCounter=" + dominatorTreeCounter +
    '}';
}



 public StreamWrapperWithLoopIdAndRunId getStream(StreamRepository streamRepository)  {
     return streamRepository.interleave;
 }

public void serialize(DataOutputStream buffer) throws Exception {
buffer.write( (byte)  6 );
     buffer.writeInt( threadIndex ); 
     buffer.writeInt( bytecodePosition ); 
     buffer.writeInt( methodCounter ); 
     buffer.writeInt( methodId ); 
     buffer.writeInt( operation ); 
      buffer.writeLong( objectHashCode );  
     buffer.writeInt( atomicMethodId ); 
     buffer.writeInt( loopId ); 
     buffer.writeInt( runId ); 
     buffer.writeInt( runPosition ); 
     buffer.writeInt( dominatorTreeCounter ); 
}


}