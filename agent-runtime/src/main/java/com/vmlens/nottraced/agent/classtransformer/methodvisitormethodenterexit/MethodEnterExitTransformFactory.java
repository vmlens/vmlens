package com.vmlens.nottraced.agent.classtransformer.methodvisitormethodenterexit;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactory;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.FactoryContext;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.TransformFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.MethodVisitor;

import static com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper.wrap;

public class MethodEnterExitTransformFactory implements TransformFactory {


    private final MethodCallbackFactoryFactory factoryFactory;

    public MethodEnterExitTransformFactory(MethodCallbackFactoryFactory factoryFactory) {
        this.factoryFactory = factoryFactory;
    }

    public static void addEnterExitTransform(MethodCallbackFactoryFactory factory,
                           TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result) {
        result.add(wrap(new MethodEnterExitTransformFactory(factory)));
    }

    @Override
    public MethodVisitor create(FactoryContext factoryContext, MethodVisitor previous) {
        return new MethodEnterExitTransform(previous,factoryContext,factoryFactory);
    }

}
