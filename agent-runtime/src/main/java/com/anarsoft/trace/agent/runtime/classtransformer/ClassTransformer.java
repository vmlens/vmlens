package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.classtransformer.methodfilter.MethodFilter;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactoryFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorForTransformFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class ClassTransformer {

    private final MethodCallIdMap methodCallIdMap;
    private final TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactoryFactory>>
            factoryFactoryList;
    private final TLinkedList<TLinkableWrapper<MethodVisitorForTransformFactory>> transformFactoryList;
    private final ClassVisitor previousClassVisitor;
    private final MethodFilter methodFilter;

    // Visible for Tests
    public ClassTransformer(MethodCallIdMap methodCallIdMap,
                            TLinkedList<TLinkableWrapper<MethodVisitorAnalyzeAndTransformFactoryFactory>> factoryFactoryList,
                            TLinkedList<TLinkableWrapper<MethodVisitorForTransformFactory>> transformFactoryList,
                            ClassVisitor previousClassVisitor, MethodFilter methodFilter) {
        this.methodCallIdMap = methodCallIdMap;
        this.factoryFactoryList = factoryFactoryList;
        this.transformFactoryList = transformFactoryList;
        this.previousClassVisitor = previousClassVisitor;
        this.methodFilter = methodFilter;
    }

    public byte[] transform(byte[] classfileBuffer, String name) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        transform(classfileBuffer, name, classWriter);

        return classWriter.toByteArray();
    }

    // Visible for test
    public void transform(byte[] classfileBuffer,
                          String name,
                          ClassVisitor classWriter) {

        MethodVisitorAnalyzeAndTransformFactoryMap
                methodIdToFactory = new MethodVisitorAnalyzeAndTransformFactoryMap();

        ClassVisitor classVisitorAnalyze = createAnalyze(name, methodIdToFactory);
        ClassReader readerForAnalyze = new ClassReader(classfileBuffer);
        readerForAnalyze.accept(classVisitorAnalyze, 0);

        ClassReader readerForTransform = new ClassReader(classfileBuffer);
        ClassVisitor classVisitorTransform = createTransform(classWriter, name, methodIdToFactory);
        readerForTransform.accept(classVisitorTransform, 0);
    }

    private ClassVisitor createAnalyze(String className,
                                       MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory) {
        return ClassVisitorApplyMethodVisitor
                .createAnalyze(previousClassVisitor, className, methodIdToFactory, factoryFactoryList, methodCallIdMap, methodFilter);
    }


    private ClassVisitor createTransform(ClassVisitor classWriter,
                                         String className,
                                         MethodVisitorAnalyzeAndTransformFactoryMap methodIdToFactory) {
        return ClassVisitorApplyMethodVisitor.createTransform(classWriter,
                className, methodIdToFactory, methodCallIdMap, transformFactoryList, methodFilter);
    }
}
