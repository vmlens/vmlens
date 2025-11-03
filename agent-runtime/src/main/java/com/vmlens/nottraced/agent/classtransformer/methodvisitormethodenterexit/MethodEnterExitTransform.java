package com.vmlens.nottraced.agent.classtransformer.methodvisitormethodenterexit;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactory;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.FactoryContext;
import org.objectweb.asm.MethodVisitor;

import static com.vmlens.nottraced.agent.classtransformer.ASMConstants.ASM_API_VERSION;
import static org.objectweb.asm.Opcodes.*;


public class MethodEnterExitTransform extends MethodVisitor {

    private final FactoryContext factoryContext;
    private final MethodCallbackFactory methodCallbackFactory;

    public MethodEnterExitTransform(MethodVisitor methodVisitor,
                                    FactoryContext factoryContext,
                                    MethodCallbackFactoryFactory factoryFactory) {
        super(ASM_API_VERSION, methodVisitor);
        this.factoryContext = factoryContext;
        this.methodCallbackFactory = factoryFactory.create(this.mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        factoryContext.methodEnterExitStrategy().createMethodEnter(mv,
                methodCallbackFactory,
                factoryContext.methodId(),
                factoryContext.className());
    }

    @Override
    public final void visitInsn(int inst) {
        switch (inst) {
            case RETURN:
            case IRETURN:
            case FRETURN:
            case LRETURN:
            case DRETURN:
            case ATHROW:
                createMethodExitCall();
                break;
            case ARETURN:
                createMethodExitWithObjectReturnCall();
                break;
            default:
                break;

        }
        super.visitInsn(inst);
    }

    private void createMethodExitWithObjectReturnCall()  {
        factoryContext.methodEnterExitStrategy().createMethodExitWithObjectReturn(mv,
                methodCallbackFactory,
                factoryContext.methodId(),
                factoryContext.className());
    }

    private void createMethodExitCall() {
        factoryContext.methodEnterExitStrategy().createMethodExit(mv,
                methodCallbackFactory,
                factoryContext.methodId(),
                factoryContext.className());
    }

}
