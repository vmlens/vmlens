package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryAll;
import com.vmlens.nottraced.agent.classtransformer.methodvisitor.AddArrayAccessAccessCall;
import com.vmlens.nottraced.agent.classtransformer.methodvisitor.AddFieldAccessCall;
import com.vmlens.nottraced.agent.classtransformer.methodvisitor.AddMethodCall;
import com.vmlens.nottraced.agent.classtransformer.methodvisitor.AddMonitorCall;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.transformed.agent.bootstrap.fieldrepository.FieldRepositoryForTransform;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.nottraced.agent.classtransformer.methodvisitormethodenterexit.MethodEnterExitTransformFactory.addEnterExitTransform;
import static com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper.wrap;

public class FactoryCollectionAll extends FactoryCollectionPreAnalyzedOrAll {

    private final FieldRepositoryForTransform fieldIdMap;
    private final MethodRepositoryForTransform methodCallIdMap;

    public FactoryCollectionAll(FieldRepositoryForTransform fieldIdMap, MethodRepositoryForTransform methodCallIdMap) {
        this.fieldIdMap = fieldIdMap;
        this.methodCallIdMap = methodCallIdMap;
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategyAfterFilter(
            NameAndDescriptor nameAndDescriptor,
            int access,
            int methodId,
            MethodRepositoryForTransform methodRepositoryForTransform) {
        // Set strategy synchronized or normal
        new AnalyzeMethodAccess(new OnMethodAccess(methodRepositoryForTransform, methodId))
                .analyze(access);
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = new TLinkedList<>();

        result.add(wrap(AddMonitorCall.factory()));
        result.add(wrap(AddFieldAccessCall.factory(fieldIdMap)));
        result.add(wrap(AddArrayAccessAccessCall.factory()));

        addEnterExitTransform(new MethodCallbackFactoryFactoryAll(),result);
        result.add(wrap(AddMethodCall.factory(methodCallIdMap, new MethodCallbackFactoryFactoryAll())));
        return result;
    }

}
