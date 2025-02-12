package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory;

import com.anarsoft.trace.agent.runtime.classtransformer.NameAndDescriptor;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class MapForList<ELEMENT> {

    private final THashMap<NameAndDescriptor, TLinkedList<TLinkableWrapper<ELEMENT>>> map
            = new THashMap<>();

    public void put(NameAndDescriptor key, ELEMENT value) {
        TLinkedList<TLinkableWrapper<ELEMENT>> list = map.get(key);
        if (list == null) {
            list = new TLinkedList<>();

        }
    }

    public TLinkedList<TLinkableWrapper<ELEMENT>> get(NameAndDescriptor key) {
        return map.get(key);
    }
}
