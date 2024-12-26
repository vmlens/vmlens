package com.anarsoft.trace.agent.runtime.classtransformerall;

import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitor.AddFieldAccessCall;
import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitor.AddMonitorCall;
import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitor.MethodVisitorFactory;
import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitor.MethodVisitorFactoryFactory;
import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitormethod.MethodFactoryFactory;
import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitormethodcall.MethodCallFactoryFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldOwnerAndNameToIntMap;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallIdMap;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import static com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper.wrap;

public class ClassTransformer {

    private final MethodCallIdMap methodCallIdMap;
    private final TLinkedList<TLinkableWrapper<MethodVisitorFactoryFactory>>
            factoryFactoryList;
    private final TLinkedList<TLinkableWrapper<MethodVisitorFactory>> transformFactoryList;
    private final ClassVisitor previousClassVisitor;

    // Visible for Tests
    public ClassTransformer(MethodCallIdMap methodCallIdMap,
                            TLinkedList<TLinkableWrapper<MethodVisitorFactoryFactory>> factoryFactoryList,
                            TLinkedList<TLinkableWrapper<MethodVisitorFactory>> transformFactoryList,
                            ClassVisitor previousClassVisitor) {
        this.methodCallIdMap = methodCallIdMap;
        this.factoryFactoryList = factoryFactoryList;
        this.transformFactoryList = transformFactoryList;
        this.previousClassVisitor = previousClassVisitor;
    }


    public static ClassTransformer createAll(MethodCallIdMap methodCallIdMap,
                                             FieldOwnerAndNameToIntMap fieldIdMap,
                                             // can be null
                                             ClassVisitor previousClassVisitor) {
        TLinkedList<TLinkableWrapper<MethodVisitorFactoryFactory>>
                methodVisitorFactoryAnalyzeList = new TLinkedList<>();
        // The methodCallFactory must be added last, since it transforms method calls
        methodVisitorFactoryAnalyzeList.add(wrap(new MethodFactoryFactory()));
        methodVisitorFactoryAnalyzeList.add(wrap(new MethodCallFactoryFactory()));


        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> methodVisitorFactoryList
                = new TLinkedList<>();
        methodVisitorFactoryList.add(wrap(AddFieldAccessCall.factory(fieldIdMap)));
        methodVisitorFactoryList.add(wrap(AddMonitorCall.factory()));

        return new ClassTransformer(methodCallIdMap, methodVisitorFactoryAnalyzeList,
                methodVisitorFactoryList, previousClassVisitor);
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

        ClassVisitorAnalyzeForTransform classVisitorAnalyze = new ClassVisitorAnalyzeForTransform(methodCallIdMap, name,
                factoryFactoryList, methodIdToFactory, previousClassVisitor);
        ClassReader readerForAnalyze = new ClassReader(classfileBuffer);
        readerForAnalyze.accept(classVisitorAnalyze, 0);

        ClassReader readerForTransform = new ClassReader(classfileBuffer);
        ClassVisitorTransform classVisitorTransform = new ClassVisitorTransform(methodCallIdMap,
                classWriter, name, transformFactoryList, methodIdToFactory);
        readerForTransform.accept(classVisitorTransform, 0);
    }

}
