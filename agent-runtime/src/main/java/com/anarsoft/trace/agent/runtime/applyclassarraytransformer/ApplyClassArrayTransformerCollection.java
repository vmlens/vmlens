package com.anarsoft.trace.agent.runtime.applyclassarraytransformer;

import com.anarsoft.trace.agent.runtime.TLinkableWrapper;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;

public class ApplyClassArrayTransformerCollection {

    private final TLinkedList<TLinkableWrapper<ApplyClassArrayTransformer>> classArrayTransformerList;

    public ApplyClassArrayTransformerCollection(TLinkedList<TLinkableWrapper<ApplyClassArrayTransformer>> classArrayTransformerList) {
        this.classArrayTransformerList = classArrayTransformerList;
    }

    /**
     * @param name
     * @return null when no ClassArrayTransformer could be found
     */
    public ApplyClassArrayTransformer get(String name) {
        if (name == null) {
            return null;
        }
        if (name.indexOf('[') > -1) {
            return null;
        }
        if (name.startsWith("com/vmlens/shaded")) {
            return null;
        }

        for (TLinkableWrapper<ApplyClassArrayTransformer> transformerWrapper : classArrayTransformerList) {
            ApplyClassArrayTransformer transformer = transformerWrapper.getElement();
            if (transformer.appliesTo(name)) {
                return transformer;
            }
        }
        return null;
    }
}
