package com.anarsoft.trace.agent.runtime.classtransformer.factorycollection;

import com.anarsoft.trace.agent.runtime.classtransformer.NameAndDescriptor;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public interface FactoryCollection {

    TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyze(NameAndDescriptor nameAndDescriptor, int access);

    TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(NameAndDescriptor nameAndDescriptor,
                                                                                   int access,
                                                                                   int methodId,
                                                                                   MethodRepositoryForTransform methodRepositoryForTransform);

}
