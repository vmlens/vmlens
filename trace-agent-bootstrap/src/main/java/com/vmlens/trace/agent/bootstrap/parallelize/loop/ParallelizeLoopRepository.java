package com.vmlens.trace.agent.bootstrap.parallelize.loop;


import com.vmlens.api.AllInterleavingsBuilder;
import com.vmlens.trace.agent.bootstrap.description.TestLoopDescription;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContextBuilder;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.THashMap;

import java.lang.reflect.Field;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;


public class ParallelizeLoopRepository {
    private final Object lock = new Object();
    private final ParallelizeLoopFactory parallelizeLoopFactory;
    private final THashMap<Object, ParallelizeLoop> object2ParallelizeLoop = new THashMap<>();
    private int maxLoopId = 0;

    public ParallelizeLoopRepository(ParallelizeLoopFactory parallelizeLoopFactory) {
        this.parallelizeLoopFactory = parallelizeLoopFactory;
    }

    public ParallelizeLoop getOrCreate(Object config, TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents) {
        synchronized (lock) {
            ParallelizeLoop parallelizeLoop = object2ParallelizeLoop.get(config);
            if (parallelizeLoop == null) {
                try {
                    /*
                    we need to use reflection since AllInterleavings might not be loaded by the bootstrap classloader in gradle
                    class com.vmlens.api.AllInterleavings cannot be cast to class com.vmlens.api.AllInterleavings (com.vmlens.api.AllInterleavings is in module com.vmlens.api@1.2.8-SNAPSHOT of loader 'app'; com.vmlens.api.AllInterleavings is in unnamed module of loader 'bootstrap')
                        java.lang.ClassCastException: class com.vmlens.api.AllInterleavings cannot be cast to class com.vmlens.api.AllInterleavings (com.vmlens.api.AllInterleavings is in module com.vmlens.api@1.2.8-SNAPSHOT of loader 'app'; com.vmlens.api.AllInterleavings is in unnamed module of loader 'bootstrap')
	                    at com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopRepository.getOrCreate(ParallelizeLoopRepository.java:33)
	                    at com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade.hasNext(ParallelizeFacade.java:41)
	                    at com.vmlens.trace.agent.bootstrap.callback.impl.VmlensApiCallbackImpl.hasNext(VmlensApiCallbackImpl.java:16)
	                    at com.vmlens.trace.agent.bootstrap.callback.VmlensApiCallback.hasNext(VmlensApiCallback.java:28)
	                    at com.vmlens.api@1.2.8-SNAPSHOT/com.vmlens.api.AllInterleavings.hasNext(AllInterleavings.java:55)
	                    at org.hiero.consensus.model@0.65.0-SNAPSHOT/org.hiero.consensus.model.sequence.map.ConcurrentSequenceMapTest.readFromCopyAndUpdateMutable(ConcurrentSequenceMapTest.java:32)
	                    at java.base/java.lang.reflect.Method.invoke(Method.java:580)
	                    at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	                    at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                     */
                    Field field = config.getClass().getField("name");
                    String name = field.get(config).toString();
                    serializableEvents.add(wrap(new TestLoopDescription(maxLoopId, name)));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    serializableEvents.add(wrap(new TestLoopDescription(maxLoopId, e.getMessage())));
                }
                InterleaveLoopContextBuilder builder = new InterleaveLoopContextBuilder();
                builder.withMaximumIterations(getValue(config,"maximumIterations" ,
                        AllInterleavingsBuilder.MAXIMUM_ITERATIONS,serializableEvents));
                builder.withMaximumAlternatingOrders(getValue(config,"maximumAlternatingOrders" ,
                        AllInterleavingsBuilder.MAXIMUM_ALTERNATING_ORDERS,serializableEvents));

                parallelizeLoop = parallelizeLoopFactory.create(maxLoopId, builder.build());
                maxLoopId++;
                object2ParallelizeLoop.put(config, parallelizeLoop);
                return parallelizeLoop;
            }
            return parallelizeLoop;
        }
    }

    public void remove(Object obj) {
        synchronized (lock) {
            object2ParallelizeLoop.remove(obj);
        }
    }


    private int getValue(Object config,
                         String fieldName,
                         int defaultValue,
                         TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents ) {
        try {
            Field field = config.getClass().getField(fieldName);
            return field.getInt(config);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            serializableEvents.add(wrap(new TestLoopDescription(maxLoopId, e.getMessage())));
        }
        return defaultValue;
    }


}
