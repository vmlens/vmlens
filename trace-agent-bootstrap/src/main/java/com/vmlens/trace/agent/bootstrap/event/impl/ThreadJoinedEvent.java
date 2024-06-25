package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.gen.ThreadJoinedEventGen;

public class ThreadJoinedEvent extends ThreadJoinedEventGen implements RuntimeEvent {

    @Override
    public void accept(RuntimeEventVisitor visitor) {
        visitor.visit(this);
    }
}
