package com.anarsoft.trace.agent.runtime.transformer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.Label;

import com.anarsoft.trace.agent.runtime.ClassVisitorCreateDesc;
import com.anarsoft.trace.agent.runtime.MethodCounts;
import com.anarsoft.trace.agent.runtime.MethodDescriptionBuilder;
import com.anarsoft.trace.agent.runtime.MethodIdentifier;
import com.anarsoft.trace.agent.runtime.TransformConstants;
import com.anarsoft.trace.agent.runtime.WriteClassDescription;
import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethods;
import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethodsSetBased;
import com.anarsoft.trace.agent.runtime.transformer.template.Add2TemplateMethodDescList;
import com.anarsoft.trace.agent.runtime.transformer.template.ApplyMethodTemplateBeforeAfter;
import com.anarsoft.trace.agent.runtime.transformer.template.TemplateMethodDesc;
import com.anarsoft.trace.agent.runtime.waitPoints.CallbackFactory;
import com.anarsoft.trace.agent.runtime.waitPoints.FilterList;
import com.anarsoft.trace.agent.runtime.waitPoints.MethodInClassIdentifier;
import com.vmlens.trace.agent.bootstrap.typeDesc.AtomicMethodWithCallback;

import com.vmlens.trace.agent.bootstrap.AtomicClassRepo;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.shaded.gnu.trove.set.hash.THashSet;

public class ClassTransformer extends ClassTransformerAbstract implements Opcodes {

	
	
	
	private static final  THashMap<MethodInClassIdentifier,TLinkedList<ApplyMethodTemplateBeforeAfter>>  methodIdentifier2CreateBeforeAfter = Add2TemplateMethodDescList.createBeforeAfter(); 
	private static final  THashMap<MethodInClassIdentifier,TLinkedList<TemplateMethodDesc>>  methodIdentifier2CallbackList = Add2TemplateMethodDescList.createMap(); 
	private static final  THashSet<MethodInClassIdentifier> startThreadMethodSet = Add2TemplateMethodDescList.createStartThreadMethodSet();
	//private static final  THashSet<MethodInClassIdentifier> beganThreadMethodSet = Add2TemplateMethodDescList.createBeganThreadMethodSet();
	
	
	private static final THashSet<String>  classesWithoutFinal = new THashSet<String>();
	
	private void add2NotFinal()
	{
		synchronized(classesWithoutFinal)
		{
			classesWithoutFinal.add(className);
		}
	}
	
	
	private boolean isNotFinal(String name)
	{
		synchronized(classesWithoutFinal)
		{
			return classesWithoutFinal.contains(name);
		}
	}
	
	
	private static final String CALLBACK_CLASS_FIELD_ACCESS = "com/vmlens/trace/agent/bootstrap/callback/FieldAccessCallback";

	protected void addMethodsAndFieldsToClass() {

		if (addGeneratedMethod()) {

			FieldVisitor fieldVisitor = cv.visitField(
					Opcodes.ACC_PRIVATE | Opcodes.ACC_VOLATILE | Opcodes.ACC_SYNTHETIC | Opcodes.ACC_TRANSIENT,
					"_pAnarsoft_", "Ljava/lang/Object;", null, null);

			fieldVisitor.visitEnd();
			
			
			
			if(classVisitorCreateDesc.isAbstract)
			{
				createCallbackMethod4AbstractClasses();
			}
			else
			{
				if(containsNative())
				{
					FieldVisitor fv = cv.visitField(ACC_PRIVATE  + Opcodes.ACC_SYNTHETIC + Opcodes.ACC_TRANSIENT+  Opcodes.ACC_VOLATILE,
							"_pAnarsoft_offset_normal", "Ljava/lang/Long;", null, null);
					fv.visitEnd();
					
					createCallbackMethodNonStatic();
					
				}
				else
				{
					add2NotFinal();
					
					FieldVisitor fv = cv.visitField(ACC_PRIVATE + ACC_STATIC + Opcodes.ACC_SYNTHETIC + Opcodes.ACC_TRANSIENT +  Opcodes.ACC_VOLATILE,
							"_pAnarsoft_offset", "Ljava/lang/Long;", null, null);
					fv.visitEnd();

					createCallbackMethod();
				}
			
			
			
		

			// FieldVisitor fv = cv.visitField(ACC_PRIVATE + ACC_FINAL + ACC_STATIC +
			// Opcodes.ACC_SYNTHETIC + Opcodes.ACC_TRANSIENT , "_pAnarsoft_offset", "J",
			// null, null);
			// fv.visitEnd();

	
			}
		}
		else if(overridePossibleCallbackMethods())
		{
			createOverride4NativeClasses();
		}

	}
	
	
	
	
	private boolean containsNative()
	{
//		if( ! className.contains("$"))
//		{
//			return classVisitorCreateDesc.containsNative;
//		}
//		
//		return ! isNotFinal(removeInnerClassName(className));
		
		return classVisitorCreateDesc.containsNative;
		
	}
	

	
	private void createOverride4NativeClasses() {
		MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "_pAnarsoft_field_access", "(III)V", null, null);
		mv.visitCode();
		Label l0 = new Label();
		mv.visitLabel(l0);
		mv.visitLineNumber(11, l0);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitVarInsn(ILOAD, 1);
		mv.visitVarInsn(ILOAD, 2);
		mv.visitVarInsn(ILOAD, 3);
		mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/FieldAccessCallback", "field_access", "(Ljava/lang/Object;III)V", false);
		Label l1 = new Label();
		mv.visitLabel(l1);
		mv.visitLineNumber(12, l1);
		mv.visitInsn(RETURN);
		Label l2 = new Label();
		mv.visitLabel(l2);

		mv.visitMaxs(4, 4);
		mv.visitEnd();
		
		
	}





	private void createCallbackMethod4AbstractClasses() {
		MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "_pAnarsoft_field_access", "(III)V", null, null);
		mv.visitCode();
		Label l0 = new Label();
		mv.visitLabel(l0);
		mv.visitLineNumber(8, l0);
		mv.visitInsn(RETURN);
		Label l1 = new Label();
		mv.visitLabel(l1);
		
		mv.visitMaxs(0, 4);
		mv.visitEnd();
		
	}





	private boolean addGeneratedMethod()
	{
		if (!addInterface) {
			return false;
		}

		if (!filterList.changeClass(className)) {
			return false;
		}
		
//		if(classVisitorCreateDesc.containsNative)
//		{
//			return false;
//		}
		
		
		if(classVisitorCreateDesc.potentialSingelton)
		{
			return false;
		}
		
		
		
		return true;
	}
	
	
	private boolean overridePossibleCallbackMethods()
	{
		if (!addInterface) {
			return false;
		}

		if (!filterList.changeClass(className)) {
			return false;
		}
		
		if(classVisitorCreateDesc.potentialSingelton)
		{
			return true;
		}
		
		
//		if(classVisitorCreateDesc.containsNative)
//		{
//			return true;
//		}
		
		
		
		return false;
	}
	
	



	protected void addToHasGeneratedMethodsSetBasedAtStart() {
	
		if(addGeneratedMethod())
		{
			HasGeneratedMethodsSetBased.addClassName(className);
		}
	
	}


	private void createCallbackMethodNonStatic() {
		MethodVisitor mv   = cv.visitMethod(ACC_PUBLIC, "_pAnarsoft_field_access", "(III)V", null, null);
		mv.visitCode();
		Label l0 = new Label();
		mv.visitLabel(l0);
		mv.visitLineNumber(10, l0);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitFieldInsn(GETFIELD, className , "_pAnarsoft_offset_normal", "Ljava/lang/Long;");
		mv.visitVarInsn(ILOAD, 1);
		mv.visitVarInsn(ILOAD, 2);
		mv.visitVarInsn(ILOAD, 3);
		mv.visitMethodInsn(INVOKESTATIC,  "com/vmlens/trace/agent/bootstrap/callback/FieldAccessCallback" , "field_access_from_generated_method", "(Ljava/lang/Object;Ljava/lang/Long;III)Ljava/lang/Long;", false);
		mv.visitFieldInsn(PUTFIELD,className , "_pAnarsoft_offset_normal", "Ljava/lang/Long;");
		Label l1 = new Label();
		mv.visitLabel(l1);
		mv.visitLineNumber(11, l1);
		mv.visitInsn(RETURN);
		Label l2 = new Label();
		mv.visitLabel(l2);
//		mv.visitLocalVariable("this", "Lcom/vmlens/classes4Bytecode/WithNonStaticOffset;", null, l0, l2, 0);
//		mv.visitLocalVariable("fieldId", "I", null, l0, l2, 1);
//		mv.visitLocalVariable("methodId", "I", null, l0, l2, 2);
//		mv.visitLocalVariable("callbackId", "I", null, l0, l2, 3);
		mv.visitMaxs(6, 4);
		mv.visitEnd();
	}
	

	private void createCallbackMethod() {
		{
			MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "_pAnarsoft_field_access", "(III)V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(52, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitFieldInsn(GETSTATIC,className, "_pAnarsoft_offset", "Ljava/lang/Long;");
			mv.visitVarInsn(ILOAD, 1);
			mv.visitVarInsn(ILOAD, 2);
			mv.visitVarInsn(ILOAD, 3);
			mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/FieldAccessCallback", "field_access_from_generated_method", "(Ljava/lang/Object;Ljava/lang/Long;III)Ljava/lang/Long;", false);
			mv.visitFieldInsn(PUTSTATIC, className , "_pAnarsoft_offset", "Ljava/lang/Long;");
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLineNumber(53, l1);
			mv.visitInsn(RETURN);
			Label l2 = new Label();
			mv.visitLabel(l2);
//			mv.visitLocalVariable("this",className, null, l0, l2, 0);
//			mv.visitLocalVariable("fieldId", "I", null, l0, l2, 1);
//			mv.visitLocalVariable("methodId", "I", null, l0, l2, 2);
//			mv.visitLocalVariable("callbackId", "I", null, l0, l2, 3);
			mv.visitMaxs(5, 4);
			mv.visitEnd();
		}

	}

	private final boolean addInterface;
	private final HasGeneratedMethods hasGeneratedMethods;
	private final AtomicMethodWithCallback[] isAtomic;

	public ClassTransformer(ClassVisitor cv, String className, FilterList filterList,
			TransformConstants callBackStrings,

			ClassVisitorCreateDesc classVisitorCreateDesc, WriteClassDescription writeClassDescription,
			boolean addInterface, HasGeneratedMethods hasGeneratedMethods) {

		super(cv, className, filterList, callBackStrings, classVisitorCreateDesc, writeClassDescription);

		// if( className.startsWith("org/apache/archiva/web/model") ||
		// className.startsWith("org.apache.archiva.web.model") )
		// {
		// System.out.println("no interface for " + className);
		// this.addInterface = false;
		// }
		// else
		// {
		// this.addInterface = addInterface;
		// }
		//

		this.addInterface = addInterface;
		this.hasGeneratedMethods = hasGeneratedMethods;

		AtomicMethodWithCallback[] atomicFromAnnotation = classVisitorCreateDesc.createAtomic.create();

		if (atomicFromAnnotation != null) {
			this.isAtomic = atomicFromAnnotation;
		} else {

			this.isAtomic = filterList.classIsAtomic(className);
			//this.isAtomic=null;
		}

	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

		if (!isClass) {
			return super.visitMethod(access, name, desc, signature, exceptions);
		}

		MethodIdentifier methodIdentifier = new MethodIdentifier(name, desc);

		MethodCounts methodCounts = classVisitorCreateDesc.getMethodCounts(methodIdentifier);

		boolean traceCalls = classVisitorCreateDesc.traceMethodCalls(methodIdentifier);

		// if(filterList.createMethodParallized( className ) )
		// {
		// traceCalls = true;
		// }

		int methodId = newMethodId();

		MethodDescriptionBuilder methodDescriptionBuilder = new MethodDescriptionBuilder(name, methodId, desc, access,
				classVisitorCreateDesc);
		methodBuilderList.add(methodDescriptionBuilder);

		
		
		
		/*
		 * clinit lesen und schreiben muss herausgefiltert werden.
		 */

		if (name.startsWith("<cl")) {

	
			
			
			return new MethodTransformerForClassloader(super.visitMethod(access, name, desc, signature, exceptions),
					access, desc, name, className, superClassName, methodCounts.tryCatchBlockCount, traceCalls,
					methodDescriptionBuilder, methodCounts.dottyProblematic,useExpandedFrames());

		}

		/**
		 * Filter für Class getDeclaredMethods()
		 * 
		 */

		// Konstruktoren werden gesondert behandelt
		if (name.startsWith("<init")) {
			/*
			 * MethodTransformerForConstructor(MethodVisitor mv, int access, String desc,
			 * String name, String className, String superClassName, int tryCatchBlockCount,
			 * MethodDescriptionBuilder methodDescriptionBuilder, HasGeneratedMethods
			 * hasGeneratedMethods, TransformConstants callBackStrings, boolean
			 * traceMethodCalls)
			 */
			// tring className, HasGeneratedMethods hasGeneratedMethods, TransformConstants
			// callBackStrings,MethodDescriptionBuilder methodDescriptionBuilder
			return new MethodTransformerForConstructor(cv.visitMethod(access, name, desc, signature, exceptions),
					access, desc, name, className, superClassName, methodCounts.tryCatchBlockCount, methodDescriptionBuilder,
					hasGeneratedMethods, callBackStrings, traceCalls);

		}

		MethodInClassIdentifier id = new MethodInClassIdentifier(className, name, desc);

		IsAtomicCallback isAtomicCallback = null;

		if (isAtomic != null) {

		
			
			int atomicId = AtomicClassRepo.getId4AtomicClass(removeInnerClassName(className));

			isAtomicCallback = new IsAtomicCallbackEmpty(atomicId);

			for (AtomicMethodWithCallback atomicMethodWithCallback : isAtomic) {

				if (atomicMethodWithCallback.getMethodName().equals(name)
						&& atomicMethodWithCallback.getDesc().equals(desc)) {
					isAtomicCallback = new IsAtomicCallbackFilled(atomicMethodWithCallback.getCallbackClassName(),
							atomicId);
				}

			}

		}
		
		
		
	
		
		MethodInClassIdentifier methodInClassIdentifier  = new MethodInClassIdentifier(className , name , desc);

		TLinkedList<TemplateMethodDesc> methodSpecificTemplatelist = methodIdentifier2CallbackList.get( methodInClassIdentifier );
		TLinkedList<ApplyMethodTemplateBeforeAfter> templatelistBeforeAfter = methodIdentifier2CreateBeforeAfter.get( methodInClassIdentifier );
		
		
		
		boolean beganThread = false; //beganThreadMethodSet.contains(methodInClassIdentifier);
		
		if( name.equals("run") && desc.equals("()V") && ( access  & Opcodes.ACC_STATIC ) != Opcodes.ACC_STATIC )
		{
			beganThread = true;
		}
		
		

		if ((Opcodes.ACC_SYNCHRONIZED & access) == Opcodes.ACC_SYNCHRONIZED) {

			if ((Opcodes.ACC_STATIC & access) == Opcodes.ACC_STATIC) {

				return new MethodTransformer(cv.visitMethod(access, name, desc, signature, exceptions), access, name,
						desc, className, superClassName, filterList, methodDescriptionBuilder, callBackStrings,
						TraceSynchronization.STATIC, traceCalls, methodCounts.tryCatchBlockCount, hasGeneratedMethods,
						new CallbackFactory(), isAtomicCallback,
						classVisitorCreateDesc.isThreadSafe,methodSpecificTemplatelist , startThreadMethodSet.contains(methodInClassIdentifier), beganThread , methodCounts.dottyProblematic , useExpandedFrames() , templatelistBeforeAfter);

			} else {
				return new MethodTransformer(cv.visitMethod(access, name, desc, signature, exceptions), access, name,
						desc, className, superClassName, filterList, methodDescriptionBuilder, callBackStrings,
						TraceSynchronization.NORMAL, traceCalls, methodCounts.tryCatchBlockCount, hasGeneratedMethods,
						new CallbackFactory(), isAtomicCallback,
						classVisitorCreateDesc.isThreadSafe,methodSpecificTemplatelist , startThreadMethodSet.contains(methodInClassIdentifier),beganThread , methodCounts.dottyProblematic , useExpandedFrames() , templatelistBeforeAfter);

			}

		}

		return new MethodTransformer(cv.visitMethod(access, name, desc, signature, exceptions), access, name, desc,
				className, superClassName, filterList, methodDescriptionBuilder, callBackStrings,
				TraceSynchronization.NONE, traceCalls, methodCounts.tryCatchBlockCount, hasGeneratedMethods,
				new CallbackFactory(), isAtomicCallback,
				classVisitorCreateDesc.isThreadSafe,methodSpecificTemplatelist , startThreadMethodSet.contains(methodInClassIdentifier), beganThread , methodCounts.dottyProblematic , useExpandedFrames(),templatelistBeforeAfter);

	}
	
	public static String removeInnerClassName(String in)
	{
		int index = in.indexOf('$');
		
		if( index > 0 )
		{
			return in.substring(0, index);
			
			
			
		}
		else
		{
			return in;
		}
	}
	
	
	

}
