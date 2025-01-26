package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory;

import com.anarsoft.trace.agent.runtime.classtransformer.MethodId;
import com.anarsoft.trace.agent.runtime.classtransformer.MethodVisitorAnalyzeAndTransformFactoryMap;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorForTransformFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ACC_STATIC;

public class MethodVisitorFactoryForTransform implements MethodVisitorFactory {

    private final TLinkedList<TLinkableWrapper<MethodVisitorForTransformFactory>> methodVisitorFactoryList;

    public MethodVisitorFactoryForTransform(TLinkedList<TLinkableWrapper<MethodVisitorForTransformFactory>> methodVisitorFactoryList) {
        this.methodVisitorFactoryList = methodVisitorFactoryList;
    }

    @Override
    public MethodVisitor create(MethodVisitorFactoryContext context,
                                MethodVisitor previous,
                                MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory,
                                MethodCallIdMap methodCallIdMap) {
        MethodVisitor current = previous;
        for (TLinkableWrapper<MethodVisitorForTransformFactory> element : methodVisitorFactoryList) {
            current = element.element().create(context.methodId(), current);
        }
        TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactory>> factoryList =
                methodIdToFactory.get(context.methodId());
        if (factoryList != null) {
            for (TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactory> element : factoryList) {
                current = element.element().createTransform(context, current);
            }
        }
        return current;
    }
}
