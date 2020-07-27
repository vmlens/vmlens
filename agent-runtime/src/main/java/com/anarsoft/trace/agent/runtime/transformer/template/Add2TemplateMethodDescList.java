package com.anarsoft.trace.agent.runtime.transformer.template;

import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.shaded.gnu.trove.set.hash.THashSet;

import org.objectweb.asm.Opcodes;

import com.anarsoft.trace.agent.runtime.waitPoints.MethodInClassIdentifier;

public class Add2TemplateMethodDescList implements  Opcodes {


public static  void add2TemplateList(TLinkedList<TemplateMethodDesc> templateMethodDescList )
{
	templateMethodDescList.add(new PolymorphicSignatureGet("getVolatile"));
	templateMethodDescList.add(new PolymorphicSignatureSet("setVolatile"));
	
	templateMethodDescList.add(new PolymorphicSignatureGet("getAcquire"));
	templateMethodDescList.add(new PolymorphicSignatureSet("setRelease"));
	
	
	
	
	templateMethodDescList.add(new PolymorphicSignatureGetAndWithNumber("getAndAdd"));
	templateMethodDescList.add(new PolymorphicSignatureGetAndWithNumber("getAndBitwiseXor"));
	templateMethodDescList.add(new PolymorphicSignatureGetAndWithNumber("getAndBitwiseOr"));
	templateMethodDescList.add(new PolymorphicSignatureGetAndWithNumber("getAndBitwiseAnd"));
	
	
	templateMethodDescList.add(new PolymorphicSignatureGetAndSet("getAndSet"));
	
	templateMethodDescList.add(new PolymorphicSignatureCompareAndSet("compareAndSet"));
	templateMethodDescList.add(new PolymorphicSignatureCompareAndSet("weakCompareAndSet"));
	
	
	templateMethodDescList.add(new PolymorphicSignatureCompareAndExchange("compareAndExchange"));
	
	
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod("java/lang/invoke/MethodHandles$Lookup" , 
			"findVarHandle" ,
 			"(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/invoke/VarHandle;"  , 
 			INVOKEVIRTUAL , 
 			"com/vmlens/trace/agent/bootstrap/callback/MethodHandlesLookupCallback" , 
			"(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;I)Ljava/lang/invoke/VarHandle;" ));
	
	
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod( 
			"sun/misc/Unsafe" ,    "objectFieldOffset" , "(Ljava/lang/reflect/Field;)J" , INVOKEVIRTUAL , 
			"com/vmlens/trace/agent/bootstrap/callback/UnsafeCallback" , "(Lsun/misc/Unsafe;Ljava/lang/reflect/Field;I)J" ) ); 

	/*
	 * unsafeTemplates[7] = new UnsafeTemplateWithMethodId("tryMonitorEnter", "(Ljava/lang/Object;)Z",
				"(Lsun/misc/Unsafe;Ljava/lang/Object;I)Z");
	 */
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod( 
			"sun/misc/Unsafe" ,    "tryMonitorEnter" , "(Ljava/lang/Object;)Z" , INVOKEVIRTUAL , 
			"com/vmlens/trace/agent/bootstrap/callback/UnsafeCallback" , "(Lsun/misc/Unsafe;Ljava/lang/Object;I)Z" ) ); 
	
	

  /*
   * 		unsafeTemplates[8] = new UnsafeTemplateWithMethodId("monitorEnter", "(Ljava/lang/Object;)V",
				"(Lsun/misc/Unsafe;Ljava/lang/Object;I)V");

   */
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod( 
			"sun/misc/Unsafe" ,    "monitorEnter" , "(Ljava/lang/Object;)V" , INVOKEVIRTUAL , 
			"com/vmlens/trace/agent/bootstrap/callback/UnsafeCallback" , "(Lsun/misc/Unsafe;Ljava/lang/Object;I)V" ) ); 
	
	/*
	 * 
		unsafeTemplates[9] = new UnsafeTemplateWithMethodId("monitorExit", "(Ljava/lang/Object;)V",
				"(Lsun/misc/Unsafe;Ljava/lang/Object;I)V");
	 */
	
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod( 
			"sun/misc/Unsafe" ,    "monitorExit" , "(Ljava/lang/Object;)V" , INVOKEVIRTUAL , 
			"com/vmlens/trace/agent/bootstrap/callback/UnsafeCallback" , "(Lsun/misc/Unsafe;Ljava/lang/Object;I)V" ) ); 
	
	
	
	
	
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Iterator" ,    "next" , "()Ljava/lang/Object;" , 
			INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/IteratorCallback" , "(Ljava/util/Iterator;I)Ljava/lang/Object;" ) ); 

	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/Iterator" ,    "hasNext" , "()Z" , 
			INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/obj/IteratorCallback" , "(Ljava/util/Iterator;I)Z" ) ); 
	
	
	
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/Future" ,    "get" , "()Ljava/lang/Object;" , 
			INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback" , "(Ljava/util/concurrent/Future;I)Ljava/lang/Object;" ) );
	
	
	
	// lock templates
	

	// ReentrantLock
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/ReentrantLock" ,    "tryLock" , "()Z" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/ReentrantLock;I)Z" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/ReentrantLock" ,    "tryLock" , "(JLjava/util/concurrent/TimeUnit;)Z" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/ReentrantLock;JLjava/util/concurrent/TimeUnit;I)Z" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/ReentrantLock" ,    "lockInterruptibly" , "()V" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/ReentrantLock;I)V" ) );

	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/ReentrantLock" ,    "lock" , "()V" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/ReentrantLock;I)V" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/ReentrantLock" ,    "unlock" , "()V" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/ReentrantLock;I)V" ) );
	
	// read lock
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock" ,    "tryLock" , "()Z" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;I)Z" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock" ,    "tryLock" , "(JLjava/util/concurrent/TimeUnit;)Z" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;JLjava/util/concurrent/TimeUnit;I)Z" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock" ,    "lockInterruptibly" , "()V" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;I)V" ) );
	
	
	
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock" ,    "lock" , "()V" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;I)V" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock" ,    "unlock" , "()V" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;I)V" ) );
	
	// write Lock
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock" ,    "tryLock" , "()Z" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;I)Z" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock" ,    "tryLock" , "(JLjava/util/concurrent/TimeUnit;)Z" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;JLjava/util/concurrent/TimeUnit;I)Z" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock" ,    "lockInterruptibly" , "()V" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;I)V" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock" ,    "lock" , "()V" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;I)V" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock" ,    "unlock" , "()V" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;I)V" ) );
	
	
	// lock interface 
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/Lock" ,    "tryLock" , "()Z" , 
			INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/Lock;I)Z" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/Lock" ,    "tryLock" , "(JLjava/util/concurrent/TimeUnit;)Z" , 
			INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/Lock;JLjava/util/concurrent/TimeUnit;I)Z" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/Lock" ,    "lockInterruptibly" , "()V" , 
			INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/Lock;I)V" ) );

	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/Lock" ,    "lock" , "()V" , 
			INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/Lock;I)V" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/Lock" ,    "unlock" , "()V" , 
			INVOKEINTERFACE , "com/vmlens/trace/agent/bootstrap/callback/LockTemplateCallback" , "(Ljava/util/concurrent/locks/Lock;I)V" ) );
	
	// Stamped locks
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/StampedLock" ,    "readLock" , "()J" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/StampedLockCallback" , "(Ljava/util/concurrent/locks/StampedLock;I)J" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/StampedLock" ,    "unlockRead" , "(J)V" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/StampedLockCallback" , "(Ljava/util/concurrent/locks/StampedLock;JI)V" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/StampedLock" ,    "writeLock" , "()J" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/StampedLockCallback" , "(Ljava/util/concurrent/locks/StampedLock;I)J" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/StampedLock" ,    "unlockWrite" , "(J)V" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/StampedLockCallback" , "(Ljava/util/concurrent/locks/StampedLock;JI)V" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/StampedLock" ,    "unlock" , "(J)V" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/StampedLockCallback" , "(Ljava/util/concurrent/locks/StampedLock;JI)V" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/StampedLock" ,    "unstampedUnlockWrite" , "()V" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/StampedLockCallback" , "(Ljava/util/concurrent/locks/StampedLock;I)V" ) );
	
	templateMethodDescList.add(new TemplateMethodDescSingleMethod(  "java/util/concurrent/locks/StampedLock" ,    "unstampedUnlockRead" , "()V" , 
			INVOKEVIRTUAL , "com/vmlens/trace/agent/bootstrap/callback/StampedLockCallback" , "(Ljava/util/concurrent/locks/StampedLock;I)V" ) );
	
	
	
	
	
	
	
	
	
	
	
	
 }


//public static THashSet<MethodInClassIdentifier> createBeganThreadMethodSet()
//{
//	 THashSet<MethodInClassIdentifier>  set = new  THashSet<MethodInClassIdentifier> ();
//	 
//	 set.add(new MethodInClassIdentifier("java/util/concurrent/ForkJoinTask" , "doExec" , "()I" ));
//	 
//
//	 return set;
//	 
//}



 public static THashSet<MethodInClassIdentifier> createStartThreadMethodSet()
 {
	 THashSet<MethodInClassIdentifier>  set = new  THashSet<MethodInClassIdentifier> ();
	 
	 set.add(new MethodInClassIdentifier("java/util/concurrent/ThreadPoolExecutor" , "execute" , "(Ljava/lang/Runnable;)V" ));
	 
	 set.add(new MethodInClassIdentifier("java/util/concurrent/ForkJoinPool" , "externalPush" , "(Ljava/util/concurrent/ForkJoinTask;)V" ));
	 
	 
	 return set;
	 
 }
 
 
 public static THashMap<MethodInClassIdentifier,TLinkedList<ApplyMethodTemplateBeforeAfter>> createBeforeAfter()
 {
	  THashMap<MethodInClassIdentifier,TLinkedList<ApplyMethodTemplateBeforeAfter>> map = new  THashMap<MethodInClassIdentifier,TLinkedList<ApplyMethodTemplateBeforeAfter>>();
	  
	  TLinkedList<ApplyMethodTemplateBeforeAfter> forThreadBeginThreadPool = new  TLinkedList<ApplyMethodTemplateBeforeAfter>();
	  
	  forThreadBeginThreadPool.add(new ApplyMethodTemplateBeforeAfter("java/util/concurrent/ForkJoinTask" , 
	  	"exec" ,
		"()Z"  , 
		INVOKEVIRTUAL , 
		"com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback" , 
		"(Ljava/util/concurrent/ForkJoinTask;)V" ));


      map.put( new MethodInClassIdentifier("java/util/concurrent/ForkJoinTask" , "doExec" , "()I") ,  forThreadBeginThreadPool );
	  
	  
   return map;
 }
 
 


  public static THashMap<MethodInClassIdentifier,TLinkedList<TemplateMethodDesc>> createMap()
  {
	  THashMap<MethodInClassIdentifier,TLinkedList<TemplateMethodDesc>> map = new  THashMap<MethodInClassIdentifier,TLinkedList<TemplateMethodDesc>>();
	  
	  TLinkedList<TemplateMethodDesc> forThreadBeginThreadPool = new  TLinkedList<TemplateMethodDesc>();
	  
	  forThreadBeginThreadPool.add(new TemplateMethodDescSingleMethod("java/lang/Runnable" , 
				"run" ,
	 			"()V"  , 
	 			INVOKEINTERFACE , 
	 			"com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback" , 
				"(Ljava/lang/Runnable;I)V" ));
	  
	  
	  map.put( new MethodInClassIdentifier("java/util/concurrent/ThreadPoolExecutor" , "runWorker" , "(Ljava/util/concurrent/ThreadPoolExecutor$Worker;)V") ,  forThreadBeginThreadPool );
	  
	  
	  
	  
	  forThreadBeginThreadPool = new  TLinkedList<TemplateMethodDesc>();
	  
	  forThreadBeginThreadPool.add(new TemplateMethodDescSingleMethod("java/util/concurrent/Callable" , 
				"call" ,
	 			"()Ljava/lang/Object;"  , 
	 			INVOKEINTERFACE , 
	 			"com/vmlens/trace/agent/bootstrap/callback/ExecutorCallback" , 
				"(Ljava/util/concurrent/Callable;I)Ljava/lang/Object;" ));
	  
	  
	  map.put( new MethodInClassIdentifier("java/util/concurrent/FutureTask" , "run" , "()V") ,  forThreadBeginThreadPool );
	  

//	  
//	  forThreadBeginThreadPool = new  TLinkedList<TemplateMethodDesc>();
//	  

//	  
//	  
//	  
//	  
//	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  return map;
  }


	
}
