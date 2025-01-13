package com.vmlens.report.element.operationtextfactoryimpl;

import com.vmlens.report.element.NeedsDescriptionCallback;
import com.vmlens.report.element.OperationTextFactory;

public class ThreadOperationTextFactory implements OperationTextFactory {

    private final String text;
    private final int onThreadIndex;

    public ThreadOperationTextFactory(String text, int onThreadIndex) {
        this.text = text;
        this.onThreadIndex = onThreadIndex;
    }

    @Override
    public String create() {
        return text;
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {

    }
}
