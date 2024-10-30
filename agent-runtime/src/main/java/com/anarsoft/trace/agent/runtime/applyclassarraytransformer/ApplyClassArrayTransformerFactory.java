package com.anarsoft.trace.agent.runtime.applyclassarraytransformer;

import com.anarsoft.trace.agent.runtime.TLinkableWrapper;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;


public class ApplyClassArrayTransformerFactory {

    private final TransformerTypes transformerTypes;

    public ApplyClassArrayTransformerFactory(TransformerTypes transformerTypes) {
        this.transformerTypes = transformerTypes;
    }

    public static ApplyClassArrayTransformerFactory instrument() {
        return new ApplyClassArrayTransformerFactory(new TransformerTypes());
    }

    public static ApplyClassArrayTransformerFactory retransform() {
        return new ApplyClassArrayTransformerFactory(new TransformerTypes());
    }

    private void appendVMLensApi(TLinkedList<TLinkableWrapper<ApplyClassArrayTransformer>> result) {
        result.add(new TLinkableWrapper(new ApplyClassArrayTransformer("com/vmlens/api/AllInterleavings",
                transformerTypes.vmlensApi())));
    }

    private void appendThread(TLinkedList<TLinkableWrapper<ApplyClassArrayTransformer>> result) {
        result.add(new TLinkableWrapper(new ApplyClassArrayTransformer("java/lang/Thread",
                transformerTypes.thread())));
    }

    private void appendNormal(TLinkedList<TLinkableWrapper<ApplyClassArrayTransformer>> result) {
        result.add(new TLinkableWrapper(new ApplyClassArrayTransformer("com/vmlens/test",
                transformerTypes.normal())));
    }

    public ApplyClassArrayTransformerCollection create() {
        TLinkedList<TLinkableWrapper<ApplyClassArrayTransformer>> result = new TLinkedList<>();

        appendVMLensApi(result);
        appendThread(result);
        appendNormal(result);

        return new ApplyClassArrayTransformerCollection(result);
    }
}
