package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.runtime.TLinkableWrapper;
import com.anarsoft.trace.agent.runtime.classtransformer.TransformerStrategyAll;
import com.anarsoft.trace.agent.runtime.classtransformervmlensapi.ClassTransformerVmlensApi;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldOwnerAndNameToIntMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;

import static com.anarsoft.trace.agent.runtime.TLinkableWrapper.wrap;


public class ApplyClassTransformerCollectionFactory {


    private final MethodCallIdMap methodCallIdMap;
    private final FieldOwnerAndNameToIntMap fieldIdMap;
    private final TLinkedList<TLinkableWrapper<ApplyClassTransformerElement>> result = new TLinkedList<>();

    public ApplyClassTransformerCollectionFactory(MethodCallIdMap methodCallIdMap, FieldOwnerAndNameToIntMap fieldIdMap) {
        this.methodCallIdMap = methodCallIdMap;
        this.fieldIdMap = fieldIdMap;
    }

    // Visible for Test
    // From specific to generic
    void add(String name, TransformerStrategy transformerStrategy) {
        result.add(wrap(new ApplyClassTransformerElement(name, transformerStrategy)));
    }

    // Visible for Test
    ApplyClassTransformerCollection createInternal() {
        return new ApplyClassTransformerCollection(result);
    }

    public ApplyClassTransformerCollection create() {


        add("com/vmlens/api/AllInterleavingsBuilder", new TransformerStrategyFilter());
        add("com/vmlens/api/AllInterleavings", new ClassTransformerVmlensApi());
        add("", new TransformerStrategyAll(methodCallIdMap, fieldIdMap));

        return createInternal();
    }

}
