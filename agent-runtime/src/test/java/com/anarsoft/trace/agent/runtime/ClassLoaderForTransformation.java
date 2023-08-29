package com.anarsoft.trace.agent.runtime;

import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethodsSetBased;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionNormal;
import org.apache.commons.io.IOUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.*;
import java.lang.instrument.IllegalClassFormatException;

public class ClassLoaderForTransformation extends ClassLoader {

    private final ClassLoader testClassLoader;


    public ClassLoaderForTransformation(ClassLoader testClassLoader) {
        super(null);
        this.testClassLoader = testClassLoader;
    }

    AgentClassFileTransformer createTransformer() {
        return new AgentClassFileTransformer(false,
                new WriteClassDescriptionNormal(), true, new HasGeneratedMethodsSetBased());
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name.startsWith("com.vmlens.trace.agent")) {
            return testClassLoader.loadClass(name);
        }
        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {


            String resourceName = name.replace('.', '/') + ".class";
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream(resourceName);

            byte[] targetArray = IOUtils.toByteArray(stream);
            AgentClassFileTransformer transformer = createTransformer();

            byte[] transformed = transformer.transform(null, name.replace('.', '/'), null, null, targetArray);
            if (transformed == null) {
                System.out.println("not transformed " + name);
                return defineClass(name, targetArray, 0, targetArray.length);
            }

            ClassReader classReader = new ClassReader(transformed);
            ClassVisitor visitor = new TraceClassVisitor(new PrintWriter(System.out));
            classReader.accept(visitor, ClassReader.EXPAND_FRAMES);

            String fileName = name.substring(name.lastIndexOf("/") + 1);
            OutputStream outTransformed = new FileOutputStream(fileName + "_trans.class");
            outTransformed.write(transformed);
            outTransformed.close();

            return defineClass(name, transformed, 0, transformed.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalClassFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
