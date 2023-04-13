package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;
import gnu.trove.map.hash.THashMap;

import java.util.Iterator;

public class KeyToThreadIdToElementList<KEY,ELEMENT extends WithThreadIndex>
        implements Iterable<ThreadIdToElementList<ELEMENT>> {

    private final THashMap<KEY,ThreadIdToElementList<ELEMENT>> map =
            new THashMap<KEY,ThreadIdToElementList<ELEMENT>>();

    public void put(KEY key, ELEMENT element) {
        ThreadIdToElementList<ELEMENT> threadIdToElementList = map.get(key);
        if(threadIdToElementList == null) {
            threadIdToElementList = new ThreadIdToElementList<ELEMENT>();
            map.put(key,threadIdToElementList);
        }
        threadIdToElementList.add(element);
    }

    public Iterator<ThreadIdToElementList<ELEMENT>> iterator() {
        return map.values().iterator();
    }

}
