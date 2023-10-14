package com.vmlens.trace.agent.bootstrap.interleave.run;

import java.util.Objects;

public class ContainerForInterleaveActionWithPositionFactory implements InterleaveActionWithPositionFactoryAndOrRuntimeEvent {
    private final InterleaveActionWithPositionFactory interleaveActionWithPositionFactory;

    public ContainerForInterleaveActionWithPositionFactory(InterleaveActionWithPositionFactory interleaveActionWithPositionFactory) {
        this.interleaveActionWithPositionFactory = interleaveActionWithPositionFactory;
    }

    @Override
    public void apply(ActualRun actualRun, ActualRunContext actualRunContext) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerForInterleaveActionWithPositionFactory that = (ContainerForInterleaveActionWithPositionFactory) o;

        return Objects.equals(interleaveActionWithPositionFactory, that.interleaveActionWithPositionFactory);
    }

    @Override
    public int hashCode() {
        return interleaveActionWithPositionFactory != null ? interleaveActionWithPositionFactory.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ContainerForInterleaveActionWithPositionFactory{" +
                "interleaveActionWithPositionFactory=" + interleaveActionWithPositionFactory +
                '}';
    }
}
