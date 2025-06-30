package com.vmlens.trace.agent.bootstrap.event.gen;

import java.io.DataOutputStream;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId;

public class ThreadStartEventGen  {

    protected int     threadIndex;
    protected int     bytecodePosition;
    protected int     methodId;
    protected int     startedThreadIndex;
    protected int     methodCounter;
    protected int     eventType;
    protected int     loopId;
    protected int     runId;
    protected int     runPosition;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ThreadStartEventGen that = (ThreadStartEventGen) o;
    if ( threadIndex != that.threadIndex) return false;
    if ( bytecodePosition != that.bytecodePosition) return false;
    if ( methodId != that.methodId) return false;
    if ( startedThreadIndex != that.startedThreadIndex) return false;
    if ( methodCounter != that.methodCounter) return false;
    if ( eventType != that.eventType) return false;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    if ( runPosition != that.runPosition) return false;
    return true;
}

@Override
public String toString() {
    return "ThreadStartEventGen{" +
    "threadIndex=" + threadIndex +
    "bytecodePosition=" + bytecodePosition +
    "methodId=" + methodId +
    "startedThreadIndex=" + startedThreadIndex +
    "methodCounter=" + methodCounter +
    "eventType=" + eventType +
    "loopId=" + loopId +
    "runId=" + runId +
    "runPosition=" + runPosition +
    '}';
}



 public StreamWrapperWithLoopIdAndRunId getStream(StreamRepository streamRepository)  {
     return streamRepository.interleave;
 }

public void serialize(DataOutputStream buffer) throws Exception {
buffer.write( (byte)  19 );
     buffer.writeInt( threadIndex ); 
     buffer.writeInt( bytecodePosition ); 
     buffer.writeInt( methodId ); 
     buffer.writeInt( startedThreadIndex ); 
     buffer.writeInt( methodCounter ); 
     buffer.writeInt( eventType ); 
     buffer.writeInt( loopId ); 
     buffer.writeInt( runId ); 
     buffer.writeInt( runPosition ); 
}


}