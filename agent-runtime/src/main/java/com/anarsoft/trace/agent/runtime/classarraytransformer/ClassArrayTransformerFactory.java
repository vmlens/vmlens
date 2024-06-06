package com.anarsoft.trace.agent.runtime.classarraytransformer;

import com.anarsoft.trace.agent.runtime.TLinkableWrapper;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;


public class ClassArrayTransformerFactory {
    private void appendVMLensApi(TLinkedList<TLinkableWrapper<ClassArrayTransformer>> result) {
        result.add(new TLinkableWrapper(new ClassArrayTransformer("com/vmlens/api/AllInterleavings",
                new TransformerStrategyVMLensApi())));
    }

    private void appendThread(TLinkedList<TLinkableWrapper<ClassArrayTransformer>> result) {
        result.add(new TLinkableWrapper(new ClassArrayTransformer("java/lang/Thread",
                new TransformerStrategyThread())));
    }

    private void appendNormal(TLinkedList<TLinkableWrapper<ClassArrayTransformer>> result) {
        result.add(new TLinkableWrapper(new ClassArrayTransformer("com/vmlens/test",
                new TransformerStrategyNormal())));
    }

    public TLinkedList create() {
        TLinkedList<TLinkableWrapper<ClassArrayTransformer>> result = new TLinkedList<>();

        appendVMLensApi(result);
        appendThread(result);
        appendNormal(result);

        return result;
    }
}
