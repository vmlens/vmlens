package com.vmlens.report.element;

import com.anarsoft.trace.agent.description.ThreadDescription;

public class ContainerForThread implements Container {

    private final ThreadDescription threadDescription;

    public ContainerForThread(ThreadDescription threadDescription) {
        this.threadDescription = threadDescription;
    }

    @Override
    public String getName() {
        return Long.toString(threadDescription.threadId());
    }
}
