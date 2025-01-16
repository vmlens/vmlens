package com.anarsoft.trace.agent.runtime;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.ApplyClassTransformerCollection;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.ApplyClassTransformerCollectionFactory;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.ApplyClassTransformerElement;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerContext;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;


public class AgentClassFileTransformer implements ClassFileTransformer {

    public static final int ASM_API_VERSION = Opcodes.ASM7;
    private final ApplyClassTransformerCollection classArrayTransformerCollection;

    public AgentClassFileTransformer(ApplyClassTransformerCollectionFactory classArrayTransformerFactory) {
        super();
        this.classArrayTransformerCollection = classArrayTransformerFactory.create();
    }

    private static void logTransformedClass(String className, byte[] transformedArray) {
        if (transformedArray == null) {
            return;
        }
        try {
            String logDir = "";
            String fileName = className.substring(className.lastIndexOf("/") + 1);
            fileName = fileName.replace('$', 'I');
            OutputStream outTransformed = new FileOutputStream(logDir + fileName + "_trans.class");
            outTransformed.write(transformedArray);
            outTransformed.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }

    @Override
    public byte[] transform(ClassLoader loader, String name, Class<?> cl, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        try {
            if (loader != null && loader.equals(this.getClass().getClassLoader())) {
                return null;
            }
            if (name.startsWith("com/vmlens/test") || name.startsWith("com/vmlens/api")) {
                System.out.println();
            }
            ApplyClassTransformerElement transformer = classArrayTransformerCollection.get(name);
            if (transformer != null) {
                TransformerContext context = new TransformerContext(classfileBuffer, name);
                byte[] transformed = transformer.transform(context);
                logTransformedClass(name, transformed);
                return transformed;
            }
        } catch (Throwable e) {
            return null;
        }
        return null;
    }
}
