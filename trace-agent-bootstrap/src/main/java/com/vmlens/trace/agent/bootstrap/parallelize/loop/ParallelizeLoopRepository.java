package com.vmlens.trace.agent.bootstrap.parallelize.loop;


import com.vmlens.api.AllInterleavingsBuilder;
import com.vmlens.trace.agent.bootstrap.description.LoopControl;
import com.vmlens.trace.agent.bootstrap.description.TestLoopDescription;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldOwnerAndName;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContextBuilder;
import gnu.trove.map.hash.THashMap;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.List;

import static com.vmlens.api.AllInterleavingsBuilder.REPORT_AS_SUMMARY_THRESHOLD;
import static com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEvent.fromException;


public class ParallelizeLoopRepository {
    private final Object lock = new Object();
    private final ParallelizeLoopFactory parallelizeLoopFactory;
    private final THashMap<Object, ParallelizeLoop> object2ParallelizeLoop = new THashMap<>();
    private int maxLoopId = 0;

    public ParallelizeLoopRepository(ParallelizeLoopFactory parallelizeLoopFactory) {
        this.parallelizeLoopFactory = parallelizeLoopFactory;
    }

    public ParallelizeLoop getOrCreate(Object config, QueueIn queueIn) {
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
                    queueIn.offer(new TestLoopDescription(maxLoopId, name , getValue(config,
                            "reportAsSummaryThreshold" ,
                            REPORT_AS_SUMMARY_THRESHOLD ,
                            queueIn)));

                    intentionalDataRaces(maxLoopId,config,queueIn);


                } catch (NoSuchFieldException | IllegalAccessException e) {
                    queueIn.offer(fromException(maxLoopId, e));
                }
                InterleaveLoopContextBuilder builder = new InterleaveLoopContextBuilder();
                builder.withMaximumIterations(getValue(config,"maximumIterations" ,
                        AllInterleavingsBuilder.MAXIMUM_ITERATIONS,queueIn));
                builder.withRemoveCycleThreshold(getValue(config,"removeCycleThreshold" ,
                        AllInterleavingsBuilder.REMOVE_CYCLE_THRESHOLD,queueIn));

                parallelizeLoop = parallelizeLoopFactory.create(maxLoopId, builder.build(queueIn,maxLoopId));
                maxLoopId++;
                object2ParallelizeLoop.put(config, parallelizeLoop);
                return parallelizeLoop;
            }
            return parallelizeLoop;
        }
    }

    private void intentionalDataRaces(int loopId, Object config, QueueIn queueIn) throws NoSuchFieldException, IllegalAccessException {
        Field field = config.getClass().getField("intentionalDataRaces");
        List<AbstractMap.SimpleImmutableEntry<String,String>> list =
                (List<AbstractMap.SimpleImmutableEntry<String,String>>) field.get(config);
        if(list.isEmpty()) {
            return;
        }
        int[] array = new int[list.size()];
        FieldRepositoryForTransform fieldRepositoryForTransform = FieldRepositorySingleton.INSTANCE;
        int index = 0;
        for(AbstractMap.SimpleImmutableEntry<String,String> elem : list) {
            array[index] =   fieldRepositoryForTransform.asInt(new FieldOwnerAndName(elem.getKey(), elem.getValue()));
            index++;
        }
        queueIn.offer(new LoopControl(loopId,array));
    }

    public void remove(Object obj) {
        synchronized (lock) {
            object2ParallelizeLoop.remove(obj);
        }
    }


    private int getValue(Object config,
                         String fieldName,
                         int defaultValue,
                         QueueIn queueIn) {
        try {
            Field field = config.getClass().getField(fieldName);
            return field.getInt(config);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            queueIn.offer(fromException(maxLoopId, e));
        }
        return defaultValue;
    }
    
}
