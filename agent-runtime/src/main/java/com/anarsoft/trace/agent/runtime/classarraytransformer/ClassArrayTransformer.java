package com.anarsoft.trace.agent.runtime.classarraytransformer;

import com.anarsoft.trace.agent.runtime.classarraytransformer.plan.MethodTransformPlanBuilder;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

public class ClassArrayTransformer {

    private final MethodCallIdMap methodCallIdMap;

    public ClassArrayTransformer(MethodCallIdMap methodCallIdMap) {
        this.methodCallIdMap = methodCallIdMap;
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
        ClassVisitorTransform classVisitorTransform = new ClassVisitorTransform(methodIdToPlan, methodCallIdMap, classWriter);
        readerForTransform.accept(classVisitorTransform, 0);
    }


}
