package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.gen.ThreadStartEventGen;

public class ThreadStartEvent extends ThreadStartEventGen implements RuntimeEvent {

    @Override
    public void accept(RuntimeEventVisitor visitor) {
        visitor.visit(this);
    }
}
