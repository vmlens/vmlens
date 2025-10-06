package com.vmlens.trace.agent.bootstrap.event.gen;

import java.io.DataOutputStream;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId;

public class MethodExitEventGen  {

    protected int     threadIndex;
    protected int     methodCounter;
    protected int     loopId;
    protected int     runId;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MethodExitEventGen that = (MethodExitEventGen) o;
    if ( threadIndex != that.threadIndex) return false;
    if ( methodCounter != that.methodCounter) return false;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    return true;
}

@Override
public String toString() {
    return "MethodExitEventGen{" +
    "threadIndex=" + threadIndex +
    "methodCounter=" + methodCounter +
    "loopId=" + loopId +
    "runId=" + runId +
    '}';
}



 public StreamWrapperWithLoopIdAndRunId getStream(StreamRepository streamRepository)  {
     return streamRepository.method;
 }

public void serialize(DataOutputStream buffer) throws Exception {
buffer.write( (byte)  20 );
     buffer.writeInt( threadIndex ); 
     buffer.writeInt( methodCounter ); 
     buffer.writeInt( loopId ); 
     buffer.writeInt( runId ); 
}


}