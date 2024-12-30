package com.anarsoft.trace.agent.runtime.classtransformerall;

import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitor.MethodVisitorAnalyzeAndTransformFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class MethodVisitorAnalyzeAndTransformFactoryMap {

    private final THashMap<MethodId, TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactory>>> map =
            new THashMap<>();

    public void put(MethodId methodId, MethodVisitorAnalyzeAndTransformFactory factory) {
        TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactory>> result =
                map.computeIfAbsent(methodId, k -> new TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactory>>());
        result.add(wrap(factory));
    }

    public TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactory>> get(MethodId methodId) {
        return map.get(methodId);
    }


}