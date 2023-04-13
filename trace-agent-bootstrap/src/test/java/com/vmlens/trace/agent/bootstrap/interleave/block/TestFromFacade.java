package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveTestBuilder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestFromFacade {

    @Test
    public void testAlternatingOrderContainerFactory() {
        InterleaveTestBuilder builder = new InterleaveTestBuilder();
        builder.beginMainThread();
        Position read = builder.readFirstVolatileField();
        builder.beginFirstWorkerThread();
        Position write = builder.writeFirstVolatileField();

        AlternatingOrderContainer alternatingOrderContainer = new AlternatingOrderContainerFactory()
                .create(builder.buildAsList());

        AlternatingOrderElement[] alernatingOrder = new AlternatingOrderElement[1];
        alernatingOrder[0] = new AlternatingOrderElement(new LeftBeforeRight(write,read),
                new LeftBeforeRight(read,write));

        AlternatingOrderContainer expected = new AlternatingOrderContainer(new LeftBeforeRight[0],alernatingOrder);

        assertThat(alternatingOrderContainer,is(expected));
    }


}
