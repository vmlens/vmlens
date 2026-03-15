package com.vmlens.nottraced.agent.classtransformer.methodvisitor;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactory;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.FactoryContext;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static com.vmlens.nottraced.agent.classtransformer.ASMConstants.ASM_API_VERSION;

public class AddTryFinallyBlock extends MethodVisitor  {

    private final FactoryContext factoryContext;
    private final MethodCallbackFactory methodCallbackFactory;
    private final  Label startTryBlock = new Label();


    public AddTryFinallyBlock(MethodVisitor methodVisitor,
                                    FactoryContext factoryContext,
                                    MethodCallbackFactoryFactory factoryFactory) {
        super(ASM_API_VERSION, methodVisitor);
        this.factoryContext = factoryContext;
        this.methodCallbackFactory = factoryFactory.create(this.mv);
    }

    public static MethodVisitorFactory factory(MethodCallbackFactoryFactory factoryFactory) {
        return (factoryContext, previous) ->
                new AddTryFinallyBlock(previous,
                        factoryContext,
                        factoryFactory);
    }

    @Override
    public void visitCode() {
        super.visitLabel(startTryBlock);
        super.visitCode();
    }


    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        Label endTryBlock = new Label();
        Label startFinally = new Label();
        /*
         The JVM Checks the try catch block in the table from first to last
         So the finally block must come last to not swallow other catch blocks
         */
        super.visitTryCatchBlock(startTryBlock, endTryBlock, startFinally, null);
        super.visitLabel(endTryBlock);
        super.visitLabel(startFinally);
        if(factoryContext.needsVisitFrames()) {
            /*
            When a TRYCATCHBLOCK catches an exception and jumps to the handler (in your case, L1),
            the JVM clears the operand stack and pushes exactly one object: the Throwable that was thrown.
             */
            if(!factoryContext.isStatic()) {
                super.visitFrame(Opcodes.F_NEW, 1,  new Object[]{factoryContext.className()}, 1, new Object[]{"java/lang/Throwable"});
            } else {
                super.visitFrame(Opcodes.F_NEW, 0,  null, 1, new Object[]{"java/lang/Throwable"});
            }
        }
        super.visitInsn(Opcodes.ATHROW);

        super.visitMaxs(maxStack , maxLocals);
    }

}
