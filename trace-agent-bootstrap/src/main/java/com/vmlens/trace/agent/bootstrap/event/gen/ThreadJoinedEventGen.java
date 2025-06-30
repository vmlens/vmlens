package com.vmlens.trace.agent.bootstrap.event.gen;

import java.io.DataOutputStream;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId;

public class ThreadJoinedEventGen  {

    protected int     threadIndex;
    protected int     bytecodePosition;
    protected int     methodId;
    protected int     joinedThreadIndex;
    protected int     methodCounter;
    protected int     eventType;
    protected int     loopId;
    protected int     runId;
    protected int     runPosition;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ThreadJoinedEventGen that = (ThreadJoinedEventGen) o;
    if ( threadIndex != that.threadIndex) return false;
    if ( bytecodePosition != that.bytecodePosition) return false;
    if ( methodId != that.methodId) return false;
    if ( joinedThreadIndex != that.joinedThreadIndex) return false;
    if ( methodCounter != that.methodCounter) return false;
    if ( eventType != that.eventType) return false;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    if ( runPosition != that.runPosition) return false;
    return true;
}

@Override
public String toString() {
    return "ThreadJoinedEventGen{" +
    "threadIndex=" + threadIndex +
    "bytecodePosition=" + bytecodePosition +
    "methodId=" + methodId +
    "joinedThreadIndex=" + joinedThreadIndex +
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
buffer.write( (byte)  20 );
     buffer.writeInt( threadIndex ); 
     buffer.writeInt( bytecodePosition ); 
     buffer.writeInt( methodId ); 
     buffer.writeInt( joinedThreadIndex ); 
     buffer.writeInt( methodCounter ); 
     buffer.writeInt( eventType ); 
     buffer.writeInt( loopId ); 
     buffer.writeInt( runId ); 
     buffer.writeInt( runPosition ); 
}


}