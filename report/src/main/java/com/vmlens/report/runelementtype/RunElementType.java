package com.vmlens.report.runelementtype;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.runelementtype.objecthashcodemap.ObjectHashCodeMap;

public interface RunElementType {

    String operation();

    String element(DescriptionContext context);

    void addToNeedsDescription(NeedsDescriptionCallback callback);
    void setObjectHashCodeMap(ObjectHashCodeMap objectHashCodeMap, int threadIndex);

    String object(DescriptionContext context);
}
