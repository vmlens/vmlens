package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.anarsoft.trace.agent.description.WhileLoopDescription;
import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopRepository;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ParallelizeLoopFactoryImpl;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.apache.commons.lang3.tuple.Pair;


/**
 * Fixme synchronization
 */


public class ParallelizeFacade {

    private static volatile ParallelizeFacade parallelizeFacade =
            new ParallelizeFacade(new ParallelizeLoopRepository(new ParallelizeLoopFactoryImpl()));
    private final ParallelizeLoopRepository parallelizeLoopRepository;
    private ParallelizeLoop currentLoop;


    public static ParallelizeFacade parallelize() {
        return parallelizeFacade;
    }

    public ParallelizeFacade(ParallelizeLoopRepository parallelizeLoopRepository) {
        this.parallelizeLoopRepository = parallelizeLoopRepository;
    }

    public void beginThreadMethodEnter(ThreadLocalForParallelize threadLocalWrapperForParallelize,
                                       RunnableOrThreadWrapper beganTask) {
        if (currentLoop != null) {
            currentLoop.beginThreadMethodEnter(threadLocalWrapperForParallelize, beganTask);
        }
    }

    public HasNextResult hasNext(ThreadLocalForParallelize threadLocalWrapperForParallelize, Object obj) {
        Pair<ParallelizeLoop, Boolean> currentLoopAndNewFlag = parallelizeLoopRepository.getOrCreate(obj);
        currentLoop = currentLoopAndNewFlag.getLeft();

        TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEventList =
                new TLinkedList<>();

        SerializableEvent newRunEvent = currentLoop.hasNext(threadLocalWrapperForParallelize);

        if (newRunEvent == null) {
            return new HasNextResult(false, serializableEventList);
        }

        if (currentLoopAndNewFlag.getRight()) {
            serializableEventList.add(TLinkableWrapper.<SerializableEvent>wrapp(
                    new WhileLoopDescription(currentLoop.loopId(), ((AllInterleavings) obj).name)));
        }

        serializableEventList.add(TLinkableWrapper.wrapp(newRunEvent));

        return new HasNextResult(true, serializableEventList);
    }

    public void close(ThreadLocalForParallelize threadLocalWrapperForParallelize, Object obj) {
        currentLoop.close(threadLocalWrapperForParallelize);
        currentLoop = null;
    }
}
