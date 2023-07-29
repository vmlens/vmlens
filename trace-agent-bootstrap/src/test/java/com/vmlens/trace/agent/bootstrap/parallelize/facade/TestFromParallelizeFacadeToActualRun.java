package com.vmlens.trace.agent.bootstrap.parallelize.facade;


import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.loop.AgentLoggerForTest;
import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.TestFixture;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeFacadeImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopContainer;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ParallelizeLoopFactoryImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ThreadLocalWrapperMock;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class TestFromParallelizeFacadeToActualRun {

    private static final long SECOND_THREAD_ID = 20L;

    private Object loopIdentifier = new Object();
    private ThreadLocalWrapper mainThread;
    private ThreadLocalWrapper secondThread;

    private ParallelizeFacadeImpl parallelizeFacadeImpl;
    private ParallelizeLoopContainer parallelizeLoopContainer;

    @Before
    public void initBeforeEachTest() {
        // Mocks
        WaitNotifyStrategy waitNotifyStrategyMock = mock(WaitNotifyStrategy.class);
        InterleaveLoop interleaveLoopMock = mock(InterleaveLoop.class);
        // Given
        mainThread = new ThreadLocalWrapperMock(1L);
        secondThread = new ThreadLocalWrapperMock(SECOND_THREAD_ID);
        parallelizeLoopContainer = new ParallelizeLoopContainer(new ParallelizeLoopFactoryImpl(mock(WaitNotifyStrategy.class),
                new AgentLoggerForTest()));
        parallelizeFacadeImpl = new ParallelizeFacadeImpl(parallelizeLoopContainer);
    }

    @Test
    public void volatileReadAndWrite() {
        // Given
        parallelizeFacadeImpl.hasNext(mainThread, loopIdentifier);
        // second thread is started
        TestThreadState threadStateSecond = new TestThreadState(secondThread);
        threadStateSecond.createNewParallelizedThreadLocal(parallelizeLoopContainer.currentRun(), 1);

        // When
        parallelizeFacadeImpl.afterFieldAccessVolatile(mainThread, 1, MemoryAccessType.IS_READ);
        parallelizeFacadeImpl.afterFieldAccessVolatile(secondThread, 1, MemoryAccessType.IS_WRITE);

        // Then
        parallelizeLoopContainer.currentRun().actualRun().debug(new AgentLoggerForTest());
        assertThat(parallelizeLoopContainer.currentRun().actualRun().run(),
                is(TestFixture.volatileReadAndWrite().getLeft()));
    }

    @Test
    public void startThread() {
        // Given
        RunnableOrThreadWrapper runnableOrThreadWrapper = new RunnableOrThreadWrapper(new Object());
        ThreadLocalWrapperMock startedThread = new ThreadLocalWrapperMock(30L);
        parallelizeFacadeImpl.hasNext(mainThread, loopIdentifier);

        // When
        parallelizeFacadeImpl.beforeThreadStart(mainThread, runnableOrThreadWrapper);
        parallelizeFacadeImpl.afterThreadStart(mainThread);
        parallelizeFacadeImpl.beginThreadMethodEnter(startedThread, runnableOrThreadWrapper);
        parallelizeFacadeImpl.afterFieldAccessVolatile(startedThread, 1, MemoryAccessType.IS_READ);

        // Then
        parallelizeLoopContainer.currentRun().actualRun().debug(new AgentLoggerForTest());
        assertThat(parallelizeLoopContainer.currentRun().actualRun().run(),
                is(TestFixture.startThread()));
    }

    @Test
    public void joinThread() {
        // Given
        RunnableOrThreadWrapper runnableOrThreadWrapper = new RunnableOrThreadWrapper(new Object());
        parallelizeFacadeImpl.hasNext(mainThread, loopIdentifier);

        // When
        parallelizeFacadeImpl.beforeThreadStart(mainThread, runnableOrThreadWrapper);
        parallelizeFacadeImpl.afterThreadStart(mainThread);
        parallelizeFacadeImpl.beginThreadMethodEnter(secondThread, runnableOrThreadWrapper);
        parallelizeFacadeImpl.beforeThreadJoin(mainThread, SECOND_THREAD_ID);
        parallelizeFacadeImpl.afterFieldAccessVolatile(secondThread, 1, MemoryAccessType.IS_READ);


        // Then
        parallelizeLoopContainer.currentRun().actualRun().debug(new AgentLoggerForTest());
        assertThat(parallelizeLoopContainer.currentRun().actualRun().run(),
                is(TestFixture.threadJoin().getLeft()));
    }

}
