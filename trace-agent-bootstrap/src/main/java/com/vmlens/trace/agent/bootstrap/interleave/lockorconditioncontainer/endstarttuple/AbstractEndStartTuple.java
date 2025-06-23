package com.vmlens.trace.agent.bootstrap.interleave.lockorconditioncontainer.endstarttuple;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.AddToAlternatingOrder;

public abstract class AbstractEndStartTuple implements AddToAlternatingOrder  {

    protected Position startPosition;
    protected Position endPosition;

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public void setEndPosition(Position endPosition) {
        this.endPosition = endPosition;
    }
}
