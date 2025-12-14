package com.vmlens.report.input.run;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.input.run.objecthashcodemap.ObjectHashCodeMap;

public interface RunElementType {

    String operation();

    String element(DescriptionContext context);

    void addToNeedsDescription(NeedsDescriptionCallback callback);
    void setObjectHashCodeMap(ObjectHashCodeMap objectHashCodeMap, int threadIndex);

    String object(DescriptionContext context);
}
