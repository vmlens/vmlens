package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryDoNotTrace;
import com.vmlens.nottraced.agent.classtransformer.FactoryCollectionAdapterContext;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.nottraced.agent.classtransformer.methodvisitormethodenterexit.MethodEnterExitTransformFactory.addEnterExitTransform;

public class FactoryCollectionDoNotTrace implements FactoryCollection {

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(FactoryCollectionAdapterContext context) {
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = TLinkableWrapper.emptyList();
        if(doNotTraceInMethod(context.nameAndDescriptor(),context.access())) {
            addEnterExitTransform(new MethodCallbackFactoryFactoryDoNotTrace(),result);
        }
        return result;
    }

    private boolean doNotTraceInMethod(NameAndDescriptor nameAndDescriptor, int access) {
        return true;
    }

    @Override
    public boolean computeFrames() {
        return false;
    }

}
