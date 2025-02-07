package com.anarsoft.trace.agent.runtime;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.*;
import com.anarsoft.trace.agent.runtime.classtransformer.TransformerStrategyClassTransformerThread;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEventBuilder;
import com.vmlens.trace.agent.bootstrap.fieldidtostrategy.FieldRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodRepositorySingleton;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;


public class AgentClassFileTransformer implements ClassFileTransformer {

    public static final int ASM_API_VERSION = Opcodes.ASM7;
    private final ClassFilterAndTransformerStrategyCollection classArrayTransformerCollection;
    private final ClassFilterDeprecated classFilter;
    private final WriteClassDescriptionAndWarning writeClassDescriptionAndWarning;

    public AgentClassFileTransformer(ClassFilterAndTransformerStrategyCollectionFactory classArrayTransformerFactory,
                                     ClassFilterDeprecated classFilter,
                                     WriteClassDescriptionAndWarning writeClassDescriptionAndWarning) {
        super();
        this.classArrayTransformerCollection = classArrayTransformerFactory.create();
        this.classFilter = classFilter;
        this.writeClassDescriptionAndWarning = writeClassDescriptionAndWarning;
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
            if (!classFilter.take(name)) {
                return null;
            }
            TransformerContext context = new TransformerContext(classfileBuffer, name);

            TransformerStrategy transformer;
            if (name.equals("java/lang/Thread")) {
                transformer = new TransformerStrategyClassTransformerThread(MethodRepositorySingleton.INSTANCE,
                        FieldRepositorySingleton.INSTANCE, writeClassDescriptionAndWarning);
                byte[] transformed = transformer.transform(context);
                //  logTransformedClass(name, transformed);
                return transformed;
            }

            transformer = classArrayTransformerCollection.get(name);
            if (transformer != null) {
                byte[] transformed = transformer.transform(context);
                //  logTransformedClass(name, transformed);
                return transformed;
            }
        } catch (Throwable e) {
            InfoMessageEventBuilder infoMessageEventBuilder = new InfoMessageEventBuilder();
            infoMessageEventBuilder.add(e);
            writeClassDescriptionAndWarning.write(infoMessageEventBuilder.build());
            return null;
        }
        return null;
    }
}
