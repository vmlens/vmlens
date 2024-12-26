package com.anarsoft.trace.agent.runtime;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.ApplyClassTransformerCollection;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.ApplyClassTransformerCollectionFactory;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescription;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.AgentLogCallback;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;


public class AgentClassFileTransformer implements ClassFileTransformer {

    public static final int ASM_API_VERSION = Opcodes.ASM7;

    private final WriteClassDescription writeClassDescription;
    private final ApplyClassTransformerCollection classArrayTransformerCollection;

    public AgentClassFileTransformer(WriteClassDescription writeClassDescription,
                                     ApplyClassTransformerCollectionFactory classArrayTransformerFactory) {
        super();
        this.writeClassDescription = writeClassDescription;
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


        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            return null;
        }

        return null;
    }
}
