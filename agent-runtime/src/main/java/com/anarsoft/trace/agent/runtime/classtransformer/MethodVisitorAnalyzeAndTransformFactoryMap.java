package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class MethodVisitorAnalyzeAndTransformFactoryMap {

    private final THashMap<Integer, TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactory>>> map =
            new THashMap<>();

    public void put(Integer methodId, MethodVisitorAnalyzeAndTransformFactory factory) {
        TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactory>> result =
                map.computeIfAbsent(methodId, k -> new TLinkedList<>());
        result.add(wrap(factory));
    }

    public TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactory>> get(Integer methodId) {
        return map.get(methodId);
    }


}