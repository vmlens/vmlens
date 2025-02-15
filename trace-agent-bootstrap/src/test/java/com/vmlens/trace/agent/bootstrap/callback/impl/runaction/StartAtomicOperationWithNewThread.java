package com.vmlens.trace.agent.bootstrap.callback.impl.runaction;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

import java.util.Objects;

public class StartAtomicOperationWithNewThread implements RunAction {

    private final RunnableOrThreadWrapper newWrapper;

    public StartAtomicOperationWithNewThread(RunnableOrThreadWrapper newWrapper) {
        this.newWrapper = newWrapper;
    }

    public static StartAtomicOperationWithNewThread startAtomicOperationWithNewThread(RunnableOrThreadWrapper newWrapper) {
        return new StartAtomicOperationWithNewThread(newWrapper);
    }

    @Override
    public String toString() {
        return "StartAtomicOperationWithNewThread{" +
                "newWrapper=" + newWrapper +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StartAtomicOperationWithNewThread that = (StartAtomicOperationWithNewThread) o;

        return Objects.equals(newWrapper, that.newWrapper);
    }

    @Override
    public int hashCode() {
        return newWrapper != null ? newWrapper.hashCode() : 0;
    }
}
