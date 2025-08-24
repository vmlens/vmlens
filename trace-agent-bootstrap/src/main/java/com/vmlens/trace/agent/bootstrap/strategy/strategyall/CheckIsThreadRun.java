package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

public class CheckIsThreadRun {

    private final AlreadyCheckedThreadIds alreadyCheckedThreadIds;

    public CheckIsThreadRun(AlreadyCheckedThreadIds alreadyCheckedThreadIds) {
        this.alreadyCheckedThreadIds = alreadyCheckedThreadIds;
    }

    public CheckIsThreadRun() {
        this(new AlreadyCheckedThreadIds());
    }

    public boolean isThreadRun() {
        return ! alreadyCheckedThreadIds.containsAndAddThreadId();
    }

}
