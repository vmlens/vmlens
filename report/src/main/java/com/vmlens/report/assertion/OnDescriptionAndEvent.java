package com.vmlens.report.assertion;

public interface OnDescriptionAndEvent extends OnDescription{
    
    void onFieldDescription(int loopId, int fieldId, String name);
    void onVolatileAccessEvent(int loopId, int fieldId, int threadIndex, int operation);
    
}
