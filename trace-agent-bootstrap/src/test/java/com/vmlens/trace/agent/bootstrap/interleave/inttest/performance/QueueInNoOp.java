package com.vmlens.trace.agent.bootstrap.interleave.inttest.performance;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;

public class QueueInNoOp extends QueueIn {
    @Override
    public void offer(SerializableEvent element) {

    }
}
