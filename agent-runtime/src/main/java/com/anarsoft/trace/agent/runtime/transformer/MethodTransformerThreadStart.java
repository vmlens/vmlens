package com.anarsoft.trace.agent.runtime.transformer;


import com.anarsoft.trace.agent.runtime.MethodDescriptionBuilder;
import org.objectweb.asm.MethodVisitor;

/**
 *  public synchronized void start() {
       
	 * This method is not invoked for the main method thread or "system"
	 * group threads created/set up by the VM. Any new functionality added 
	 * to this method in the future may have to also be added to the VM.
	 *
	 * A zero status value corresponds to state "NEW".
       
        if (threadStatus != 0)
            throw new IllegalThreadStateException();
        group.add(this);
        start0();
        if (stopBeforeStart) {
	    stop0(throwableFromStop);
	}
    }

 * @author Thomas Krieger
 *
 */


public class MethodTransformerThreadStart  extends MethodTransformerAbstract {

	
	protected static final String CALLBACK_CLASS_THREAD_START = "com/vmlens/trace/agent/bootstrap/callback/ThreadStartCallback";

	public MethodTransformerThreadStart(MethodVisitor mv, int access, String desc, String name, String className,
			String superClassName, int tryCatchBlockCount, MethodDescriptionBuilder methodDescriptionBuilder, boolean dottyProblematic,boolean useExpandedFrames) {
		super(mv, access, desc, name, className, superClassName, tryCatchBlockCount, methodDescriptionBuilder,dottyProblematic,useExpandedFrames);
		// TODO Auto-generated constructor stub
	}
	

	
	protected void onMethodReturn() {

		  this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/parallelize/facade/ParallelizeCallback", "afterThreadStart", "()V");

	}

	protected void onMethodEscapedException() {
		onMethodReturn();
	}

	protected void onMethodEnter() {

		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_THREAD_START , "threadStart", "(Ljava/lang/Object;)V");

	}

	protected int getMethodId() {
		return this.methodDescriptionBuilder.getId();
	}

	protected void onMonitorEnter() {

		this.mv.visitInsn(MONITORENTER);

	}

	protected void onMonitorExit() {

	}

	public void visitMethodInsnInChild(int opcode, String owner, String name, String desc, boolean isInterface) {
		mv.visitMethodInsn(opcode, owner, name, desc, isInterface);
	}

	protected void onArrayStore() {

	}

	protected void onArrayLoad() {

	}

}
