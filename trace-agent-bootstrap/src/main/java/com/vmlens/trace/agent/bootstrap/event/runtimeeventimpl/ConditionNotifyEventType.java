package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

public enum ConditionNotifyEventType {

    NOTIFY(0) , NOTIFY_ALL(1);

    private final int id;

    ConditionNotifyEventType(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }
}
