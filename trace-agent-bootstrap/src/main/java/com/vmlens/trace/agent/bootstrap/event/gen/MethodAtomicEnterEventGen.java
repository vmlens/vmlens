package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;


public class MethodAtomicEnterEventGen  {

    protected int     threadIndex;
    protected int     methodId;
    protected int     methodCounter;
    protected byte     hasCallback;
    protected int     loopId;
    protected int     runId;
    protected int     runPosition;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MethodAtomicEnterEventGen that = (MethodAtomicEnterEventGen) o;
    if ( threadIndex != that.threadIndex) return false;
    if ( methodId != that.methodId) return false;
    if ( methodCounter != that.methodCounter) return false;
    if ( hasCallback != that.hasCallback) return false;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    if ( runPosition != that.runPosition) return false;
    return true;
}

@Override
public String toString() {
    return "MethodAtomicEnterEventGen{" +
    "threadIndex=" + threadIndex +
    "methodId=" + methodId +
    "methodCounter=" + methodCounter +
    "hasCallback=" + hasCallback +
    "loopId=" + loopId +
    "runId=" + runId +
    "runPosition=" + runPosition +
    '}';
}



 public void serialize(StreamRepository streamRepository) throws Exception {
     serialize( streamRepository.interleave.
                     getByteBuffer(new LoopIdAndRunId(loopId,runId),  26, EventConstants.MAX_ARRAY_SIZE * 1000));

 }

public void serialize(ByteBuffer buffer) throws Exception {
buffer.put( (byte)  18 );
     buffer.putInt( threadIndex ); 
     buffer.putInt( methodId ); 
     buffer.putInt( methodCounter ); 
     buffer.put( hasCallback ); 
     buffer.putInt( loopId ); 
     buffer.putInt( runId ); 
     buffer.putInt( runPosition ); 
}


}