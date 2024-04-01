package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.event.StaticEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.ParallelizeActionForThreadStart;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.testFixture.InitialRunMockFactory;
import com.vmlens.trace.agent.bootstrap.testFixture.QueueInMock;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class StartThreadRunTest {

    @Test
    public void testInitialRecording() {
        // Given
        Run run = new InitialRunMockFactory().create(1, 1);
        RunnableOrThreadWrapper threadWrapper = new RunnableOrThreadWrapper(new Object());

        List<StaticEvent> eventList = new LinkedList<>();
        QueueInMock queue = new QueueInMock(eventList);

        ThreadLocalDataWhenInTest startingThread = new ThreadLocalDataWhenInTest(run, 0, queue, 1L);
        ThreadLocalForParallelize startedThread = new ThreadLocalForParallelize(2L, queue);

        // When
        run.after(new ParallelizeActionForThreadStart(threadWrapper), startingThread);
        run.newTask(threadWrapper, startedThread);

        // Then
        // Fixme prüfung

    }


}
