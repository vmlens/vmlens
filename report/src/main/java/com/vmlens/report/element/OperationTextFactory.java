package com.vmlens.report.element;

public interface OperationTextFactory {

    String create();

    void addToNeedsDescription(NeedsDescriptionCallback callback);
    
}
