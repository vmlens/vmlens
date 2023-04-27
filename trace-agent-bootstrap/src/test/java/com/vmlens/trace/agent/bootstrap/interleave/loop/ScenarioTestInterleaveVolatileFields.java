package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.InterleaveTestBuilder;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeTestBuilder.FIRST_WORKER_THREAD_INDEX;
import static com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeTestBuilder.MAIN_THREAD_INDEX;

public class ScenarioTestInterleaveVolatileFields {
    @Test
    public void testReadWriteSingleVolatileField()  {
        TestBuilderResultInterleaveFactory testBuilderResultInterleaveFactory = new TestBuilderResultInterleaveFactory();
        InterleaveTestBuilder builder = new InterleaveTestBuilder(testBuilderResultInterleaveFactory);
        builder.beginThread(MAIN_THREAD_INDEX);
        Position read = builder.readFirstVolatileField();
        builder.beginThread(FIRST_WORKER_THREAD_INDEX);
        Position write = builder.writeFirstVolatileField();

        InterleaveTestMatcher matcher = new InterleaveTestMatcher();
        matcher.leftBeforeRight(read,write);
        matcher.leftBeforeRight(write,read);
        matcher.runs(3);

        new InterleaveTestRunner().run(testBuilderResultInterleaveFactory.actualRun(),matcher);
    }
}
