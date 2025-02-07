package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.AddFieldAccessCall;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.AddMonitorCall;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodenterexit.MethodEnterExitAnalyzeFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodenterexit.MethodEnterExitTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodenterexit.TryCatchBlockCounter;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.fieldidtostrategy.FieldOwnerAndNameToIntMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class FactoryCollectionAll implements FactoryCollection {

    private final THashMap<NameAndDescriptor, TryCatchBlockCounter> nameAndDescriptorToTryCatchBlockCounter =
            new THashMap<>();
    private final FieldOwnerAndNameToIntMap fieldIdMap;

    public FactoryCollectionAll(FieldOwnerAndNameToIntMap fieldIdMap) {
        this.fieldIdMap = fieldIdMap;
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyze(NameAndDescriptor nameAndDescriptor) {
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = new TLinkedList<>();
        TryCatchBlockCounter tryCatchBlockCounter = new TryCatchBlockCounter();
        MethodEnterExitAnalyzeFactory methodEnterExitAnalyzeFactory = new MethodEnterExitAnalyzeFactory(tryCatchBlockCounter);
        nameAndDescriptorToTryCatchBlockCounter.put(nameAndDescriptor, tryCatchBlockCounter);
        result.add(wrap(methodEnterExitAnalyzeFactory));
        return result;
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(NameAndDescriptor nameAndDescriptor,
                                                                                          boolean isSynchronized,
                                                                                          int methodId) {
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = new TLinkedList<>();
        TryCatchBlockCounter tryCatchBlockCounter = nameAndDescriptorToTryCatchBlockCounter.get(nameAndDescriptor);
        result.add(wrap(new MethodEnterExitTransformFactory(tryCatchBlockCounter.tryCatchBlockCount())));
        result.add(wrap(AddFieldAccessCall.factory(fieldIdMap)));
        result.add(wrap(AddMonitorCall.factory()));
        return result;
    }
}
