package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestMatcher;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.ResultTestBuilderForActualRun;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestBuilder.FIRST_WORKER_THREAD_INDEX;
import static com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestBuilder.MAIN_THREAD_INDEX;

public class TestFeatureVolatileFields {
    @Test
    public void testReadWriteSingleVolatileField() {
        ResultTestBuilderForActualRun resultTestBuilderForInterleaveLoop = new ResultTestBuilderForActualRun();
        FeatureTestBuilder builder = new FeatureTestBuilder(resultTestBuilderForInterleaveLoop);
        builder.beginThread(MAIN_THREAD_INDEX);
        Position read = builder.readFirstVolatileField();
        builder.beginThread(FIRST_WORKER_THREAD_INDEX);
        Position write = builder.writeFirstVolatileField();

        FeatureTestMatcher matcher = new FeatureTestMatcher();
        matcher.leftBeforeRight(read, write);
        matcher.leftBeforeRight(write, read);
        matcher.runs(2);

        new InterleaveTestRunner().run(resultTestBuilderForInterleaveLoop, matcher);
    }
}
