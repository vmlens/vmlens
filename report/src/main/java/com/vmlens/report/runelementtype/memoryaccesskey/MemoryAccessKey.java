package com.vmlens.report.runelementtype.memoryaccesskey;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;

public interface MemoryAccessKey {

    String asString(DescriptionContext context);
    void addToNeedsDescription(NeedsDescriptionCallback callback);

}
