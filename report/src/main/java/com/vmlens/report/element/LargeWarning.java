package com.vmlens.report.element;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.uielement.UIWarning;

public class LargeWarning {
    
    private final int methodId;

    public LargeWarning(int methodId) {
        this.methodId = methodId;
    }

    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
        callback.needsMethod(methodId);
    }

    public UIWarning toUIWarning(DescriptionContext descriptionContext) {
        return new UIWarning(descriptionContext.methodName(methodId ) + " is not yet testable ");
    }
}
