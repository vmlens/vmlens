package com.anarsoft.trace.agent.runtime.classarraytransformer;

import com.anarsoft.trace.agent.runtime.classarraytransformer.methodvisitor.MethodVisitorFactory;
import com.anarsoft.trace.agent.runtime.classarraytransformer.methodvisitormethodcall.TransformMethodMethodCallFactory;
import com.anarsoft.trace.agent.runtime.classarraytransformer.plan.MethodTransformPlanBuilder;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

public class ClassArrayTransformer {

    private final MethodCallIdMap methodCallIdMap;
    private final TransformMethodMethodCallFactory transformMethodMethodCallFactory;
    private final TLinkedList<TLinkableWrapper<MethodVisitorFactory>> methodVisitorFactoryList;

    public ClassArrayTransformer(MethodCallIdMap methodCallIdMap,
                                 TransformMethodMethodCallFactory transformMethodMethodCallFactory,
                                 TLinkedList<TLinkableWrapper<MethodVisitorFactory>> methodVisitorFactoryList) {
        this.methodCallIdMap = methodCallIdMap;
        this.transformMethodMethodCallFactory = transformMethodMethodCallFactory;
        this.methodVisitorFactoryList = methodVisitorFactoryList;
    }

    public static String normalize(String name) {
        return name.replace('.', '/');
    }

    public byte[] transform(byte[] classfileBuffer, String name) {

        new ClassReader(classfileBuffer);

        return null;
    }

    // Visible for test
    public void transform(byte[] classfileBuffer,
                          String name,
                          ClassVisitor classWriter) {
        THashMap<MethodId, MethodTransformPlanBuilder> methodIdToPlan =
                new THashMap<>();

        ClassVisitorAnalyze classVisitorAnalyze = new ClassVisitorAnalyze(methodIdToPlan, methodCallIdMap);
        ClassReader readerForAnalyze = new ClassReader(classfileBuffer);
        readerForAnalyze.accept(classVisitorAnalyze, 0);

        ClassReader readerForTransform = new ClassReader(classfileBuffer);
        ClassVisitorTransform classVisitorTransform = new ClassVisitorTransform(methodIdToPlan,
                methodCallIdMap,
                classWriter,
                normalize(name), transformMethodMethodCallFactory, methodVisitorFactoryList);
        readerForTransform.accept(classVisitorTransform, 0);
    }

}
