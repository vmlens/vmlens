package com.vmlens.report.assertion;

public interface OnDescriptionAndLeftBeforeRight extends OnDescription {
    
    void startRun(int loopId, int runId);
    void onLeftBeforeRight(LeftBeforeRight leftBeforeRight);


}
