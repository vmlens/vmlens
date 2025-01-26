package com.anarsoft.trace.agent.runtime.classtransformer.plan;

import com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory.MethodCallbackFactory;

public class ApplyAfterOperationMethodCallTarget implements ApplyAfterOperation {

    private final int forMethodId;

    public ApplyAfterOperationMethodCallTarget(int forMethodId) {
        this.forMethodId = forMethodId;
    }

    @Override
    public void afterOperation(MethodCallbackFactory callbackCallFactory) {
        callbackCallFactory.afterMethodCallTarget(forMethodId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplyAfterOperationMethodCallTarget that = (ApplyAfterOperationMethodCallTarget) o;
        return forMethodId == that.forMethodId;
    }

    @Override
    public int hashCode() {
        return forMethodId;
    }

    @Override
    public String toString() {
        return "ApplyAfterOperationMethodCallTarget{" +
                "forMethodId=" + forMethodId +
                '}';
    }
}
