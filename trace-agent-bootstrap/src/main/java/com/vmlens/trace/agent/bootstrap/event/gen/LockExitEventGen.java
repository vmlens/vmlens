package com.vmlens.trace.agent.bootstrap.event.gen;

import java.io.DataOutputStream;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId;

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
    protected int     dominatorTreeCounter;

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
    if ( dominatorTreeCounter != that.dominatorTreeCounter) return false;
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
    "dominatorTreeCounter=" + dominatorTreeCounter +
    '}';
}



 public StreamWrapperWithLoopIdAndRunId getStream(StreamRepository streamRepository)  {
     return streamRepository.interleave;
 }

public void serialize(DataOutputStream buffer) throws Exception {
buffer.write( (byte)  8 );
     buffer.writeInt( threadIndex ); 
     buffer.writeInt( methodCounter ); 
      buffer.writeLong( objectHashCode );  
     buffer.writeInt( lockType ); 
     buffer.writeInt( bytecodePosition ); 
     buffer.writeInt( methodId ); 
     buffer.writeInt( loopId ); 
     buffer.writeInt( runId ); 
     buffer.writeInt( runPosition ); 
     buffer.writeInt( dominatorTreeCounter ); 
}


}