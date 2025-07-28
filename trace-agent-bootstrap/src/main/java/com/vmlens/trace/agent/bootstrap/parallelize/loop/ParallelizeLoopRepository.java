package com.vmlens.trace.agent.bootstrap.parallelize.loop;


import com.vmlens.trace.agent.bootstrap.description.TestLoopDescription;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.THashMap;

import java.lang.reflect.Field;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;


/**
 * Adapter for the generic class @see gnu.trove.map.hash.THashMap
 */
public class ParallelizeLoopRepository {
    private final Object lock = new Object();
    private final ParallelizeLoopFactory parallelizeLoopFactory;
    private final THashMap<Object, ParallelizeLoop> object2ParallelizeLoop = new THashMap<Object, ParallelizeLoop>();
    private int maxLoopId = 0;

    public ParallelizeLoopRepository(ParallelizeLoopFactory parallelizeLoopFactory) {
        this.parallelizeLoopFactory = parallelizeLoopFactory;
    }

    public ParallelizeLoop getOrCreate(Object config, TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents) {
        synchronized (lock) {
            ParallelizeLoop parallelizeLoop = object2ParallelizeLoop.get(config);
            if (parallelizeLoop == null) {
                parallelizeLoop = parallelizeLoopFactory.create(maxLoopId);
                try {
                    Field field = config.getClass().getField("name");
                    String name = field.get(config).toString();
                    serializableEvents.add(wrap(new TestLoopDescription(maxLoopId, name)));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    serializableEvents.add(wrap(new TestLoopDescription(maxLoopId, e.getMessage())));
                }


                maxLoopId++;
                object2ParallelizeLoop.put(config, parallelizeLoop);
                return parallelizeLoop;
            }
            return parallelizeLoop;
        }
    }

}
