package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;
import gnu.trove.map.hash.THashMap;

import java.util.Iterator;

public class KeyToThreadIdToElementList<KEY, ELEMENT extends WithThreadIndex>
        implements Iterable<ThreadIndexToElementList<ELEMENT>> {

    private final THashMap<KEY, ThreadIndexToElementList<ELEMENT>> map =
            new THashMap<KEY, ThreadIndexToElementList<ELEMENT>>();

    public void put(KEY key, ELEMENT element) {
        ThreadIndexToElementList<ELEMENT> threadIndexToElementList = map.get(key);
        if (threadIndexToElementList == null) {
            threadIndexToElementList = new ThreadIndexToElementList<ELEMENT>();
            map.put(key, threadIndexToElementList);
        }
        threadIndexToElementList.add(element);
    }

    public Iterator<ThreadIndexToElementList<ELEMENT>> iterator() {
        return map.values().iterator();
    }

}
