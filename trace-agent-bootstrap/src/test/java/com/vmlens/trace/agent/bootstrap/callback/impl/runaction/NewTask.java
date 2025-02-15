package com.vmlens.trace.agent.bootstrap.callback.impl.runaction;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

import java.util.Objects;

public class NewTask implements RunAction {

    private final RunnableOrThreadWrapper newWrapper;

    public NewTask(RunnableOrThreadWrapper newWrapper) {
        this.newWrapper = newWrapper;
    }

    public static NewTask newTask(RunnableOrThreadWrapper newWrapper) {
        return new NewTask(newWrapper);
    }

    @Override
    public String toString() {
        return "NewTask{" +
                "newWrapper=" + newWrapper +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewTask newTask = (NewTask) o;

        return Objects.equals(newWrapper, newTask.newWrapper);
    }

    @Override
    public int hashCode() {
        return newWrapper != null ? newWrapper.hashCode() : 0;
    }
}
