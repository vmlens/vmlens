package com.vmlens.report.runelementtype;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.element.LoopRunAndThreadIndex;

public class ThreadRunElementType implements RunElementType {

    private final String text;
    private final LoopRunAndThreadIndex onThreadIndex;

    public ThreadRunElementType(String text, LoopRunAndThreadIndex onThreadIndex) {
        this.text = text;
        this.onThreadIndex = onThreadIndex;
    }

    @Override
    public String asString(DescriptionContext context) {
        return String.format(text, context.threadName(onThreadIndex));
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {

    }
}
