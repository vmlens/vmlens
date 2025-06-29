package com.vmlens.trace.agent.bootstrap.event.gen;

import java.io.DataOutputStream;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId;

public class LoopWarningEventGen  {

    protected int     loopId;
    protected int     runId;
    protected int     messageId;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    LoopWarningEventGen that = (LoopWarningEventGen) o;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    if ( messageId != that.messageId) return false;
    return true;
}

@Override
public String toString() {
    return "LoopWarningEventGen{" +
    "loopId=" + loopId +
    "runId=" + runId +
    "messageId=" + messageId +
    '}';
}



 public StreamWrapperWithLoopIdAndRunId getStream(StreamRepository streamRepository)  {
     return streamRepository.control;
 }

public void serialize(DataOutputStream buffer) throws Exception {
buffer.write( (byte)  24 );
     buffer.writeInt( loopId ); 
     buffer.writeInt( runId ); 
     buffer.writeInt( messageId ); 
}


}