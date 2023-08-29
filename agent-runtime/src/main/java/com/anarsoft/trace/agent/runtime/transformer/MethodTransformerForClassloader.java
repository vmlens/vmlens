package com.anarsoft.trace.agent.runtime.transformer;

import com.anarsoft.trace.agent.runtime.MethodDescriptionBuilder;
import com.anarsoft.trace.agent.runtime.transformer.template.ApplyMethodTemplate;
import com.anarsoft.trace.agent.runtime.transformer.template.TemplateMethodDesc;
import org.objectweb.asm.MethodVisitor;

import java.util.Iterator;

import static com.anarsoft.trace.agent.runtime.TransformConstants.CALLBACK_CLASS_STRACKTRACE_BASED_FILTER;
import static com.anarsoft.trace.agent.runtime.TransformConstants.CALLBACK_CLASS_SYNCHRONIZED_STATEMENT;

public class MethodTransformerForClassloader extends MethodTransformerAbstract {
    private int monitorPosition = 1;
    private int monitorExitPosition = 0;

    public MethodTransformerForClassloader(MethodVisitor mv, int access, String desc, String name,
                                           String className, String superClassName, int tryCatchBlockCount, MethodDescriptionBuilder methodDescriptionBuilder, boolean dottyProblematic, boolean useExpandedFrames) {
        super(mv, access, desc, name, className, superClassName, tryCatchBlockCount, methodDescriptionBuilder, dottyProblematic, useExpandedFrames);
    }

    protected void onMethodReturn() {
        this.mv.visitLdcInsn(Integer.valueOf(methodDescriptionBuilder.getId()));
        this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/MethodCallback", "methodExit", "(I)V");
        mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_STRACKTRACE_BASED_FILTER, "onMethodExitDoNotTrace", "()V");
    }

    protected void onMethodEscapedException() {
        onMethodReturn();
    }

    protected void onMethodEnter() {
        mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_STRACKTRACE_BASED_FILTER, "onMethodEnterDoNotTrace", "()V");
        this.mv.visitLdcInsn(Integer.valueOf(methodDescriptionBuilder.getId()));
        this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/MethodCallback", "methodEnter", "(I)V");
    }

    protected int getMethodId() {
        return this.methodDescriptionBuilder.getId();
    }

    protected void onMonitorEnter() {
        this.mv.visitInsn(DUP);

        this.mv.visitInsn(MONITORENTER);

        this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
        this.mv.visitLdcInsn(Integer.valueOf(monitorPosition));
        monitorPosition++;

        this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "monitorEnter", "(Ljava/lang/Object;II)V");


    }

    protected void onMonitorExit() {
        this.mv.visitInsn(DUP);
        this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
        this.mv.visitLdcInsn(Integer.valueOf(monitorExitPosition));
        monitorExitPosition++;

        this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "monitorExit", "(Ljava/lang/Object;II)V");
    }

    public void visitMethodInsnInChild(int opcode, String owner, String name,
                                       String desc, boolean isInterface) {
        ApplyMethodTemplate applyMethodTemplate = null;
        Iterator<TemplateMethodDesc> it = MethodTransformer.templateMethodDescList.iterator();

        while (it.hasNext()) {
            TemplateMethodDesc current = it.next();
            applyMethodTemplate = current.applies(opcode, owner, name, desc);

            if (applyMethodTemplate != null) {
                break;
            }
        }
        if (applyMethodTemplate != null) {

            mv.visitLdcInsn(getMethodId());
            applyMethodTemplate.apply(mv);
        } else {
            mv.visitMethodInsn(opcode, owner, name, desc, isInterface);
        }
    }

    protected void onArrayStore() {

    }

    protected void onArrayLoad() {

    }

}
