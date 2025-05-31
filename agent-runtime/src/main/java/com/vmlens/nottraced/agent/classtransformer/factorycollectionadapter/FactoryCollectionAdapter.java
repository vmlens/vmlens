package com.vmlens.nottraced.agent.classtransformer.factorycollectionadapter;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public interface FactoryCollectionAdapter {

    TLinkedList<TLinkableWrapper<MethodVisitorFactory>> get(NameAndDescriptor nameAndDescriptor,
                                                            int access,
                                                            int methodId,
                                                            MethodRepositoryForTransform methodRepositoryForTransform);


}
