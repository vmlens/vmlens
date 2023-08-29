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

    // Visible for Test
    public ThreadIndexToElementList<ELEMENT> get(KEY key) {
        return map.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyToThreadIdToElementList<?, ?> that = (KeyToThreadIdToElementList<?, ?>) o;

        return map.equals(that.map);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }
}
