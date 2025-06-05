package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public class AlternativeNoOrder implements OrderAlternative {

    private final boolean processFlag;

    public AlternativeNoOrder(boolean processFlag) {
        this.processFlag = processFlag;
    }

    @Override
    public boolean process(CreateOrderContext context) {
        return processFlag;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        AlternativeNoOrder that = (AlternativeNoOrder) object;
        return processFlag == that.processFlag;
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(processFlag);
    }
}
