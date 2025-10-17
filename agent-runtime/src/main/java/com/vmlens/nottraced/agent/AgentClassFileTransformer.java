package com.vmlens.nottraced.agent;

import com.vmlens.nottraced.agent.applyclasstransformer.ClassFilterAndTransformerStrategyCollection;
import com.vmlens.nottraced.agent.applyclasstransformer.ClassFilterAndTransformerStrategyCollectionFactory;
import com.vmlens.nottraced.agent.applyclasstransformer.TransformerContext;
import com.vmlens.nottraced.agent.applyclasstransformer.TransformerStrategy;
import com.vmlens.nottraced.agent.write.WriteClassDescriptionAndWarning;
import com.vmlens.transformed.agent.bootstrap.event.warning.InfoMessageEventBuilder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import static com.vmlens.transformed.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.*;


public class AgentClassFileTransformer implements ClassFileTransformer {

    private final ClassFilterAndTransformerStrategyCollection classArrayTransformerCollection;
    private final WriteClassDescriptionAndWarning writeClassDescriptionAndWarning;
    private final ClassFilterFromFile classFilterFromFile;
    private volatile boolean isInReTransform = true;

    public AgentClassFileTransformer(ClassFilterAndTransformerStrategyCollectionFactory classArrayTransformerFactory,
                                     WriteClassDescriptionAndWarning writeClassDescriptionAndWarning,
                                     ClassFilterFromFile classFilterFromFile) {
        super();
        this.classArrayTransformerCollection = classArrayTransformerFactory.create();
        this.writeClassDescriptionAndWarning = writeClassDescriptionAndWarning;
        this.classFilterFromFile = classFilterFromFile;
    }

    private static void logClass(String className, byte[] transformedArray) {
        if (transformedArray == null) {
            return;
        }
        try {
            String logDir = "";
            String fileName = className.substring(className.lastIndexOf("/") + 1);
            fileName = fileName.replace('$', 'I');
            OutputStream outTransformed = new FileOutputStream(logDir + fileName + ".class");
            outTransformed.write(transformedArray);
            outTransformed.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }

    @Override
    public byte[] transform(ClassLoader loader, String name, Class<?> cl, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        incrementInsideVMLens();
        try {
              if (loader != null && loader.equals(this.getClass().getClassLoader())) {
                return null;
            }

            if(classFilterFromFile.filter(name)) {
                return null;
            }

            /*
             * for junit6 and jdk 25 we have problems transforming proxies
             */
            if(name.contains("$Proxy")) {
                return null;
            }

            TransformerContext context = new TransformerContext(classfileBuffer, name, isInReTransform);
            TransformerStrategy transformer = classArrayTransformerCollection.get(name);
            if (transformer != null) {
                byte[] transformed = transformer.transform(context);
          /*   if(name.startsWith("org/junit/platform/launcher/jfr/$Pr")) {
                    logClass(name + "_trans", transformed);
                 logClass(name , classfileBuffer);
                }
            */
                return transformed;
            }
        } catch (Throwable e) {
            InfoMessageEventBuilder infoMessageEventBuilder = new InfoMessageEventBuilder();
            infoMessageEventBuilder.add(e);
            writeClassDescriptionAndWarning.write(infoMessageEventBuilder.build());
            return null;
        }
        finally {
            decrementInsideVMLens();
        }
        return null;
    }

    public void isInTransform() {
        this.isInReTransform = false;
    }

}
