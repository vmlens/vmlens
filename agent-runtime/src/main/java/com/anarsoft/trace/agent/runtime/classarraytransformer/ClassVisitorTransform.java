package com.anarsoft.trace.agent.runtime.classarraytransformer;

import com.anarsoft.trace.agent.runtime.classarraytransformer.plan.MethodTransformPlanBuilder;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassVisitorTransform extends ClassVisitor {

    private final THashMap<MethodId, MethodTransformPlanBuilder> methodIdToPlan;
    private final MethodCallIdMap methodCallIdMap;

    public ClassVisitorTransform(THashMap<MethodId, MethodTransformPlanBuilder> methodIdToPlan,
                                 MethodCallIdMap methodCallIdMap,
                                 ClassVisitor classVisitor) {
        super(ASMConstants.ASM_API_VERSION, classVisitor);
        this.methodIdToPlan = methodIdToPlan;
        this.methodCallIdMap = methodCallIdMap;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor,
                                     String signature, String[] exceptions) {
        return TransformMethod.asMethodVisitor(methodCallIdMap, methodIdToPlan.get(new MethodId(access, name, descriptor,
                        signature, exceptions)).build(),
                super.visitMethod(access, name, descriptor, signature, exceptions));
    }


}
