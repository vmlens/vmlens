package com.vmlens.report.container;

import com.vmlens.trace.agent.bootstrap.description.ThreadDescription;

public class ContainerForThread implements Container {

    private final ThreadDescription threadDescription;

    public ContainerForThread(ThreadDescription threadDescription) {
        this.threadDescription = threadDescription;
    }

    @Override
    public String getName() {
        return threadDescription.threadName();
    }
}
