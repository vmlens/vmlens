package com.vmlens.trace.agent.bootstrap.event.gen;

import java.io.DataOutputStream;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId;

public class AutomaticTestMethodEventGen  {

    protected int     threadIndex;
    protected int     automaticTestType;
    protected int     automaticTestId;
    protected int     automaticTestMethodId;
    protected int     loopId;
    protected int     runId;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AutomaticTestMethodEventGen that = (AutomaticTestMethodEventGen) o;
    if ( threadIndex != that.threadIndex) return false;
    if ( automaticTestType != that.automaticTestType) return false;
    if ( automaticTestId != that.automaticTestId) return false;
    if ( automaticTestMethodId != that.automaticTestMethodId) return false;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    return true;
}

@Override
public String toString() {
    return "AutomaticTestMethodEventGen{" +
    "threadIndex=" + threadIndex +
    "automaticTestType=" + automaticTestType +
    "automaticTestId=" + automaticTestId +
    "automaticTestMethodId=" + automaticTestMethodId +
    "loopId=" + loopId +
    "runId=" + runId +
    '}';
}



 public StreamWrapperWithLoopIdAndRunId getStream(StreamRepository streamRepository)  {
     return streamRepository.automaticTest;
 }

public void serialize(DataOutputStream buffer) throws Exception {
buffer.write( (byte)  28 );
     buffer.writeInt( threadIndex ); 
     buffer.writeInt( automaticTestType ); 
     buffer.writeInt( automaticTestId ); 
     buffer.writeInt( automaticTestMethodId ); 
     buffer.writeInt( loopId ); 
     buffer.writeInt( runId ); 
}


}