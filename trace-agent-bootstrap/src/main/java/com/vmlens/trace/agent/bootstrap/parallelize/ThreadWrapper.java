package com.vmlens.trace.agent.bootstrap.parallelize;

import java.util.Objects;

public class ThreadWrapper {
    public final Object runnable;

    public ThreadWrapper(Object runnable) {
        super();
        this.runnable = runnable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreadWrapper that = (ThreadWrapper) o;

        return Objects.equals(runnable, that.runnable);
    }

    @Override
    public int hashCode() {
        return runnable != null ? runnable.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RunnableWrapper [runnable=" + runnable + "]";
    }



}
