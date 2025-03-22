package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;


public class LoopEndEventGen  {

    protected int     loopId;
    protected int     runId;
    protected int     status;

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    LoopEndEventGen that = (LoopEndEventGen) o;
    if ( loopId != that.loopId) return false;
    if ( runId != that.runId) return false;
    if ( status != that.status) return false;
    return true;
}

@Override
public String toString() {
    return "LoopEndEventGen{" +
    "loopId=" + loopId +
    "runId=" + runId +
    "status=" + status +
    '}';
}



 public void serialize(StreamRepository streamRepository) throws Exception {
     serialize( streamRepository.control.
                     getByteBuffer(new LoopIdAndRunId(loopId,runId),  13, EventConstants.MAX_ARRAY_SIZE * 1000));

 }

public void serialize(ByteBuffer buffer) throws Exception {
buffer.put( (byte)  23 );
     buffer.putInt( loopId ); 
     buffer.putInt( runId ); 
     buffer.putInt( status ); 
}


}