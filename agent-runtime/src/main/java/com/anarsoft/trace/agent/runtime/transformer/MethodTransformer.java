package com.anarsoft.trace.agent.runtime.transformer;

import com.anarsoft.trace.agent.runtime.MethodDescriptionBuilder;
import com.anarsoft.trace.agent.runtime.TLinkableWrapper;
import com.anarsoft.trace.agent.runtime.TransformConstants;
import com.anarsoft.trace.agent.runtime.filter.FieldRepositoryFacade;
import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethods;
import com.anarsoft.trace.agent.runtime.transformer.gen.Add2TemplateMethodDescListGen;
import com.anarsoft.trace.agent.runtime.transformer.template.Add2TemplateMethodDescList;
import com.anarsoft.trace.agent.runtime.transformer.template.ApplyMethodTemplate;
import com.anarsoft.trace.agent.runtime.transformer.template.ApplyMethodTemplateBeforeAfter;
import com.anarsoft.trace.agent.runtime.transformer.template.TemplateMethodDesc;
import com.anarsoft.trace.agent.runtime.waitPoints.CallbackFactory;
import com.anarsoft.trace.agent.runtime.waitPoints.FilterList;
import com.anarsoft.trace.agent.serialization.FieldAccessDescription;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.FieldIdAndTyp;
import com.vmlens.trace.agent.bootstrap.FieldTyp;
import com.vmlens.trace.agent.bootstrap.StaticMonitorRepository;
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import java.util.Iterator;

public class MethodTransformer extends MethodTransformerAbstract {
	// private MethodDescriptionBuilder methodDescriptionBuilder;
	protected final String CALLBACK_CLASS_FIELD_ACCESS;
	protected final String CALLBACK_CLASS_SYNCHRONIZED_STATEMENT;
	protected final String CALLBACK_CLASS_LOCK_STATEMENT;
	protected final String CALLBACK_CLASS_METHOD_ENTER;
	private FilterList filterList;
	private TraceSynchronization traceSynchronization;
	private boolean traceMethodCalls;
	private final HasGeneratedMethods hasGeneratedMethods;
	private final CallbackFactory callbackFactory;

	private final boolean isThreadSafe;
	private final IsAtomicCallback classIsAtomic;

	private final TLinkedList<TemplateMethodDesc> methodSpecificTemplatelist;
	private final TLinkedList<ApplyMethodTemplateBeforeAfter> templatelistBeforeAfter;
	
	
	private int monitorPosition = 1;
	private int arrayPosition = 0;
	
	//private int fieldPosition = 0;
	private int monitorExitPosition =0;
	

	

	private final boolean startsThread;
	
	private final boolean beganNewThread;

	static final TLinkedList<TemplateMethodDesc> templateMethodDescList = new TLinkedList<TemplateMethodDesc>();

	static {

		Add2TemplateMethodDescListGen.add2TemplateList(templateMethodDescList);
		Add2TemplateMethodDescList.add2TemplateList(templateMethodDescList);

	
	}

	public static boolean isThreadActiveCall(int opcode, String owner, String name, String desc) {
		return (opcode == INVOKEVIRTUAL) && (owner.equals("java/lang/Thread")) && (name.equals("isAlive"))
				&& (desc.equals("()Z"));
	}

	public static void onThreadActiveCall(MethodVisitor mv, int opcode, String owner, String name, String desc) {
		mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ThreadStartCallback", "isAlive",
				"(Ljava/lang/Thread;)Z");
	}

	private boolean isWaitCall(int opcode, String owner, String name, String desc) {
		if (filterList.agentMode.processMonitors()) {
			return (opcode == INVOKEVIRTUAL) && (owner.equals("java/lang/Object")) && (name.equals("wait"))
					&& ((desc.equals("()V")) || (desc.equals("(J)V")) || (desc.equals("(JI)V")));
		} else {
			return false;
		}
	}

	

	public MethodTransformer(MethodVisitor mv, int access, String name, String desc, String className,
			String superClassName, FilterList filterList, MethodDescriptionBuilder methodDescriptionBuilder,
			TransformConstants callBackStrings, TraceSynchronization traceSynchronization, boolean traceMethodCalls,
			int tryCatchBlockCount, HasGeneratedMethods hasGeneratedMethods, CallbackFactory callbackFactory,
			IsAtomicCallback classIsAtomic, boolean isThreadSafe,
			TLinkedList<TemplateMethodDesc> methodSpecificTemplatelist, boolean startsThread,boolean beganNewThread, boolean dottyProblematic,boolean useExpandedFrames,TLinkedList<ApplyMethodTemplateBeforeAfter>  templatelistBeforeAfter) {
		super(mv, access, desc, name, className, superClassName, tryCatchBlockCount, methodDescriptionBuilder,dottyProblematic,useExpandedFrames);

		this.filterList = filterList;
		// this.methodDescriptionBuilder = methodDescriptionBuilder;
		this.CALLBACK_CLASS_FIELD_ACCESS = callBackStrings.callback_class_field_access;
		this.CALLBACK_CLASS_SYNCHRONIZED_STATEMENT = callBackStrings.callback_class_synchronized_statement;
		this.CALLBACK_CLASS_LOCK_STATEMENT = callBackStrings.callback_class_lock_statement;
		this.traceSynchronization = traceSynchronization;
		this.traceMethodCalls = traceMethodCalls;
		this.CALLBACK_CLASS_METHOD_ENTER = callBackStrings.callback_class_method_enter;
		this.hasGeneratedMethods = hasGeneratedMethods;
		this.callbackFactory = callbackFactory;

		this.classIsAtomic = classIsAtomic;

		this.isThreadSafe = isThreadSafe;
		this.methodSpecificTemplatelist = methodSpecificTemplatelist;
		this.startsThread = startsThread;
		this.beganNewThread = beganNewThread;
		this.templatelistBeforeAfter = templatelistBeforeAfter;
		//this.methodLockType = methodLockType;
		

	}

	protected void onMethodEscapedException() {
		onMethodReturn();
	}
	
	
	private boolean isSemaphoreAcquire()
	{
		/*
		 * 
		 * 
		 *  public boolean tryAcquire(int permits, long timeout, TimeUnit unit)
		 *  public boolean tryAcquire(int permits) {
		 * public  void acquireUninterruptibly(int permits) {
		 *    public void acquire(int permits) throws InterruptedException {
		 *   public boolean tryAcquire(long timeout, TimeUnit unit)
		 *   public boolean tryAcquire() {
		 *     public void acquireUninterruptibly() {
		 *     public void acquire() throws InterruptedException {
		 *   
		 */
		
		if( className.startsWith("java/util/concurrent/Semaphore") )
		{
			if(name.startsWith("tryAcquire"))
			{
				return true;
			}
			
			if(name.startsWith("acquire"))
			{
				return true;
			}
		
		}
		

		
		return false;
		
	}
	
	private boolean isDoNotInterleave()
	{
		if( className.startsWith("java/util/concurrent/ThreadPoolExecutor") )
		{
			return true;
		}
		
//		if( className.startsWith("java/util/concurrent/ForkJoinPool") )
//		{
//			return true;
//		}
		
		return false;
		
	}
	
	
	private boolean isFutureTask()
	{
		if( className.equals("java/util/concurrent/FutureTask") && name.equals("run"))
		{
			return true;
		}
		
		
		
		return false;
		
	}
	
	
	private boolean isForkJoinFork()
	{
		if( className.equals("java/util/concurrent/ForkJoinTask") && name.equals("fork"))
		{
			return true;
		}
		
		
		
		return false;
	}
	
	
	
	private boolean isConditionAwait()
	{
		if( className.equals("java/util/concurrent/locks/AbstractQueuedSynchronizer$ConditionObject") && name.equals("await"))
		{
			return true;
		}
		
		
		
		return false;
	}
	
	
	
	

	protected void onMethodReturn() {

		
		if(isConditionAwait())
		{
			mv.visitVarInsn(ALOAD, 0);
			mv.visitFieldInsn(GETFIELD, "java/util/concurrent/locks/AbstractQueuedSynchronizer$ConditionObject", "this$0", "Ljava/util/concurrent/locks/AbstractQueuedSynchronizer;");
			this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback",
					"conditionAwait", "(Ljava/util/concurrent/locks/AbstractQueuedSynchronizer;)V");
		}
		
		
		if (startsThread) {
			this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback",
					"threadStartMethodExit", "()V");

		}
		
		if(isForkJoinFork())
		  {
			  //this.mv.visitVarInsn(ALOAD, 0);
			  this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback", "forkJoinTaskForkExit", "()V");
			     
		  }
		
		
		
		if(beganNewThread)
		  {
			  this.mv.visitVarInsn(ALOAD, 0);
			  this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback", "methodExitExecTask", "(Ljava/lang/Object;)V");
			     
		  }
		

		if (filterList.agentMode.processMonitors()) {
			switch (this.traceSynchronization) {
			case NORMAL:
				this.mv.visitVarInsn(ALOAD, 0);
				this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
				this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_SYNCHRONIZED_STATEMENT,
						"synchronizedMethodExit", "(Ljava/lang/Object;I)V");

				break;
			case STATIC:
				Integer monitorId = Integer.valueOf(StaticMonitorRepository.getOrCreate(this.className));
				this.mv.visitLdcInsn(monitorId);
				this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
				this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_SYNCHRONIZED_STATEMENT,
						"staticSynchronizedMethodExit", "(II)V");

				break;
			case NONE:
				break;

			}
		}


		if (this.classIsAtomic != null) {
			this.mv.visitLdcInsn(Integer.valueOf(classIsAtomic.id()));
			this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
			
			 if(this.classIsAtomic.hasCallback())
	    	 {
				 this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_METHOD_ENTER, "atomicMethodExitWithCallback", "(II)V");
	    	 }
			 else
			 {
				 this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_METHOD_ENTER, "atomicMethodExitWithoutCallback", "(II)V");
			 }
			
			
			
		} else if (isThreadSafe) {
			this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
			this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_METHOD_ENTER, "methodExitOwner", "(I)V");
		} else if (this.traceMethodCalls) {
			this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
			this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_METHOD_ENTER, "methodExit", "(I)V");
		}

		/**
		 * die do not trace oder trace filter methoden müssen die method enter/exit
		 * methoden umrahem. d.h. bei method enter am anfang und bei method exit am ende
		 * 
		 * 
		 */

		if (this.filterList.doNotTraceWhenInMethod(this.className, name, desc)) {
			this.mv.visitMethodInsn(INVOKESTATIC,
					"com/vmlens/trace/agent/bootstrap/callback/StackTraceBasedFilterCallback",
					"onMethodExitDoNotTrace", "()V");
		}
		if (this.filterList.onlyTraceWhenInMethod(this.className, name)) {
			this.mv.visitMethodInsn(INVOKESTATIC,
					"com/vmlens/trace/agent/bootstrap/callback/StackTraceBasedFilterCallback", "onMethodExitDoTrace",
					"()V");
		}

		
		if(isDoNotInterleave())
		{
			this.mv.visitMethodInsn(INVOKESTATIC,
					CALLBACK_CLASS_METHOD_ENTER,
					"doNotInterleaveExit", "()V");
		}
		
		
		if(isFutureTask())
		{
			this.mv.visitMethodInsn(INVOKESTATIC,
					CALLBACK_CLASS_METHOD_ENTER,
					"taskMethodExit", "()V");
		}
		
		if(isSemaphoreAcquire())
		{
			this.mv.visitMethodInsn(INVOKESTATIC,
					CALLBACK_CLASS_METHOD_ENTER,
					"semaphoreAcquireExit", "()V");
		}		
		
	}

	protected void onMethodEnter()
  {
	  
	  
	  if(startsThread)
	  {
		  this.mv.visitVarInsn(ALOAD, 1);
		  this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback", "threadStartMethodEnter", "(Ljava/lang/Object;)V");
		     
	  }
	  
	  if(isForkJoinFork())
	  {
		  this.mv.visitVarInsn(ALOAD, 0);
		  this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback", "forkJoinTaskForkEnter", "(Ljava/util/concurrent/ForkJoinTask;)V");
		     
	  }
	  
	  
	  if(beganNewThread)
	  {
		  this.mv.visitVarInsn(ALOAD, 0);
		  this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback", "methodEnterExecTask", "(Ljava/lang/Object;)V");
		     
	  }
	  
	  
//    if (("<clinit>".equals(this.name)) || ((isConstructor()) && (this.hasFinalFields))) {
//      this.mv.visitMethodInsn(184, "com/vmlens/trace/agent/bootstrap/callback/StackTraceBasedFilterCallback", "onMethodEnterDoNotTraceField", "()V");
//    }
    if (this.filterList.doNotTraceWhenInMethod(this.className,name,desc)) {
      this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/StackTraceBasedFilterCallback", "onMethodEnterDoNotTrace", "()V");
    }
    if (this.filterList.onlyTraceWhenInMethod(this.className , name)) {
      this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/StackTraceBasedFilterCallback", "onMethodEnterDoTrace", "()V");
    }
    
    
  
    
    
  
    if (this.classIsAtomic != null)
    {
    	
    	
    	 this.mv.visitLdcInsn(Integer.valueOf(classIsAtomic.id()));
    	 this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
    	 
    	 
    	 if(this.classIsAtomic.hasCallback())
    	 {
    		    this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_METHOD_ENTER, "atomicMethodEnterWithCallback", "(II)V");
    	 }
    	 else
    	 {
    		    this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_METHOD_ENTER, "atomicMethodEnterWithoutCallback", "(II)V");
    	 }
    	 
     
     }
    else   if(isThreadSafe )
    {
 	   this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
 	   
 	      this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_METHOD_ENTER, "methodEnterOwner", "(I)V");
    }
    else
    if (this.traceMethodCalls)
    {
      this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
      this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_METHOD_ENTER,  "methodEnter"  , "(I)V");
    }
    
    if(filterList.agentMode.processMonitors())
    {
    	switch (this.traceSynchronization)
        {
        case NORMAL: 
          this.mv.visitVarInsn(ALOAD, 0);
          this.mv.visitLdcInsn(Integer.valueOf(getMethodId())); 
          this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "synchronizedMethod", "(Ljava/lang/Object;I)V"); 
     
          break;
        case STATIC: 
          Integer monitorId = Integer.valueOf(StaticMonitorRepository.getOrCreate(this.className));
          this.mv.visitLdcInsn(monitorId);
          this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
          this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "staticSynchronizedMethod", "(II)V");
          break;    
        case NONE:
        	break;
        }
    }
   
    
    if(isDoNotInterleave())
	{
		this.mv.visitMethodInsn(INVOKESTATIC,
				CALLBACK_CLASS_METHOD_ENTER,
				"doNotInterleaveEnter", "()V");
	}
	

    if(isFutureTask())
	{
		this.mv.visitMethodInsn(INVOKESTATIC,
				CALLBACK_CLASS_METHOD_ENTER,
				"taskMethodEnter", "()V");
	}

  
    
    
  }

	protected int getMethodId() {
		return this.methodDescriptionBuilder.getId();
	}

	private void tracePutField(String owner, String fieldName, String desc, FieldIdAndTyp typAndId) {

		Type fieldType = Type.getType(desc);
		if (fieldType.getSize() == 2) {
			this.mv.visitInsn(DUP2_X1);

			this.mv.visitInsn(POP2);

			this.mv.visitInsn(DUP_X2);
		} else {
			this.mv.visitInsn(DUP2);
			this.mv.visitInsn(POP);
		}
		this.mv.visitLdcInsn(Integer.valueOf(typAndId.id));
		this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
		callbackFactory.createCallbackForFieldWrite(owner, fieldName, mv, typAndId,
				this.hasGeneratedMethods.hasGeneratedMethods(owner));

	}

	public void visitFieldInsn(int opcode, String owner, String name, String desc) {
		/**
		 * opcode - the opcode of the type instruction to be visited. This opcode is
		 * either GETSTATIC, PUTSTATIC, GETFIELD or PUTFIELD.
		 *
		 * daher können wir das als erstes erzeugen
		 *
		 */

		FieldIdAndTyp typAndId = FieldRepositoryFacade.get(owner, name);
		boolean isFinal = typAndId.fieldTyp == FieldTyp.FINAL;
		boolean isTraced = false;
		boolean isWrite = false;
		boolean isStatic = false;

		switch (opcode) {
		case (PUTFIELD): {
			isWrite = true;

			if ((!isFinal) && (this.filterList.traceField(owner, name))) {

				isTraced = true;
				tracePutField(owner, name, desc, typAndId);

			}

			break;
		}
		case (PUTSTATIC): {
			isWrite = true;
			isStatic = true;

			if ((!isFinal) && (this.filterList.traceField(owner, name))) {
				isTraced = true;

				this.mv.visitLdcInsn(Integer.valueOf(typAndId.id));
				this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
				// this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_FIELD_ACCESS,
				// "putStaticField", "(II)V");
				callbackFactory.createStaticCallbackForFieldWrite(owner, name, mv, typAndId);
			}

			break;
		}
		case (GETFIELD): {

			if ((!isFinal) && (this.filterList.traceField(owner, name))) {
				isTraced = true;

				this.mv.visitInsn(DUP);

				this.mv.visitLdcInsn(Integer.valueOf(typAndId.id));
				this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
				callbackFactory.createCallbackForFieldRead(owner, name, mv, typAndId,
						this.hasGeneratedMethods.hasGeneratedMethods(owner));

			}
			break;
		}
		case (GETSTATIC): {
			isStatic = true;

			if ((!isFinal) && (this.filterList.traceField(owner, name))) {
				isTraced = true;

				this.mv.visitLdcInsn(Integer.valueOf(typAndId.id));
				this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
				callbackFactory.createStaticCallbackForFieldRead(owner, name, mv, typAndId);

			}

			break;
		}
		}

		this.mv.visitFieldInsn(opcode, owner, name, desc);

		if (isTraced) {

			// afterFieldAccess(int fieldId, int operation)

			this.mv.visitLdcInsn(Integer.valueOf(typAndId.id));

			int operation = MemoryAccessType.IS_READ;

			if (isWrite) {
				operation = MemoryAccessType.IS_WRITE;
			}

			this.mv.visitLdcInsn(Integer.valueOf(operation));
			this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/parallize/ParallizeCallback",
					"afterFieldAccess", "(II)V");

		}

		/*
		 * FieldAccessDescription(String name, String owner, int id, boolean isStatic,
		 * boolean isWrite,boolean isTraced,boolean isFinal)
		 */
		this.methodDescriptionBuilder.getFieldDescriptionList().add(new TLinkableWrapper(
				new FieldAccessDescription(name, owner, typAndId.id, isStatic, isWrite, isTraced, isFinal)));
	}

	protected void onMonitorEnter() {
		if (filterList.agentMode.processMonitors()) {
			this.mv.visitInsn(DUP);

			this.mv.visitInsn(MONITORENTER);

			this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
			this.mv.visitLdcInsn(Integer.valueOf(monitorPosition));
			monitorPosition++;

			this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "monitorEnter",
					"(Ljava/lang/Object;II)V");

		} else {
			this.mv.visitInsn(MONITORENTER);
		}

	}
	
	

	protected void onMonitorExit() {
		if (filterList.agentMode.processMonitors()) {

			this.mv.visitInsn(DUP);
			this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
			this.mv.visitLdcInsn(Integer.valueOf(monitorExitPosition));
			monitorExitPosition++;
			
			
			this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "monitorExit",
					"(Ljava/lang/Object;II)V");
		}

	}

	

	protected void onWaitCall(int opcode, String owner, String name, String desc) {
		this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));
		if (desc.equals("()V")) {
			this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "waitCall",
					"(Ljava/lang/Object;I)V");
		} else if (desc.equals("(J)V")) {
			this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "waitCall",
					"(Ljava/lang/Object;JI)V");
		} else if (desc.equals("(JI)V")) {
			this.mv.visitMethodInsn(INVOKESTATIC, this.CALLBACK_CLASS_SYNCHRONIZED_STATEMENT, "waitCall",
					"(Ljava/lang/Object;JII)V");
		}
	}

	private boolean threadIsAliveTransformed(int opcode, String owner, String name, String desc) {
		if (opcode == INVOKEVIRTUAL && "isAlive".equals(name) && "()Z".equals(desc)) {

			this.mv.visitInsn(DUP);
			this.mv.visitMethodInsn(opcode, owner, name, desc);

			// Jetzt ist object + Boolean auf dem stack
			// object, boolean dup_x1 value2, value1 → value1, value2, value1

			this.mv.visitInsn(DUP_X1);

			mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ThreadStartCallback",
					"isAliveObject", "(Ljava/lang/Object;Z)V");

			return true;
		} else {
			return false;
		}

	}

	// @Override
	// public void visitInvokeDynamicInsn(String name, String descriptor, Handle
	// bootstrapMethodHandle,
	// Object... bootstrapMethodArguments) {
	//
	// System.err.println( name + " " + descriptor + bootstrapMethodHandle);
	//
	// super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle,
	// bootstrapMethodArguments);
	// }

	public void visitMethodInsnInChild(int opcode, String owner, String name, String desc, boolean isInterface) {

		if (this.classIsAtomic != null) {

			if (classIsAtomic.isCallback(owner, name, desc)) {

				this.mv.visitLdcInsn(Integer.valueOf(this.classIsAtomic.id()));
				
				this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/parallize/ParallizeCallback",
						"callbackMethodEnter", "(I)V", false);
			}

		}

		ApplyMethodTemplate applyMethodTemplate = null;

		Iterator<TemplateMethodDesc> it = templateMethodDescList.iterator();

		while (it.hasNext()) {
			TemplateMethodDesc current = it.next();
			applyMethodTemplate = current.applies(opcode, owner, name, desc);

			if (applyMethodTemplate != null) {
				break;
			}

		}

		ApplyMethodTemplateBeforeAfter applyMethodTemplateBeforeAfter = null;
		
		if (templatelistBeforeAfter != null) {
			Iterator<ApplyMethodTemplateBeforeAfter> iter = templatelistBeforeAfter.iterator();

			while (iter.hasNext()) {
				ApplyMethodTemplateBeforeAfter current = iter.next();
				ApplyMethodTemplateBeforeAfter temp = current.applies(opcode, owner, name, desc);

				if (temp != null) {
					applyMethodTemplateBeforeAfter = temp;

					break;
				}

			}
		}
		
		
		

		if (methodSpecificTemplatelist != null) {
			Iterator<TemplateMethodDesc> iter = methodSpecificTemplatelist.iterator();

			while (iter.hasNext()) {
				TemplateMethodDesc current = iter.next();
				ApplyMethodTemplate temp = current.applies(opcode, owner, name, desc);

				if (temp != null) {
					applyMethodTemplate = temp;

					break;
				}

			}
		}
		
		if( applyMethodTemplateBeforeAfter != null)
		{
			applyMethodTemplateBeforeAfter.applyBefore(mv);
		}
		

		if (applyMethodTemplate != null) {
			mv.visitLdcInsn(getMethodId());
			applyMethodTemplate.apply(mv);
		} else if (!threadIsAliveTransformed(opcode, owner, name, desc)) {

			if (isWaitCall(opcode, owner, name, desc)) {
				onWaitCall(opcode, owner, name, desc);
			} else if (opcode == INVOKEVIRTUAL && "java/lang/Class".equals(owner) && "getDeclaredMethods".equals(name)
					&& "()[Ljava/lang/reflect/Method;".equals(desc)) {
				mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ReflectionFilter",
						"getFilteredDeclaredMethods", "(Ljava/lang/Class;)[Ljava/lang/reflect/Method;", false);
			}
			else if (opcode == INVOKEVIRTUAL && "java/lang/Class".equals(owner) && "getDeclaredFields".equals(name)
					&& "()[Ljava/lang/reflect/Field;".equals(desc)) {
				mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ReflectionFilter",
						"getFilteredDeclaredFields", "(Ljava/lang/Class;)[Ljava/lang/reflect/Field;", false);
			}
			else if (opcode == INVOKEVIRTUAL && "java/lang/Class".equals(owner) && "getMethods".equals(name)
					&& "()[Ljava/lang/reflect/Method;".equals(desc)) {
				mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ReflectionFilter",
						"getFilteredMethods", "(Ljava/lang/Class;)[Ljava/lang/reflect/Method;", false);
			} else {
				this.mv.visitMethodInsn(opcode, owner, name, desc, isInterface);

				// Wenn clone dann müssen wir den state für das geclonte objekt zurücksetzen
				if (opcode == INVOKESPECIAL && owner.equals("java/lang/Object") && name.equals("clone")
						&& desc.equals("()Ljava/lang/Object;")) {
					this.mv.visitInsn(DUP);
					this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/CloneCallback",
							"resetState", "(Ljava/lang/Object;)V", false);
				} else {

					this.mv.visitMethodInsn(INVOKESTATIC,
                            "com/vmlens/trace/agent/bootstrap/parallize/ParallizeCallback", "afterMethod", "()V",
							false);

				}

			}
		}
		
		if( applyMethodTemplateBeforeAfter != null)
		{
			applyMethodTemplateBeforeAfter.applyAfter(mv);
		}
		
		

		if (this.classIsAtomic != null) {
			if (classIsAtomic.isCallback(owner, name, desc)) {
				this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/parallize/ParallizeCallback",
						"callbackMethodExit", "()V", false);
			}
		}

	}

	protected void onAfterMonitorExit() {
		this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/parallize/ParallizeCallback",
				"afterMonitor", "()V", false);
	}

	protected void onArrayLoad() {

		if (this.filterList.traceArray(className, name)) {
			this.mv.visitInsn(DUP2);
			this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));

			this.mv.visitLdcInsn(Integer.valueOf(arrayPosition));
			arrayPosition++;

			this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ArrayAccessCallback",
					"get", "(Ljava/lang/Object;III)V");
		}
	}

	protected void onArrayStore() {
		if (this.filterList.traceArray(className, name)) {
			this.mv.visitInsn(DUP_X2);
			this.mv.visitInsn(POP);
			this.mv.visitInsn(DUP2_X1);

			this.mv.visitLdcInsn(Integer.valueOf(getMethodId()));

			this.mv.visitLdcInsn(Integer.valueOf(arrayPosition));
			arrayPosition++;

			this.mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/ArrayAccessCallback",
					"put", "(Ljava/lang/Object;III)V");
		}
	}
}
