package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.AlternatingOrderContainerIterator;

public interface IteratorQueue {
    AlternatingOrderContainerIterator poll();
}
