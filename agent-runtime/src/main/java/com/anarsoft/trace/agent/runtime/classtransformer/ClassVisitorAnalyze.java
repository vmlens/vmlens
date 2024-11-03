package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodcall.AnalyzeMethodMethodCall;
import com.anarsoft.trace.agent.runtime.classtransformer.plan.MethodTransformPlanBuilder;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassVisitorAnalyze extends ClassVisitor {

    private final THashMap<MethodId, MethodTransformPlanBuilder> methodIdToPlan;
    private final MethodCallIdMap methodCallIdMap;

    public ClassVisitorAnalyze(THashMap<MethodId, MethodTransformPlanBuilder> methodIdToPlan,
                               MethodCallIdMap methodCallIdMap) {
        super(ASMConstants.ASM_API_VERSION);
        this.methodIdToPlan = methodIdToPlan;
        this.methodCallIdMap = methodCallIdMap;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor,
                                     String signature, String[] exceptions) {
        MethodTransformPlanBuilder methodTransformPlan = new MethodTransformPlanBuilder();
        MethodId methodId = new MethodId(access, name, descriptor, signature, exceptions);
        methodIdToPlan.put(methodId, methodTransformPlan);
        return AnalyzeMethodMethodCall.asMethodVisitor(methodCallIdMap, methodTransformPlan);
    }
}
