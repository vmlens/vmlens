package com.vmlens.nottraced.agent.classtransformer.methodvisitorthreadpool;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.ThreadPoolCallbackFactory;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static com.vmlens.nottraced.agent.classtransformer.ASMConstants.ASM_API_VERSION;
import static org.objectweb.asm.Opcodes.*;

public class ThreadPoolJoin  extends MethodVisitor  {

    public static MethodVisitorFactory factory() {
        return (factoryContext, previous) -> new ThreadPoolJoin(previous,
                factoryContext.methodId());
    }

    private final ThreadPoolCallbackFactory threadPoolCallbackFactory;

    public ThreadPoolJoin(MethodVisitor methodVisitor, int methodId) {
        super(ASM_API_VERSION, methodVisitor);
        this.threadPoolCallbackFactory = new ThreadPoolCallbackFactory(methodVisitor,methodId);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        super.visitVarInsn(ALOAD, 0);
        threadPoolCallbackFactory.threadJoin();

    }

}
