package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.event.gen.ThreadStartEventGen;

public class ThreadStartEvent extends ThreadStartEventGen implements RuntimeEvent {

    @Override
    public void send(SendEventContext context) {

    }
}
