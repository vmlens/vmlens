package com.anarsoft.trace.agent.runtime;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.ApplyClassTransformer;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.ApplyClassTransformerCollectionFactory;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarningDuringStartup;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepository;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepository;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import static org.mockito.Mockito.mock;

public class ClassLoaderForTransformation extends ClassLoader {

    private static final boolean TRACE_CLASSES = false;
    private final ClassLoader testClassLoader;


    public ClassLoaderForTransformation(ClassLoader testClassLoader) {
        super(null);
        this.testClassLoader = testClassLoader;
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
            byte[] targetArray = new LoadClassArray().load(name);
            WriteClassDescriptionAndWarning writeClassDescription = mock(WriteClassDescriptionAndWarning.class);
            ApplyClassTransformerCollectionFactory factory = new ApplyClassTransformerCollectionFactory(new MethodRepository(),
                    new FieldRepository(), writeClassDescription);
            ApplyClassTransformer applyClassTransformer = new ApplyClassTransformer(
                    new WriteClassDescriptionAndWarningDuringStartup(new TLinkedList<>(), new TLinkedList<>()),
                    factory);

            byte[] transformed = applyClassTransformer.transform(targetArray,
                    name.replace('.', '/'));
            if (transformed == null) {
                System.out.println("not transformed " + name);
                return defineClass(name, targetArray, 0, targetArray.length);
            }

            ClassReader classReader = new ClassReader(transformed);
            ClassVisitor visitor = new TraceClassVisitor(new PrintWriter(System.out));
            classReader.accept(visitor, ClassReader.EXPAND_FRAMES);

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
