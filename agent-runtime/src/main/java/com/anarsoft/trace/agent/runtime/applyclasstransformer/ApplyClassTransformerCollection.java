package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.runtime.TLinkableWrapper;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;

public class ApplyClassTransformerCollection {

    private final TLinkedList<TLinkableWrapper<ApplyClassTransformerElement>> classArrayTransformerList;

    public ApplyClassTransformerCollection(TLinkedList<TLinkableWrapper<ApplyClassTransformerElement>> classArrayTransformerList) {
        this.classArrayTransformerList = classArrayTransformerList;
    }

    /**
     * @param name
     * @return null when no ClassArrayTransformer could be found
     */
    public ApplyClassTransformerElement get(String name) {
        if (name == null) {
            return null;
        }
        if (name.indexOf('[') > -1) {
            return null;
        }
        if (name.startsWith("com/vmlens/shaded")) {
            return null;
        }

        for (TLinkableWrapper<ApplyClassTransformerElement> transformerWrapper : classArrayTransformerList) {
            ApplyClassTransformerElement transformer = transformerWrapper.getElement();
            if (transformer.appliesTo(name)) {
                return transformer;
            }
        }
        return null;
    }
}
