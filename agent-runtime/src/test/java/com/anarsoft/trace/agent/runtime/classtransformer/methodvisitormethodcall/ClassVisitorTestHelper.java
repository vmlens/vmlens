package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodcall;

import com.anarsoft.trace.agent.runtime.classtransformer.ASMConstants;
import com.anarsoft.trace.agent.runtime.classtransformer.plan.MethodTransformPlanBuilder;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodCallIdMap;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.Map;

import static org.mockito.Mockito.mock;

public class ClassVisitorTestHelper extends ClassVisitor {

    private final Map<String, MethodTransformPlanBuilder> methodNameToBuilder;


    public ClassVisitorTestHelper(Map<String, MethodTransformPlanBuilder> methodNameToBuilder) {
        super(ASMConstants.ASM_API_VERSION);
        this.methodNameToBuilder = methodNameToBuilder;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name,
                                     String descriptor, String signature,
                                     String[] exceptions) {
        MethodTransformPlanBuilder methodTransformPlanBuilder = new MethodTransformPlanBuilder();
        methodNameToBuilder.put(name, methodTransformPlanBuilder);
        return AnalyzeMethodMethodCall.asMethodVisitor(null, mock(MethodCallIdMap.class),
                methodTransformPlanBuilder);
    }
}
