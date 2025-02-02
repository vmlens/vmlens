package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory;

import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public interface FactoryCollectionAdapter {

    TLinkedList<TLinkableWrapper<MethodVisitorFactory>> get(NameAndDescriptor nameAndDescriptor,
                                                            boolean isSynchronized);


}
