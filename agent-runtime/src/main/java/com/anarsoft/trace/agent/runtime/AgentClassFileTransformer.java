package com.anarsoft.trace.agent.runtime;

import com.anarsoft.trace.agent.runtime.applyclassarraytransformer.ApplyClassArrayTransformer;
import com.anarsoft.trace.agent.runtime.applyclassarraytransformer.ApplyClassArrayTransformerCollection;
import com.anarsoft.trace.agent.runtime.applyclassarraytransformer.ApplyClassArrayTransformerFactory;
import com.anarsoft.trace.agent.runtime.classarraytransformer.TransformerContext;
import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethods;
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
    private final HasGeneratedMethods hasGeneratedMethods;
    private final WriteClassDescription writeClassDescription;
    private final ApplyClassArrayTransformerCollection classArrayTransformerCollection;

    public AgentClassFileTransformer(WriteClassDescription writeClassDescription,
                                     HasGeneratedMethods hasGeneratedMethods,
                                     ApplyClassArrayTransformerFactory classArrayTransformerFactory) {
        super();
        this.writeClassDescription = writeClassDescription;
        this.hasGeneratedMethods = hasGeneratedMethods;
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

            if (name == null) {
                return null;
            }
            if (name.indexOf('[') > -1) {
                return null;
            }
            if (name.startsWith("com/vmlens/shaded")) {
                return null;
            }


            ApplyClassArrayTransformer transformer = classArrayTransformerCollection.get(name);
            if (transformer != null) {
                    TransformerContext context = new TransformerContext(classfileBuffer, name,
                            writeClassDescription, hasGeneratedMethods);
                    byte[] result = transformer.transform(context);
                    //logTransformedClass(name, result);
                    return result;
            }
            return null;
        } catch (Throwable e) {
            AgentLogCallback.logException(e);
            return null;
        }
    }
}
