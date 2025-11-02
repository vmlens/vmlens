package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryDoNotTrace;
import com.vmlens.nottraced.agent.classtransformer.FactoryCollectionAdapterContext;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.nottraced.agent.classtransformer.methodvisitormethodenterexit.MethodEnterExitTransformFactory.addEnterExitTransform;

public abstract class FactoryCollectionPreAnalyzedOrAll  implements FactoryCollection {

    @Override
    public final TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(FactoryCollectionAdapterContext context) {
        if(doNotTraceIn(context.nameAndDescriptor())) {
            TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = TLinkableWrapper.emptyList();
            addEnterExitTransform(new MethodCallbackFactoryFactoryDoNotTrace(),result);
            return result;
        }
        return getTransformAndSetStrategyAfterFilter(context.nameAndDescriptor(),
                context.access(),
                context.methodId(),
                context.methodRepositoryForTransform());
    }

    protected abstract  TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategyAfterFilter
            (NameAndDescriptor nameAndDescriptor, int access, int methodId,
             MethodRepositoryForTransform methodRepositoryForTransform);

    private boolean doNotTraceIn(NameAndDescriptor nameAndDescriptor) {
        return nameAndDescriptor.name().equals("<clinit>");
    }

    @Override
    public boolean computeFrames() {
        return false;
    }

}