package com.vmlens.trace.agent.bootstrap.event.gen;

import java.io.DataOutputStream;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId;

public class RunEndEventGen  {

    protected int     loopId;
    protected int     runId;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RunEndEventGen that = (RunEndEventGen) o;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    return true;
}

@Override
public String toString() {
    return "RunEndEventGen{" +
    "loopId=" + loopId +
    "runId=" + runId +
    '}';
}



 public StreamWrapperWithLoopIdAndRunId getStream(StreamRepository streamRepository)  {
     return streamRepository.control;
 }

public void serialize(DataOutputStream buffer) throws Exception {
buffer.write( (byte)  22 );
     buffer.writeInt( loopId ); 
     buffer.writeInt( runId ); 
}


}