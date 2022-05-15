package com.vmlens.trace.agent.bootstrap.parallelize.functionalTest;

import com.vmlens.trace.agent.bootstrap.parallelize.loop.AllInterleavingsRun;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.AllInterleavingsRunFactory;

public class AllInterleavingsRunFactoryMock implements AllInterleavingsRunFactory {

    private AllInterleavingsRunMock allInterleavingsRunMock;

    @Override
    public AllInterleavingsRun create() {
        allInterleavingsRunMock = new AllInterleavingsRunMock();
        return allInterleavingsRunMock;
    }

    public AllInterleavingsRunMock getAllInterleavingsRunMock() {
        return allInterleavingsRunMock;
    }
}
