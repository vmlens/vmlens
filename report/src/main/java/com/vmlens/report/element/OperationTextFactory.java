package com.vmlens.report.element;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;

public interface OperationTextFactory {

    String create(DescriptionContext context);

    void addToNeedsDescription(NeedsDescriptionCallback callback);
    
}
