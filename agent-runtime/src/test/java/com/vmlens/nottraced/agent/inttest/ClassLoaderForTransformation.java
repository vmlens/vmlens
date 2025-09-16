package com.vmlens.nottraced.agent.inttest;

import com.vmlens.nottraced.agent.LoadClassArray;
import com.vmlens.nottraced.agent.applyclasstransformer.TransformerContext;
import com.vmlens.nottraced.agent.applyclasstransformer.TransformerStrategy;
import com.vmlens.nottraced.agent.classtransformer.RunTestClassTransformer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ClassLoaderForTransformation extends ClassLoader {

    private static final boolean TRACE_CLASSES = false;
    private final ClassLoader testClassLoader;

    public ClassLoaderForTransformation(ClassLoader testClassLoader) {
        super(null);
        this.testClassLoader = testClassLoader;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name.startsWith("java.util.concurrent.atomic")) {
            return testClassLoader.loadClass(name);
        }
        if (name.startsWith("com.vmlens.trace.agent")) {
            return testClassLoader.loadClass(name);
        }
        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] targetArray = new LoadClassArray().load(name);
            TransformerStrategy strategy =  RunTestClassTransformer.createFromLoaded().getStrategy(name);
            TransformerContext transformerContext = new TransformerContext(targetArray, name,false);

            if(strategy == null) {
                System.out.println("not transformed " + name);
                return defineClass(name, targetArray, 0, targetArray.length);
            }

            byte[] transformed = strategy.transform(transformerContext);
            if (transformed == null) {
                System.out.println("not transformed " + name);
                return defineClass(name, targetArray, 0, targetArray.length);
            }

            if (TRACE_CLASSES) {
                String fileName = name.substring(name.lastIndexOf("/") + 1);
                OutputStream outTransformed = new FileOutputStream(fileName + "_trans.class");
                outTransformed.write(transformed);
                outTransformed.close();
            }

            return defineClass(name, transformed, 0, transformed.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
