package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

public class MultipleVolatileSmallNoCycle  extends AbstractOrderArrayListTestBuilder {
    @Override
    protected void addOrder() {
        lbr(0,0,1,0);
        lbr(1,4,0,5);
        lbr(1,1,0,1);
        lbr(1,3,0,1);
        lbr(1,0,0,2);
        lbr(1,1,0,2);
        lbr(1,2,0,2);
        lbr(1,3,0,2);
        lbr(1,1,0,3);
        lbr(1,3,0,3);
        lbr(1,0,0,4);
        lbr(1,1,0,4);
        lbr(1,2,0,4);
        lbr(1,3,0,4);
        lbr(1,1,0,6);
        lbr(1,3,0,6);
    }
}
