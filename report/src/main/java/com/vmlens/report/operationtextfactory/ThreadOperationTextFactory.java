package com.vmlens.report.operationtextfactory;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.element.LoopRunAndThreadIndex;

public class ThreadOperationTextFactory implements OperationTextFactory {

    private final String text;
    private final LoopRunAndThreadIndex onThreadIndex;

    public ThreadOperationTextFactory(String text, LoopRunAndThreadIndex onThreadIndex) {
        this.text = text;
        this.onThreadIndex = onThreadIndex;
    }

    @Override
    public String create(DescriptionContext context) {
        return String.format(text, context.threadName(onThreadIndex));
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {

    }
}
