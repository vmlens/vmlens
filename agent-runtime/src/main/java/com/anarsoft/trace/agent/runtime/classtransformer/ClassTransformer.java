package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.AddFieldAccessCall;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.AddMonitorCall;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorFactoryFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethod.MethodFactoryFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodcall.MethodCallAnalyzeAndTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodcall.MethodCallFactoryFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.repository.FieldIdMap;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class ClassTransformer {

    private final MethodCallIdMap methodCallIdMap;
    private final TLinkedList<TLinkableWrapper<MethodVisitorFactoryFactory>>
            factoryFactoryList;
    private final TLinkedList<TLinkableWrapper<MethodVisitorFactory>> transformFactoryList;

    // Visible for Tests
    public ClassTransformer(MethodCallIdMap methodCallIdMap,
                            TLinkedList<TLinkableWrapper<MethodVisitorFactoryFactory>> factoryFactoryList,
                            TLinkedList<TLinkableWrapper<MethodVisitorFactory>> transformFactoryList) {
        this.methodCallIdMap = methodCallIdMap;
        this.factoryFactoryList = factoryFactoryList;
        this.transformFactoryList = transformFactoryList;
    }


    public static ClassTransformer createAll(MethodCallIdMap methodCallIdMap, FieldIdMap fieldIdMap) {
        MethodCallAnalyzeAndTransformFactory methodCallFactory = new MethodCallAnalyzeAndTransformFactory(methodCallIdMap);

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
                methodVisitorFactoryList);
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

        ClassVisitorAnalyze classVisitorAnalyze = new ClassVisitorAnalyze(methodCallIdMap, name,
                factoryFactoryList, methodIdToFactory);
        ClassReader readerForAnalyze = new ClassReader(classfileBuffer);
        readerForAnalyze.accept(classVisitorAnalyze, 0);

        ClassReader readerForTransform = new ClassReader(classfileBuffer);
        ClassVisitorTransform classVisitorTransform = new ClassVisitorTransform(methodCallIdMap,
                classWriter, name, transformFactoryList, methodIdToFactory);
        readerForTransform.accept(classVisitorTransform, 0);
    }

}
