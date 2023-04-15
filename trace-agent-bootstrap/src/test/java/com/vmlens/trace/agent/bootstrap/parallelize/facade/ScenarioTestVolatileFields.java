package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import org.junit.Test;

public class ScenarioTestVolatileFields {
    @Test
    public void testReadWriteSingleVolatileField() {
        ParallelizeTestBuilder builder = new ParallelizeTestBuilder();
        builder.beginMainThread();
        Position read = builder.readFirstVolatileField();
        builder.beginFirstWorkerThread();
        Position write = builder.writeFirstVolatileField();

        ParallelizeTestMatcher matcher = new ParallelizeTestMatcher();
        matcher.leftBeforeRight(read, write);
        matcher.leftBeforeRight(write,read);
        matcher.runs(3);

        new ParallelizeTestRunner().run(builder,matcher);
    }
}
