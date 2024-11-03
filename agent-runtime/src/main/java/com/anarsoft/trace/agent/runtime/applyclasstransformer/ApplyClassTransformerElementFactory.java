package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.runtime.TLinkableWrapper;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;


public class ApplyClassTransformerElementFactory {

    private final TransformerTypes transformerTypes;

    public ApplyClassTransformerElementFactory(TransformerTypes transformerTypes) {
        this.transformerTypes = transformerTypes;
    }

    public static ApplyClassTransformerElementFactory instrument() {
        return new ApplyClassTransformerElementFactory(new TransformerTypes());
    }

    public static ApplyClassTransformerElementFactory retransform() {
        return new ApplyClassTransformerElementFactory(new TransformerTypes());
    }

    private void appendVMLensApi(TLinkedList<TLinkableWrapper<ApplyClassTransformerElement>> result) {
        result.add(new TLinkableWrapper(new ApplyClassTransformerElement("com/vmlens/api/AllInterleavings",
                transformerTypes.vmlensApi())));
    }

    private void appendThread(TLinkedList<TLinkableWrapper<ApplyClassTransformerElement>> result) {
        result.add(new TLinkableWrapper(new ApplyClassTransformerElement("java/lang/Thread",
                transformerTypes.java())));
    }

    private void appendNormal(TLinkedList<TLinkableWrapper<ApplyClassTransformerElement>> result) {
        result.add(new TLinkableWrapper(new ApplyClassTransformerElement("com/vmlens/test",
                transformerTypes.normal())));
    }

    public ApplyClassTransformerCollection create() {
        TLinkedList<TLinkableWrapper<ApplyClassTransformerElement>> result = new TLinkedList<>();

        appendVMLensApi(result);
        // appendThread(result);
        appendNormal(result);

        return new ApplyClassTransformerCollection(result);
    }
}
