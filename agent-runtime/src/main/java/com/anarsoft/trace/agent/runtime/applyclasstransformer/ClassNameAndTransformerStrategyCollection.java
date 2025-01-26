package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class ClassNameAndTransformerStrategyCollection {

    private final TLinkedList<TLinkableWrapper<ClassNameAndTransformerStrategy>> classArrayTransformerList;

    private static String normalize(String name) {
        return name.replace('.', '/');
    }

    public ClassNameAndTransformerStrategyCollection(TLinkedList<TLinkableWrapper<ClassNameAndTransformerStrategy>> classArrayTransformerList) {
        this.classArrayTransformerList = classArrayTransformerList;
    }

    /**
     * @param notNormalizedName
     * @return null when no ClassArrayTransformer could be found
     */
    public ClassNameAndTransformerStrategy get(String notNormalizedName) {
        if (notNormalizedName == null) {
            return null;
        }

        String name = normalize(notNormalizedName);

        if (name.indexOf('[') > -1) {
            return null;
        }
        if (name.startsWith("com/vmlens/shaded")) {
            return null;
        }
        if (name.startsWith("com/vmlens/trace/agent/bootstrap")) {
            return null;
        }
        if (name.startsWith("com/anarsoft/trace/agent")) {
            return null;
        }

        for (TLinkableWrapper<ClassNameAndTransformerStrategy> transformerWrapper : classArrayTransformerList) {
            ClassNameAndTransformerStrategy transformer = transformerWrapper.element();
            if (transformer.appliesTo(name)) {
                return transformer;
            }
        }
        return null;
    }
}
