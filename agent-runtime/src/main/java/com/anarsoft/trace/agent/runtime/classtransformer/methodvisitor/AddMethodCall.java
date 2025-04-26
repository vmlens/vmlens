package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor;

import com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory.MethodCallbackFactoryFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory.MethodCallbackFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import org.objectweb.asm.MethodVisitor;

import static com.anarsoft.trace.agent.runtime.classtransformer.ASMConstants.ASM_API_VERSION;


public class AddMethodCall extends MethodVisitor {

    private final MethodRepositoryForTransform methodCallIdMap;
    private final MethodCallbackFactory callbackCallFactory;
    private final int inMethodId;
    private int position;

    public AddMethodCall(MethodVisitor methodVisitor,
                         MethodRepositoryForTransform methodCallIdMap,
                         MethodCallbackFactory callbackCallFactory,
                         int inMethodId) {
        super(ASM_API_VERSION, methodVisitor);
        this.methodCallIdMap = methodCallIdMap;
        this.callbackCallFactory = callbackCallFactory;
        this.inMethodId = inMethodId;
    }

    public static MethodVisitorFactory factory(MethodRepositoryForTransform methodCallIdMap,
                                               MethodCallbackFactoryFactory factoryFactory) {
        return (factoryContext, previous) -> new AddMethodCall(previous,
                methodCallIdMap,
                factoryFactory.create(previous),
                factoryContext.methodId());
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        int methodCallId = methodCallIdMap.asInt(new MethodCallId(owner, name, descriptor));
        callbackCallFactory.beforeMethodCall(inMethodId, position, methodCallId);
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        callbackCallFactory.afterMethodCall(inMethodId, position, methodCallId);
        position++;
    }
}
