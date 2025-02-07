package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory;

import com.anarsoft.trace.agent.runtime.classtransformer.MethodVisitorAnalyzeAndTransformFactoryMap;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactoryFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.MethodVisitor;

public class SelectMethodVisitorFactoryStrategyForAnalyze implements SelectMethodVisitorFactoryStrategy {

    private final TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactoryFactory>>
            methodVisitorFactoryAnalyzeList;

    public SelectMethodVisitorFactoryStrategyForAnalyze(TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactoryFactory>>
                                                  methodVisitorFactoryAnalyzeList) {
        this.methodVisitorFactoryAnalyzeList = methodVisitorFactoryAnalyzeList;
    }

    @Override
    public MethodVisitor create(FactoryContext context,
                                MethodVisitor previous,
                                MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory, MethodCallIdMap methodCallIdMap) {
        MethodVisitor current = previous;
        for (TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactoryFactory> element : methodVisitorFactoryAnalyzeList) {
            MethodVisitorAnalyzeAndTransformFactory factory = element.element().create(methodCallIdMap);
            methodIdToFactory.put(context.methodId(), factory);
            current = factory.createAnalyze(context.methodId(), current);
        }
        return current;
    }
}
