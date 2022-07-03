package com.vmlens.trace.agent.bootstrap.parallelize.testFixture;


import static com.vmlens.trace.agent.bootstrap.parallelize.testFixture.ExpectedRun.run;
import static com.vmlens.trace.agent.bootstrap.parallelize.testFixture.TestConfig.*;
import static com.vmlens.trace.agent.bootstrap.parallelize.testFixture.VolatileFieldModel.THREAD_ONE_WRITE;
import static com.vmlens.trace.agent.bootstrap.parallelize.testFixture.VolatileFieldModel.THREAD_ZERO_READ;

public class VolatileFieldTestConfig {

    public static final TestConfig VOLATILE_FIELD_WITH_THREAD_START_AND_STOP =
            config(
                    given(THREAD_ZERO_READ, THREAD_ONE_WRITE), expected(run(0, 1), run(1, 0)),
                    3);


}
