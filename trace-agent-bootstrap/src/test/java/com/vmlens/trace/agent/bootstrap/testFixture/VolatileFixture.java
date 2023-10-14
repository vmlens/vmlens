package com.vmlens.trace.agent.bootstrap.testFixture;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArraysBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestMatcher;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestMatcherBuilder;

import static com.vmlens.trace.agent.bootstrap.testFixture.FixtureBuilder.FIRST_WORKER_THREAD_INDEX;
import static com.vmlens.trace.agent.bootstrap.testFixture.FixtureBuilder.MAIN_THREAD_INDEX;

public class VolatileFixture {

    public static TestData volatileReadAndWrite() {

        ResultTestBuilder resultTestBuilderForActualRun = new ResultTestBuilder();


        FixtureBuilder builder = new FixtureBuilder(resultTestBuilderForActualRun);
        builder.beginThread(MAIN_THREAD_INDEX);
        Position read = builder.readFirstVolatileField();
        builder.beginThread(FIRST_WORKER_THREAD_INDEX);
        Position write = builder.writeFirstVolatileField();

        OrderArraysBuilder orderArraysBuilder = new OrderArraysBuilder();
        orderArraysBuilder.addAlternatingOrder(withInverse(read, write));


        FeatureTestMatcherBuilder featureTestMatcherBuilder = new FeatureTestMatcherBuilder();
        featureTestMatcherBuilder.both(read, write);
        featureTestMatcherBuilder.runs(2);

        FeatureTestMatcher matcher = featureTestMatcherBuilder.build();

        TestData testData = new TestData();

        testData.setAlternatingOrderContainer(new AlternatingOrderContainer(orderArraysBuilder.build(),
                resultTestBuilderForActualRun.positions()));
        testData.setFeatureTestMatcher(matcher);
        testData.setResultTestBuilder(resultTestBuilderForActualRun);

        return testData;
    }

    public static AlternatingOrderElement withInverse(Position left, Position right) {
        return new AlternatingOrderElement(new LeftBeforeRight(left, right), new LeftBeforeRight(right, left));
    }


}
