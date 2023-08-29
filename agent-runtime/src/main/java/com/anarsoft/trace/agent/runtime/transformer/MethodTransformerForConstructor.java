package com.anarsoft.trace.agent.runtime.transformer;

import com.anarsoft.trace.agent.runtime.MethodDescriptionBuilder;
import com.anarsoft.trace.agent.runtime.TransformConstants;
import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethods;
import com.anarsoft.trace.agent.runtime.transformer.template.ApplyMethodTemplate;
import com.anarsoft.trace.agent.runtime.transformer.template.TemplateMethodDesc;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Iterator;

import static com.anarsoft.trace.agent.runtime.TransformConstants.CALLBACK_CLASS_SYNCHRONIZED_STATEMENT;

/*
 * Für Constructoren wird aktuell nor monitor enter und exit getraced
 *
 *
 * wir können hier keine try catch blöcke verwenden da this noch nicht
 * initialisiert ist
 *
 *
 * @author thomas
 *
 */

public class MethodTransformerForConstructor extends MethodTransformerTraceLineNumber implements Opcodes {

    private final HasGeneratedMethods hasGeneratedMethods;
    private int monitorPosition = 1;
    private int monitorExitPosition = 0;
    protected String className;

    public MethodTransformerForConstructor(MethodVisitor mv, MethodDescriptionBuilder methodDescriptionBuilder,
                                           HasGeneratedMethods hasGeneratedMethods) {
        super(mv, methodDescriptionBuilder);
        this.hasGeneratedMethods = hasGeneratedMethods;
        this.className = className;
    }

    @Override
    public final void visitCode() {
        mv.visitCode();
        onMethodEnter();

	}

    public final void onMethodEnter() {

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
        this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "monitorEnter",
                "(Ljava/lang/Object;II)V");
	}

	protected void onMonitorExit() {
		this.mv.visitInsn(DUP);
		this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
		this.mv.visitLdcInsn(Integer.valueOf(monitorExitPosition));
		monitorExitPosition++;
        this.mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "monitorExit",
                "(Ljava/lang/Object;II)V");
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean isInterface) {

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


	@Override
	public final void visitInsn(int inst) {

		switch (inst) {

		case Opcodes.MONITORENTER:
			onMonitorEnter();
			break;

		case Opcodes.MONITOREXIT:
			onMonitorExit();
			break;

		default:
			break;

		}

		if (inst != Opcodes.MONITORENTER) {
			mv.visitInsn(inst);
		}

	}

	@Override
	public void visitIntInsn(int opcode, int operand) {

		super.visitIntInsn(opcode, operand);

		if (opcode == NEWARRAY) {
			this.mv.visitInsn(DUP);
			this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ArrayAccessCallback",
					"newArray", "(Ljava/lang/Object;)V");
		}
	}
	
	
	@Override
	public final void visitTypeInsn(int opcode, String type) {
	
		super.visitTypeInsn(opcode, type);
		
		if (opcode == ANEWARRAY) {
			this.mv.visitInsn(DUP);
			this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ArrayAccessCallback",
					"newArray", "(Ljava/lang/Object;)V");
		}
	}
}
