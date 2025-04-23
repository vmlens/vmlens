package com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.preanalyzedstrategy;

import com.anarsoft.trace.agent.runtime.classtransformer.NameAndDescriptor;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryForPreAnalyzedAndAll;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class WithObjectMethodCall implements PreAnalyzedStrategy {
    @Override
    public void addMethodCallToResult(FactoryForPreAnalyzedAndAll factory, MethodRepositoryForTransform methodRepositoryForTransform, NameAndDescriptor nameAndDescriptor, TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result) {

    }


}
