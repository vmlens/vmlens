package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryPreAnalyzed;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodEnterStrategyWithoutParam;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.factory.PreAnalyzedStrategyFactory;
import com.vmlens.nottraced.agent.classtransformer.FactoryCollectionAdapterContext;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.nottraced.agent.classtransformer.methodvisitormethodenterexit.MethodEnterExitTransformFactory.addEnterExitTransform;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

public class FactoryCollectionPreAnalyzedMethodEnterExitOnly implements FactoryCollection {

    private final PreAnalyzedStrategyFactory preAnalyzedStrategyFactory;

    public FactoryCollectionPreAnalyzedMethodEnterExitOnly(PreAnalyzedStrategyFactory preAnalyzedStrategyFactory) {
        this.preAnalyzedStrategyFactory = preAnalyzedStrategyFactory;
    }


    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(FactoryCollectionAdapterContext context) {
        if ((context.access() & ACC_PUBLIC) != ACC_PUBLIC) {
            return TLinkableWrapper.emptyList();
        }
        // Fixme MethodEnterStrategyWithoutParam
        context.methodRepositoryForTransform().setStrategyPreAnalyzed(context.methodId(),
                preAnalyzedStrategyFactory.create(context.className(),context.nameAndDescriptor()));
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = TLinkableWrapper.emptyList();
        addEnterExitTransform(new MethodCallbackFactoryFactoryPreAnalyzed(new MethodEnterStrategyWithoutParam()),result);
        return result;
    }

    @Override
    public boolean computeFrames() {
        return false;
    }

}
