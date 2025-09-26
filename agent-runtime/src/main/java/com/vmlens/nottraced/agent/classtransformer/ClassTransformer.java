package com.vmlens.nottraced.agent.classtransformer;

import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollection;
import com.vmlens.nottraced.agent.classtransformer.factorycollectionadapter.FactoryCollectionAdapterForAnalyze;
import com.vmlens.nottraced.agent.classtransformer.factorycollectionadapter.FactoryCollectionAdapterForTransform;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class ClassTransformer {

    private final FactoryCollection factoryCollection;
    private final MethodRepositoryForTransform methodCallIdMap;
    private final ClassVisitor previousClassVisitor;


    // Visible for Tests
    public ClassTransformer(FactoryCollection factoryCollection,
                            MethodRepositoryForTransform methodCallIdMap,
                            ClassVisitor previousClassVisitor) {
        this.factoryCollection = factoryCollection;
        this.methodCallIdMap = methodCallIdMap;
        this.previousClassVisitor = previousClassVisitor;
    }

    public  byte[] transform(byte[] classfileBuffer,
                             String name,
                             boolean isInRetransform) {

        String normalizedName = name.replace('.', '/');
        ClassVisitor classVisitorAnalyze = createAnalyze(normalizedName,isInRetransform);
        ClassReader readerForAnalyze = new ClassReader(classfileBuffer);
        readerForAnalyze.accept(classVisitorAnalyze, 0);

        ClassReader readerForTransform = new ClassReader(classfileBuffer);

        ClassWriter classWriter;
        if(factoryCollection.computeFrames()) {
            classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        } else {
            classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        }

        ClassVisitor classVisitorTransform = createTransform(classWriter, normalizedName,isInRetransform);
        readerForTransform.accept(classVisitorTransform, 0);
        return classWriter.toByteArray();
    }

    private ClassVisitor createAnalyze(String className,
                                       boolean isInRetransform) {
        return new ClassVisitorApplyMethodVisitor(previousClassVisitor,
                className,
                methodCallIdMap,
                new FactoryCollectionAdapterForAnalyze(factoryCollection),isInRetransform);
    }

    private ClassVisitor createTransform(ClassVisitor classWriter,
                                         String className,
                                         boolean isInRetransform) {
        return new ClassVisitorApplyMethodVisitor(classWriter,
                className,
                methodCallIdMap,
                new FactoryCollectionAdapterForTransform(factoryCollection),isInRetransform);
    }
}
