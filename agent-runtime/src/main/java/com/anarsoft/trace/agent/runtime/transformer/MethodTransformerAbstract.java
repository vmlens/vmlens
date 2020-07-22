package com.anarsoft.trace.agent.runtime.transformer;

import java.util.Iterator;

import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.anarsoft.trace.agent.runtime.MethodDescriptionBuilder;
import com.anarsoft.trace.agent.runtime.TLinkableWrapper;

/**
 * verantwortlich fuer die korrekte umsetzung von method exit ueber try block
 * 
 * 
 * @author Thomas
 *
 */

public abstract class MethodTransformerAbstract extends MethodTransformerTraceLineNumber implements Opcodes {

	private Label startLabel;
	protected Label endLabel;
	protected String name;
	protected String className;
	private String superClassName;
	private final int tryCatchBlockCount;
	private int currentCount = 0;
	private int access;
	protected String desc;

	private final boolean useExpandedFrames;
	private final boolean dottyProblematic;

	// private boolean firstConstructorInitVisited = false;

	// private boolean frameAlreadyVisited = false;
	// ,boolean dottyProblematic

	public MethodTransformerAbstract(MethodVisitor mv, int access, String desc, String name, String className,
			String superClassName, int tryCatchBlockCount, MethodDescriptionBuilder methodDescriptionBuilder,
			boolean dottyProblematic,boolean useExpandedFrames) {
		super(mv, methodDescriptionBuilder);
		this.name = name;
		this.className = className;
		this.tryCatchBlockCount = tryCatchBlockCount;
		this.superClassName = superClassName;
		this.access = access;
		this.desc = desc;
		this.dottyProblematic = dottyProblematic;
		this.useExpandedFrames = useExpandedFrames;
	}

	private boolean isStatic() {
		return (Opcodes.ACC_STATIC & access) == Opcodes.ACC_STATIC;
	}

	// public boolean isConstructor()
	// {
	// return name.startsWith("<init");
	// }

	private void createTryBlock() {
		if (!dottyProblematic) {
			mv.visitTryCatchBlock(startLabel, endLabel, endLabel, null);
		}

	}

	@Override
	public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
		mv.visitTryCatchBlock(start, end, handler, type);

		currentCount++;

		if (currentCount == tryCatchBlockCount) {
			createTryBlock();
		}

	}

	@Override
	public final void visitCode() {
		mv.visitCode();
		onMethodEnter();

		startLabel = new Label();
		endLabel = new Label();

		if (!dottyProblematic) {
			mv.visitLabel(startLabel);

			if (tryCatchBlockCount == 0) {
				createTryBlock();
			}
		}

	}

	// protected abstract boolean needsObjectInFrame();

	private Object getFrameType(Type type) {
		switch (type.getSort()) {
		case Type.BOOLEAN:
		case Type.CHAR:
		case Type.BYTE:
		case Type.SHORT:
		case Type.INT:
			return Opcodes.INTEGER;
		case Type.LONG:
			return Opcodes.LONG;
		case Type.FLOAT:
			return Opcodes.FLOAT;
		case Type.DOUBLE:
			return Opcodes.DOUBLE;
		case Type.OBJECT:
		case Type.ARRAY:
			return type.getInternalName();
		}
		throw new RuntimeException(type + " can not be converted to frame type");
	}

	/**
	 * private void writeFrameType(final Object type) { if (type instanceof String)
	 * { stackMap.putByte(7).putShort(cw.newClass((String) type)); } else if (type
	 * instanceof Integer) { stackMap.putByte(((Integer) type).intValue()); } else {
	 * stackMap.putByte(8).putShort(((Label) type).position); } }
	 * 
	 * @return
	 */

	protected Object[] buildLocalVariables() {

		TLinkedList<TLinkableWrapper<Object>> parameterList = new TLinkedList<TLinkableWrapper<Object>>();

		if (!isStatic()) {
			parameterList.add(new TLinkableWrapper<Object>(className));

		}

		Type[] args = Type.getArgumentTypes(desc);

		for (Type t : args) {
			parameterList.add(new TLinkableWrapper<Object>(getFrameType(t)));
		}

		Object[] result = new Object[parameterList.size()];

		Iterator<TLinkableWrapper<Object>> it = parameterList.iterator();

		int index = 0;

		while (it.hasNext()) {
			TLinkableWrapper<Object> c = it.next();
			result[index] = c.getElement();

			index++;

		}

		return result;

	}

	@Override
	public void visitMaxs(int maxStack, int maxLocals) {

		if (!dottyProblematic) {
			mv.visitLabel(endLabel);

			Object[] localVariableArray = buildLocalVariables();
			
			if(useExpandedFrames)
			{
				mv.visitFrame(Opcodes.F_NEW, localVariableArray.length, localVariableArray, 1,
						new Object[] { "java/lang/Throwable" });
			}
			else
			{
				mv.visitFrame(Opcodes.F_FULL, localVariableArray.length, localVariableArray, 1,
						new Object[] { "java/lang/Throwable" });

			}
			
		
			onMethodEscapedException();

			mv.visitInsn(ATHROW);
		}

		mv.visitMaxs(maxStack, maxLocals);

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

		case RETURN:
		case IRETURN:
		case FRETURN:
		case LRETURN:
		case DRETURN:
			// case ATHROW:
			onMethodReturn();
			break;

		case ARETURN:
			onMethodReturnObjectRef();
			break;

		case IALOAD:
		case FALOAD:
		case AALOAD:
		case BALOAD:
		case CALOAD:
		case SALOAD:
		case LALOAD:
		case DALOAD:
			onArrayLoad();
			break;

		case IASTORE:
		case FASTORE:
		case AASTORE:
		case BASTORE:
		case CASTORE:
		case SASTORE:
			// case LASTORE:
			// case DASTORE:
			onArrayStore();
			break;

		default:
			break;

		}

		if (inst != Opcodes.MONITORENTER) {
			mv.visitInsn(inst);

			if (inst == Opcodes.MONITOREXIT) {
				onAfterMonitorExit();
			}

		}

	}

	/**
	 * muss überall (auch im konstruktor aufgerufen werden)
	 * 
	 * 
	 */
	
	/*
	 * 
	 * mv.visitTypeInsn(ANEWARRAY, "java/lang/String");
mv.visitFieldInsn(PUTFIELD, "com/vmlens/test/state/array/ArrayInConstructor", "pathNames", "[Ljava/lang/String;");
Label l2 = new Label();
mv.visitLabel(l2);
mv.visitLineNumber(8, l2);
mv.visitVarInsn(ALOAD, 0);
mv.visitIntInsn(BIPUSH, 32);
mv.visitIntInsn(NEWARRAY, T_INT);
mv.visitFieldInsn(PUTFIELD, "com/vmlens/test/state/array/ArrayInConstructor", "pathIndices", "[I");
	 * 
	 * (non-Javadoc)
	 * @see org.objectweb.asm.MethodVisitor#visitInsn(int)
	 */
	
	
	
	

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

	public void onMethodReturnObjectRef() {
		this.onMethodReturn();
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean isInterface) {

		visitMethodInsnInChild(opcode, owner, name, desc, isInterface);

		/**
		 * needed to not get a nullpointer exception in visitMaxs:
		 * java/lang/invoke/MethodHandleImpl <init> java.lang.NullPointerException at
		 * org.objectweb.asm.MethodWriter.visitMaxs(MethodWriter.java:1389) at
		 * com.anarsoft.trace.agent.runtime.transformer.MethodTransformerAbstract.visitMaxs(MethodTransformerAbstract.java:252)
		 * 
		 */
		// herausgenommen

		// if( ! firstConstructorInitVisited && opcode == INVOKESPECIAL &&
		// name.equals("<init>") && isConstructor() && ( owner.equals(superClassName) ||
		// owner.equals(className) ) )
		// {
		//
		// mv.visitLabel(startLabel);
		// firstConstructorInitVisited = true;
		// }
	}

	public abstract void visitMethodInsnInChild(int opcode, String owner, String name, String desc,
			boolean isInterface);

	protected abstract void onMonitorEnter(); // must call monitor enter

	protected abstract void onMonitorExit();

	protected abstract void onAfterMonitorExit();

	protected abstract void onMethodEnter();

	protected abstract void onMethodReturn();

	protected abstract void onMethodEscapedException();

	protected abstract void onArrayLoad();

	protected abstract void onArrayStore();

}
