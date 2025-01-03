package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;

public class ApplyClassTransformer {

    private final WriteClassDescriptionAndWarning writeClassDescription;
    private final ApplyClassTransformerCollection classArrayTransformerCollection;

    public ApplyClassTransformer(WriteClassDescriptionAndWarning writeClassDescription,
                                 ApplyClassTransformerCollectionFactory classArrayTransformerFactory) {
        super();
        this.writeClassDescription = writeClassDescription;
        this.classArrayTransformerCollection = classArrayTransformerFactory.create();
    }

    public byte[] transform(byte[] classfileBuffer, String name) {
        ApplyClassTransformerElement transformer = classArrayTransformerCollection.get(name);
        if (transformer != null) {
            TransformerContext context = new TransformerContext(classfileBuffer, name, writeClassDescription);
            return transformer.transform(context);
        }
        return null;
    }
}
