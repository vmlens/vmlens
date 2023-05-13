package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestBuilder;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestBuilder.FIRST_WORKER_THREAD_INDEX;
import static com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestBuilder.MAIN_THREAD_INDEX;

public class TestFeatureVolatileFields {
    @Test
    public void testReadWriteSingleVolatileField() {
        ResultTestBuilderForParallelize resultBuilder = new ResultTestBuilderForParallelize();
        FeatureTestBuilder builder = new FeatureTestBuilder(resultBuilder);
        builder.beginThread(MAIN_THREAD_INDEX);
        builder.startThread(FIRST_WORKER_THREAD_INDEX);
        Position read = builder.readFirstVolatileField();
        builder.beginThread(FIRST_WORKER_THREAD_INDEX);
        Position write = builder.writeFirstVolatileField();

        ParallelizeTestMatcher matcher = new ParallelizeTestMatcher();
        matcher.leftBeforeRight(read, write);
        matcher.leftBeforeRight(write, read);
        matcher.runs(3);

        new ParallelizeTestRunner().run(resultBuilder.actualRun(), matcher);
    }
}
