package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import org.junit.Test;

public class ScenarioTestInterleaveVolatileFields {
    @Test
    public void testReadWriteSingleVolatileField()  {
        InterleaveTestBuilder builder = new InterleaveTestBuilder();
        builder.beginMainThread();
        Position read = builder.readFirstVolatileField();
        builder.beginFirstWorkerThread();
        Position write = builder.writeFirstVolatileField();

        InterleaveTestMatcher matcher = new InterleaveTestMatcher();
        matcher.leftBeforeRight(read,write);
        matcher.leftBeforeRight(write,read);
        matcher.runs(3);

        new InterleaveTestRunner().run(builder,matcher);
    }
}
