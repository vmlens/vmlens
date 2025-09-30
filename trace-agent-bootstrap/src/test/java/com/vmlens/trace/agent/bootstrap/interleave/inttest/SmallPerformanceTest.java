package com.vmlens.trace.agent.bootstrap.interleave.inttest;

import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestRunner;
import org.junit.Test;

public class SmallPerformanceTest  extends AbstractInterleaveActionBuilder {

    @Test
    public void performance() {
        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();

        // Test
        new IntTestRunner().runTest(build(),expectedBuilder.buildExpected());
    }

    @Override
    protected void addActions() {
        threadStart(0,1);
        volatileAccess(0,atomic(1773175200),1);
        lockEnter(0,lock(1194128385L));
        volatileAccess(0,atomic(1773175200),1);
        volatileAccess(0,atomic(1773175200),3);
        lockExit(0,lock(1194128385L));
        volatileAccess(1,atomic(1773175200),1);
        lockEnter(0,lock(256050525L));
        lockEnter(1,lock(1194128385L));
        lockExit(0,lock(256050525L));
        volatileAccess(1,atomic(1773175200),1);
        volatileAccess(1,atomic(1773175200),3);
        lockExit(1,lock(1194128385L));
        threadJoin(0,1);
        volatileAccess(0,atomic(1773175200),1);
        lockEnter(0,lock(256050525L));
        volatileAccess(0,atomic(1773175200),1);
        volatileAccess(0,atomic(1773175200),3);
        lockExit(0,lock(256050525L));
        volatileAccess(0,atomic(1773175200),1);
        lockEnter(0,lock(256050525L));
        volatileAccess(0,atomic(1773175200),1);
        volatileAccess(0,atomic(1773175200),3);
        lockExit(0,lock(256050525L));
    }
}
