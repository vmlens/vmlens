package com.vmlens.trace.agent.bootstrap.testfixture;


import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.VolatileAccessEvent;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.BlockBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadLocalDataWhenInTestMap;
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


    private final ThreadLocalDataWhenInTestMap runContext = new ThreadLocalDataWhenInTestMap();

    private final TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> blockBuilderList =
            new TLinkedList<>();
    private final List<ParallelizeActionAndThreadLocalWrapper> parallelizeActionAndThreadLocalWrapperList = new
            LinkedList<ParallelizeActionAndThreadLocalWrapper>();
    private final Map<Integer, ThreadLocalForParallelize> threadIndexToThreadLocalWrapperMock = new HashMap<>();

    private ThreadIndexToElementList<Position> positions = new ThreadIndexToElementList<Position>();
    private final ParallelizeFacade parallelizeFacade = new ParallelizeFacade(null);
    private List<SerializableEvent> givenEvents = new
            LinkedList<>();


    public ResultTestBuilder() {
        Run run = mock(Run.class);
        threadIndexToThreadLocalWrapperMock.put(0, threadLocalWrapper(0, 1L, run));
        threadIndexToThreadLocalWrapperMock.put(1, threadLocalWrapper(1, 15L, run));
        threadIndexToThreadLocalWrapperMock.put(2, threadLocalWrapper(2, 20L, run));
    }

    private static ThreadLocalForParallelize threadLocalWrapper(int threadIndex, long threadId, Run run) {
        ThreadLocalForParallelize threadLocalWrapperMock = new ThreadLocalForParallelize(1L);
        threadLocalWrapperMock.setThreadLocalDataWhenInTest(new ThreadLocalDataWhenInTest(run, threadIndex));
        return threadLocalWrapperMock;
    }


    public void volatileAccess(int fieldId, int operation, Position position) {
        VolatileAccessEvent actual = new VolatileAccessEvent();
        actual.setThreadIndex(1);
        actual.setFieldId(fieldId);
        actual.setOperation(operation);
        actual.setOrder(VOLATILE_FIELD_EVENT_ORDER);
        actual.setObjectHashCode(VOLATILE_FIELD_EVENT_OBJECT_HASH_CODE);
        actual.setMethodId(VOLATILE_FIELD_EVENT_METHOD_ID);

        VolatileAccessEvent expected = new VolatileAccessEvent();
        expected.setThreadIndex(1);
        expected.setFieldId(fieldId);
        expected.setOperation(operation);
        expected.setOrder(VOLATILE_FIELD_EVENT_ORDER);
        expected.setObjectHashCode(VOLATILE_FIELD_EVENT_OBJECT_HASH_CODE);
        expected.setMethodId(VOLATILE_FIELD_EVENT_METHOD_ID);


        // Fixme correct
        // givenEvents.add(expected);


    }



    public List<SerializableEvent> givenEvents() {
        return givenEvents;
    }


}
