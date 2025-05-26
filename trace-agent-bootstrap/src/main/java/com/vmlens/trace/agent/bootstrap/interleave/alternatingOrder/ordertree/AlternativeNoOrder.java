package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public class AlternativeNoOrder implements ListNodeAlternative {

    private final boolean processFlag;

    public AlternativeNoOrder(boolean processFlag) {
        this.processFlag = processFlag;
    }

    @Override
    public boolean process(CreateOrderContext context) {
        return processFlag;
    }
}
