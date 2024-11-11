package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.runtime.write.WriteClassDescription;

public class ApplyClassTransformer {

    private final WriteClassDescription writeClassDescription;
    private final ApplyClassTransformerCollection classArrayTransformerCollection;

    public ApplyClassTransformer(WriteClassDescription writeClassDescription,
                                 ApplyClassTransformerCollectionFactory classArrayTransformerFactory) {
        super();
        this.writeClassDescription = writeClassDescription;
        this.classArrayTransformerCollection = classArrayTransformerFactory.create();
    }

    public static String normalize(String name) {
        return name.replace('.', '/');
    }

    public byte[] transform(byte[] classfileBuffer, String notNormalizedName) {

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


        ApplyClassTransformerElement transformer = classArrayTransformerCollection.get(name);
        if (transformer != null) {
            TransformerContext context = new TransformerContext(classfileBuffer, name, writeClassDescription);
            return transformer.transform(context);
        }
        return null;

    }

}
