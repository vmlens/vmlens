package com.vmlens.report.runelementtype;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;

public interface RunElementType {

    String operation();

    String element(DescriptionContext context);

    void addToNeedsDescription(NeedsDescriptionCallback callback);
    
}
