package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodcall.TransformMethodMethodCallFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.plan.MethodTransformPlanBuilder;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassVisitorTransform extends ClassVisitor {

    private final THashMap<MethodId, MethodTransformPlanBuilder> methodIdToPlan;
    private final MethodCallIdMap methodCallIdMap;
    private final String className;
    private final TransformMethodMethodCallFactory transformMethodMethodCallFactory;
    private final TLinkedList<TLinkableWrapper<MethodVisitorFactory>> methodVisitorFactoryList;

    public ClassVisitorTransform(THashMap<MethodId, MethodTransformPlanBuilder> methodIdToPlan,
                                 MethodCallIdMap methodCallIdMap,
                                 ClassVisitor classVisitor, String className,
                                 TransformMethodMethodCallFactory transformMethodMethodCallFactory,
                                 TLinkedList<TLinkableWrapper<MethodVisitorFactory>> methodVisitorFactoryList) {
        super(ASMConstants.ASM_API_VERSION, classVisitor);
        this.methodIdToPlan = methodIdToPlan;
        this.methodCallIdMap = methodCallIdMap;
        this.className = className;
        this.transformMethodMethodCallFactory = transformMethodMethodCallFactory;
        this.methodVisitorFactoryList = methodVisitorFactoryList;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor,
                                     String signature, String[] exceptions) {

        int inMethodId = methodCallIdMap.asInt(new MethodCallId(className, name, descriptor));

        MethodVisitor previous = super.visitMethod(access, name, descriptor, signature, exceptions);
        for (TLinkableWrapper<MethodVisitorFactory> element : methodVisitorFactoryList) {
            previous = element.element.create(inMethodId, previous);
        }

        return transformMethodMethodCallFactory.create(inMethodId, methodIdToPlan.get(new MethodId(access, name, descriptor,
                signature, exceptions)).build(), previous);

    }
}
