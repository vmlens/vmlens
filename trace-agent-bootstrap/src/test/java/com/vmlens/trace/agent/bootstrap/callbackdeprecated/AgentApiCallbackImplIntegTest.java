package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

import com.anarsoft.trace.agent.description.TestLoopDescription;
import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RunStartEvent;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AgentApiCallbackImplIntegTest {

    @Test
    public void newRun() {
        // Expected values
        String givenName = "TestLoop";
        AllInterleavings givenAllInterleavings = new AllInterleavings(givenName);

        RunStartEvent expectedRunStartEvent = new RunStartEvent(0, 0);
        TestLoopDescription expectedWhileLoopNameEvent = new TestLoopDescription(0, givenName);

        // Initial should lead to true
        ThreadLocalForParallelize threadLocalForParallelize = new ThreadLocalForParallelize(1L);
        List<SerializableEvent> eventList = new LinkedList<>();
        AgentApiCallbackImpl agentApiCallbackImpl = new AgentApiCallbackImpl();
        boolean hasNext = agentApiCallbackImpl.hasNext(givenAllInterleavings,
                new QueueInMock(eventList),
                threadLocalForParallelize);

        assertThat(hasNext, is(true));

        // and two events
        RunStartEvent runStartEvent;
        TestLoopDescription whileLoopNameEvent;

        assertThat(eventList.size(), is(2));

        if (eventList.get(0) instanceof RunStartEvent) {
            runStartEvent = (RunStartEvent) eventList.get(0);
            whileLoopNameEvent = (TestLoopDescription) eventList.get(1);
        } else {
            runStartEvent = (RunStartEvent) eventList.get(1);
            whileLoopNameEvent = (TestLoopDescription) eventList.get(0);
        }

        assertThat(runStartEvent, is(expectedRunStartEvent));
        assertThat(whileLoopNameEvent, is(expectedWhileLoopNameEvent));

        // the second call should lead to one event and false
        eventList = new LinkedList<>();
        hasNext = agentApiCallbackImpl.hasNext(givenAllInterleavings,
                new QueueInMock(eventList),
                threadLocalForParallelize);

        assertThat(hasNext, is(false));
        assertThat(eventList.size(), is(1));
    }
}
