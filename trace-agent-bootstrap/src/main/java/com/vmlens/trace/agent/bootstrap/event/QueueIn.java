package com.vmlens.trace.agent.bootstrap.event;

public interface QueueIn {
    void offer(SerializableEvent element);

}
