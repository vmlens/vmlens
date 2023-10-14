package com.vmlens.trace.agent.bootstrap.testFixture;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.event.impl.VolatileAccessEvent;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.BlockBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.*;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.Monitor;
import com.vmlens.trace.agent.bootstrap.interleave.run.*;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.ParallelizeActionWithInterleaveAction;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.ParallelizeActionWithRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizedThreadLocal;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.RunContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.vmlens.trace.agent.bootstrap.event.AbstractRuntimeEvent.ID_SyncActions;
import static org.mockito.Mockito.mock;

public class ResultTestBuilder {

    private final RunContext runContext = new RunContext(1, 1);
    private final ThreadIndexToElementList<InterleaveActionWithPositionFactory> threadIndexToFactoryList = new ThreadIndexToElementList<>();
    private TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> factoryList =
            new TLinkedList<>();
    private final TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> blockBuilderList =
            new TLinkedList<>();
    private final List<ParallelizeActionAndThreadLocalWrapper> parallelizeActionAndThreadLocalWrapperList = new
            LinkedList<ParallelizeActionAndThreadLocalWrapper>();
    private final Map<Integer, CallbackStatePerThread> threadIndexToThreadLocalWrapperMock = new HashMap<>();


    private List<InterleaveActionWithPositionFactoryAndOrRuntimeEvent> actualRun = new
            LinkedList<>();

    private List<InterleaveActionWithPositionFactoryAndOrRuntimeEvent> expectedRun = new
            LinkedList<>();


    private ThreadIndexToElementList<Position> positions = new ThreadIndexToElementList<Position>();
    private final ParallelizeFacade parallelizeFacade = new ParallelizeFacade(null);

    public ResultTestBuilder() {
        Run run = new RunMockFactory().create(new ActualRunMock(actualRun), runContext);
        threadIndexToThreadLocalWrapperMock.put(0, threadLocalWrapper(0, 1L, run));
        threadIndexToThreadLocalWrapperMock.put(1, threadLocalWrapper(1, 15L, run));
        threadIndexToThreadLocalWrapperMock.put(2, threadLocalWrapper(2, 20L, run));
    }

    private static CallbackStatePerThread threadLocalWrapper(int threadIndex, long threadId,
                                                             Run run) {
        CallbackStatePerThread threadLocalWrapperMock = new CallbackStatePerThread(1L, null);
        threadLocalWrapperMock.setParallelizedThreadLocal(new ParallelizedThreadLocal(run, threadIndex));
        return threadLocalWrapperMock;
    }


    public void volatileAccess(int fieldId, int operation, Position position) {
        VolatileAccessEvent actual = new VolatileAccessEvent(1L, 5, fieldId, 5, 5, operation, 10L);
        VolatileAccessEvent expected = new VolatileAccessEvent(1L, 5, fieldId, 5, 5, operation, 10L);
        expectedRun.add(new ContainerForRuntimeEvent(expected));

        add(new ParallelizeActionWithRuntimeEvent(actual), position);
        VolatileFieldAccess action = new VolatileFieldAccess(fieldId, operation);

        expectedRun.add(new ContainerForInterleaveActionWithPositionFactory(new InterleaveActionWithPositionFactoryImpl(action, position.threadIndex())));

        add(new ParallelizeActionWithInterleaveAction(action), position);
        add(action, position);
    }

    public void monitorEnter(int id, Position temp) {
        add(new LockOrMonitorEnterImpl(new Monitor(id)), temp);
    }

    public void monitorExit(int id, Position temp) {
        add(new LockOrMonitorExit(new Monitor(id)), temp);
    }


    public void startThread(int index, Position position) {
        add(new ThreadStartFactory(position.threadIndex, index), position);
        blockBuilderList.add(new TLinkableWrapper<ElementAndPosition<BlockBuilder>>
                (new ElementAndPosition(new ThreadStart(index), position)));
    }

    public void joinThread(int index, Position position) {
        add(new ThreadJoinFactory(position.threadIndex, index), position);
        blockBuilderList.add(new TLinkableWrapper<ElementAndPosition<BlockBuilder>>
                (new ElementAndPosition(new ThreadJoin(index), position)));
    }

    public ThreadIndexToElementList<Position> positions() {
        return positions;
    }

    public TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> factoryList() {
        return factoryList;
    }

    public ThreadIndexToElementList<InterleaveActionWithPositionFactory> threadIndexToFactoryList() {
        return threadIndexToFactoryList;
    }

    public TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> blockBuilderList() {
        return blockBuilderList;
    }

    public List<ParallelizeActionAndThreadLocalWrapper> parallelizeActionAndThreadLocalWrapperList() {
        return parallelizeActionAndThreadLocalWrapperList;
    }

    public ParallelizeFacade parallelizeFacade() {
        return parallelizeFacade;
    }

    public List<InterleaveActionWithPositionFactoryAndOrRuntimeEvent> actualRun() {
        return actualRun;
    }

    public List<InterleaveActionWithPositionFactoryAndOrRuntimeEvent> expectedRun() {
        return expectedRun;
    }

    private void add(ParallelizeAction parallelizeAction, Position position) {
        parallelizeActionAndThreadLocalWrapperList.add(
                new ParallelizeActionAndThreadLocalWrapper(parallelizeAction,
                        threadIndexToThreadLocalWrapperMock.get(position.threadIndex())));
    }

    private void add(InterleaveActionWithPositionFactory interleaveActionWithPositionFactory, Position position) {
        factoryList.add(new TLinkableWrapper<>(interleaveActionWithPositionFactory));
        threadIndexToFactoryList.add(interleaveActionWithPositionFactory);
        positions.add(position);
    }

    private void add(InterleaveAction interleaveAction, Position position) {
        add(new InterleaveActionWithPositionFactoryImpl(interleaveAction, position.threadIndex), position);
        blockBuilderList.add(new TLinkableWrapper<ElementAndPosition<BlockBuilder>>
                (new ElementAndPosition(interleaveAction, position)));
    }
}
