package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor;

import com.anarsoft.trace.agent.runtime.classtransformer.ASMConstants;
import com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory.MonitorCallbackFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AddMonitorCall extends MethodVisitor {

    private final MonitorCallbackFactory monitorCallbackFactory;
    int position = 0;
    public AddMonitorCall(MethodVisitor methodVisitor, int inMethodId) {
        super(ASMConstants.ASM_API_VERSION, methodVisitor);
        monitorCallbackFactory = new MonitorCallbackFactory(methodVisitor, inMethodId);
    }

    public static MethodVisitorFactory factory() {
        return (factoryContext, previous) -> new AddMonitorCall(previous, factoryContext.methodId());
    }

    @Override
    public final void visitInsn(int inst) {
        switch (inst) {
            case MONITORENTER:
                super.visitInsn(DUP);
                super.visitInsn(inst);
                monitorCallbackFactory.afterMonitorEnter(position);
                position++;
                break;
            case MONITOREXIT:
                super.visitInsn(DUP);
                super.visitInsn(inst);
                monitorCallbackFactory.afterMonitorExit(position);
                position++;
                break;

            default:
                super.visitInsn(inst);
        }
    }

}
