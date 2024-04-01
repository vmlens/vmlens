package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.event.gen.ThreadJoinedEventGen;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderAndCalculatedRunElementContainer;

public class ThreadJoinedEvent extends ThreadJoinedEventGen implements RuntimeEvent {

    @Override
    public void send(SendEventContext context) {

    }

    // Fixme
    @Override
    public int threadIndex() {
        return 0;
    }

    @Override
    public void addToContainer(int positionInThread, BlockBuilderAndCalculatedRunElementContainer container) {

    }
}
