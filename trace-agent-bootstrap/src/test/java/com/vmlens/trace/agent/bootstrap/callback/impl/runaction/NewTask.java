package com.vmlens.trace.agent.bootstrap.callback.impl.runaction;

public class NewTask implements RunAction {

    public NewTask() {

    }

    public static NewTask newTask() {
        return new NewTask();
    }

    @Override
    public String toString() {
        return "NewTask{" +

                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;


        return true;
    }

    @Override
    public int hashCode() {
        return 8;
    }
}
