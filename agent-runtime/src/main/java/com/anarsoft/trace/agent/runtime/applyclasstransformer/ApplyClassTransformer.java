package com.anarsoft.trace.agent.runtime.applyclasstransformer;

public class ApplyClassTransformer {

    private final ApplyClassTransformerCollection classArrayTransformerCollection;

    public ApplyClassTransformer(ApplyClassTransformerCollectionFactory classArrayTransformerFactory) {
        super();
        this.classArrayTransformerCollection = classArrayTransformerFactory.create();
    }

    public byte[] transform(byte[] classfileBuffer, String name) {
        ApplyClassTransformerElement transformer = classArrayTransformerCollection.get(name);
        if (transformer != null) {
            TransformerContext context = new TransformerContext(classfileBuffer, name);
            return transformer.transform(context);
        }
        return null;
    }
}
