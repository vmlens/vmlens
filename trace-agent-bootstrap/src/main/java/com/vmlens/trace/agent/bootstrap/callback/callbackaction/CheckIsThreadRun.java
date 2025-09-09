package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

public class CheckIsThreadRun {

    public final static CheckIsThreadRun SINGLETON = new CheckIsThreadRun();

    private final AlreadyCheckedThreadIds alreadyCheckedThreadIds;

    private CheckIsThreadRun(AlreadyCheckedThreadIds alreadyCheckedThreadIds) {
        this.alreadyCheckedThreadIds = alreadyCheckedThreadIds;
    }

    public CheckIsThreadRun() {
        this(new AlreadyCheckedThreadIds());
    }

    public boolean isThreadRun() {
        return ! alreadyCheckedThreadIds.containsAndAddThreadId();
    }

}
