package com.vmlens.trace.agent.bootstrap.testFixture;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThreadForParallelize;
import com.vmlens.trace.agent.bootstrap.event.StaticEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.VolatileAccessEvent;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.BlockBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.*;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.Monitor;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.RunContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;


public class ResultTestBuilder {

    public static final int VOLATILE_FIELD_EVENT_ORDER = 5;
    public static final int VOLATILE_FIELD_EVENT_METHOD_ID = 22;
    public static final long VOLATILE_FIELD_EVENT_OBJECT_HASH_CODE = 33L;


    private final RunContext runContext = new RunContext(1, 1);
    private final ThreadIndexToElementList<InterleaveActionWithPositionFactory> threadIndexToFactoryList = new ThreadIndexToElementList<>();
    private TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> factoryList =
            new TLinkedList<>();
    private final TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> blockBuilderList =
            new TLinkedList<>();
    private final List<ParallelizeActionAndThreadLocalWrapper> parallelizeActionAndThreadLocalWrapperList = new
            LinkedList<ParallelizeActionAndThreadLocalWrapper>();
    private final Map<Integer, CallbackStatePerThreadForParallelize> threadIndexToThreadLocalWrapperMock = new HashMap<>();

    private ThreadIndexToElementList<Position> positions = new ThreadIndexToElementList<Position>();
    private final ParallelizeFacade parallelizeFacade = new ParallelizeFacade(null);
    private List<StaticEvent> givenEvents = new
            LinkedList<>();


    public ResultTestBuilder() {
        Run run = mock(Run.class);
        threadIndexToThreadLocalWrapperMock.put(0, threadLocalWrapper(0, 1L, run));
        threadIndexToThreadLocalWrapperMock.put(1, threadLocalWrapper(1, 15L, run));
        threadIndexToThreadLocalWrapperMock.put(2, threadLocalWrapper(2, 20L, run));
    }

    private static CallbackStatePerThreadForParallelize threadLocalWrapper(int threadIndex, long threadId, Run run) {
        CallbackStatePerThreadForParallelize threadLocalWrapperMock = new CallbackStatePerThreadForParallelize(1L, null);
        threadLocalWrapperMock.setThreadLocalDataWhenInTest(new ThreadLocalDataWhenInTest(run, threadIndex, null, threadId));
        return threadLocalWrapperMock;
    }


    public void volatileAccess(int fieldId, int operation, Position position) {
        VolatileAccessEvent actual = new VolatileAccessEvent()
                .setThreadId(1L)
                .setFieldId(fieldId)
                .setOperation(operation)
                .setOrder(VOLATILE_FIELD_EVENT_ORDER)
                .setObjectHashCode(VOLATILE_FIELD_EVENT_OBJECT_HASH_CODE)
                .setMethodId(VOLATILE_FIELD_EVENT_METHOD_ID);

        VolatileAccessEvent expected = new VolatileAccessEvent()
                .setThreadId(1L)
                .setFieldId(fieldId)
                .setOperation(operation)
                .setOrder(VOLATILE_FIELD_EVENT_ORDER)
                .setObjectHashCode(VOLATILE_FIELD_EVENT_OBJECT_HASH_CODE)
                .setMethodId(VOLATILE_FIELD_EVENT_METHOD_ID);


        givenEvents.add(expected);


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

    public List<StaticEvent> givenEvents() {
        return givenEvents;
    }


}
