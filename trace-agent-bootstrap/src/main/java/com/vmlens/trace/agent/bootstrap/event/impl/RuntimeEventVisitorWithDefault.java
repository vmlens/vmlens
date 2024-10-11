package com.vmlens.trace.agent.bootstrap.event.impl;

public abstract class RuntimeEventVisitorWithDefault implements RuntimeEventVisitor {

    @Override
    public void visit(VolatileAccessEvent volatileAccessEvent) {
        defaultMethod(volatileAccessEvent);
    }

    @Override
    public void visit(ThreadStartEvent threadStartEvent) {
        defaultMethod(threadStartEvent);
    }

    @Override
    public void visit(ThreadJoinedEvent threadJoinedEvent) {
        defaultMethod(threadJoinedEvent);
    }

    @Override
    public void visit(MethodEnterEvent methodEnterEvent) {
        defaultMethod(methodEnterEvent);
    }

    @Override
    public void visit(MethodExitEvent methodExitEvent) {
        defaultMethod(methodExitEvent);
    }

    protected abstract void defaultMethod(RuntimeEvent runtimeEvent);

}
