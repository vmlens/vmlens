package com.vmlens.trace.agent.bootstrap.event.impl;


public interface RuntimeEvent {

    void accept(RuntimeEventVisitor visitor);

}
