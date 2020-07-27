package com.anarsoft.trace.agent.runtime;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethods;
import com.anarsoft.trace.agent.runtime.transformer.ClassTransformer;
import com.anarsoft.trace.agent.runtime.transformer.ClassTransformerTraceClassLoader;
import com.anarsoft.trace.agent.runtime.transformer.ClassTransformerTraceClassThread;
import com.anarsoft.trace.agent.runtime.transformer.ClassTransformerTraceMethod;
import com.anarsoft.trace.agent.runtime.transformer.ClassTransformerTraceVmlensApi;
import com.anarsoft.trace.agent.runtime.transformer.FactoryMethodTransformer;
import com.anarsoft.trace.agent.runtime.transformer.FactoryMethodTransformerMethodEnterNoArg;
import com.anarsoft.trace.agent.runtime.transformer.FactoryMethodTransformerMethodEnterObjectArg;
import com.anarsoft.trace.agent.runtime.transformer.FactoryMethodTransformerMethodExit;
import com.anarsoft.trace.agent.runtime.transformer.FactoryMethodTransformerMethodExitWithReturnBoolean;
import com.anarsoft.trace.agent.runtime.transformer.FactoryMethodTransformerMethodExitWithReturnInt;
import com.anarsoft.trace.agent.runtime.waitPoints.FilterList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;

/**
 * Memory consistency effects: As with other concurrent collections, actions in
 * a thread prior to placing an object into a ConcurrentMap as a key or value
 * happen-before actions subsequent to the access or removal of that object from
 * the ConcurrentMap in another thread.
 *
 */

public class AgentClassFileTransformer implements ClassFileTransformer {

	public static final int ASM_API_VERSION = Opcodes.ASM7;

	private FilterList filterList;
	protected final TransformConstants callBackStrings;

	private boolean skipJavaUtil;
	private WriteClassDescription writeClassDescription;

	// || name.startsWith("java/io") || name.startsWith("java/nio")

	private static boolean isUtilPackage(String name) {
		if (name.startsWith("java/util")) {
			return true;
		}

		return false;

	}

	private boolean addInterface;
	private final HasGeneratedMethods hasGeneratedMethods;

	public AgentClassFileTransformer(FilterList filterList, TransformConstants callBackStrings, boolean skipJavaUtil,
			WriteClassDescription writeClassDescription, boolean addInterface,
			HasGeneratedMethods hasGeneratedMethods) {
		super();

		this.filterList = filterList;
		this.callBackStrings = callBackStrings;
		this.skipJavaUtil = skipJavaUtil;
		this.writeClassDescription = writeClassDescription;
		this.addInterface = addInterface;
		this.hasGeneratedMethods = hasGeneratedMethods;

	}

	// java/lang/invoke/LambdaMetafactory
	// || name.startsWith("java/io")

	public static boolean doNotTraceIn(String name) {

		if (name.startsWith("java/io")) {
			if (name.equals("java/io/ObjectStreamClass") || name.equals("java/io/ObjectInputStream")) {
				return false;
			} else {
				return true;
			}
		}

		return name.startsWith("java/lang/ClassLoader")

				|| name.startsWith("java/lang/Class") || name.startsWith("java/lang/invoke");
		// || name.startsWith("java/io") ||
		// name.startsWith("com/sun") ||
		// name.startsWith("sun/net")
		// || name.startsWith("sun/io")
		// || name.startsWith("sun/launcher");

	}
	
	


	public static boolean shouldBeTransformed(boolean skipJavaUtil, String name) {
		
		
		
		
		
		if (name.indexOf('[') > -1) {
			return false;
		}

		// CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;
		//
		// System.out.println(name);
		//
		//
		// CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;
		//
		// if( ! name.startsWith("org/apache/maven/surefire/util/ReflectionUtils") )
		// {
		// return false;
		// }
		//

		if (name.equals("java/lang/Thread") || doNotTraceIn(name)) {
			return true;
		}

		// if( name.equals("java/util/concurrent") )
		// {
		// return true;
		// }

		// ||
		// name.startsWith("java/util/concurrent" ) sun/reflect/ConstantPool
		/*
		 * name.startsWith("sun/reflect/ConstantPool") ||
		 * name.startsWith("sun/reflect/UnsafeStaticFieldAccessorImpl") ||
		 * name.startsWith("sun/nio/") || name.startsWith("java/nio/") ||
		 * name.startsWith("sun/util/calendar") || name.startsWith("sun/net/www") ||
		 * name.startsWith("sun/reflect/") ||
		 * name.startsWith("java/lang/invoke/MethodHandle") ||
		 * name.startsWith("java/lang/invoke/") ||
		 * java/util/concurrent/atomic
		 */

		if (

		name.startsWith("com/vmlens/shaded") || name.startsWith("com/vmlens/trace/agent/bootstrap") ||
	//	name.startsWith("java/util/concurrent/atomic/AtomicReferenceArray") ||
		

				name.startsWith("java/util/IdentityHashMap") || name.startsWith("java/lang/reflect/AccessibleObject")
				|| name.startsWith("java/lang/reflect/Executable") ||
				// name.startsWith("java/util") ||
				// name.startsWith("sun/util") ||
				name.startsWith("sun/reflect/GeneratedMethodAccessor")
				|| name.startsWith("java/util/concurrent/locks/LockSupport") || // für backpressure
				name.startsWith("sun/nio/ch/FileChannelImpl") ||

				/**
				 * wegen: "Reference Handler" daemon prio=10 tid=0x00007fa890138800 nid=0x6ca1
				 * waiting on condition [0x00007fa888668000] java.lang.Thread.State:
				 * TIMED_WAITING (sleeping) at java.lang.Thread.sleep(Native Method) at
				 * java.anarsoft.executorService.internal.manyToOne.BackPressure.writeOne(BackPressure.java:34)
				 * at
				 * java.anarsoft.executorService.internal.manyToOne.QueueManyWritersForThreadLocal.accept(QueueManyWritersForThreadLocal.java:61)
				 * at
				 * java.anarsoft.trace.agent.bootstrap.callback.MethodCallback.methodEnter(MethodCallback.java:175)
				 * at java.lang.Class.getName(Class.java) at
				 * java.anarsoft.trace.agent.bootstrap.callback.SynchronizedStatementCallback.monitorExit(SynchronizedStatementCallback.java:309)
				 * at sun.nio.ch.FileChannelImpl$Unmapper.run(FileChannelImpl.java:834) - locked
				 * <0x0000000580246488> (a java.lang.Class for
				 * sun.nio.ch.FileChannelImpl$Unmapper) at
				 * sun.misc.Cleaner.clean(Cleaner.java:142) at
				 * java.lang.ref.Reference$ReferenceHandler.run(Reference.java:141)
				 * 
				 * "anarsoft" daemon prio=10 tid=0x00007fa890472000 nid=0x6ca5 waiting for
				 * monitor entry [0x00007fa88820f000] java.lang.Thread.State: BLOCKED (on object
				 * monitor) at
				 * sun.nio.ch.FileChannelImpl$Unmapper.<init>(FileChannelImpl.java:808) -
				 * waiting to lock <0x0000000580246488> (a java.lang.Class for
				 * sun.nio.ch.FileChannelImpl$Unmapper) at
				 * sun.nio.ch.FileChannelImpl$Unmapper.<init>(FileChannelImpl.java:783) at
				 * sun.nio.ch.FileChannelImpl.map(FileChannelImpl.java:936) at
				 * java.anarsoft.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow.getByteBuffer(StreamWrapperWithSlidingWindow.java:112)
				 * at
				 * java.anarsoft.trace.agent.bootstrap.event.gen.MonitorEnterEventGen.serialize(MonitorEnterEventGen.java:54)
				 * at
				 * com.anarsoft.trace.agent.runtime.WriteEvent2File.execute(WriteEvent2File.java:58)
				 * at
				 * com.anarsoft.trace.agent.runtime.WriteEvent2File.execute(WriteEvent2File.java:11)
				 * at
				 * java.anarsoft.executorService.internal.manyToOne.QueueReader.processTillStopped(QueueReader.java:70)
				 * at
				 * java.anarsoft.executorService.internal.service.TaskExecutor.run(TaskExecutor.java:30)
				 * at java.lang.Thread.run(Thread.java:745)
				 * 
				 * 
				 */
				name.startsWith("sun/misc/Cleaner") ||

				/**
				 * 
				 * herausgenommen wegen:
				 * 
				 * "anarsoft" daemon prio=10 tid=0x00007f6dc3613000 nid=0x737f waiting for
				 * monitor entry [0x00007f6d8fb6c000] java.lang.Thread.State: BLOCKED (on object
				 * monitor) at sun.misc.Cleaner.add(Cleaner.java) - waiting to lock
				 * <0x0000000580027648> (a java.lang.Class for sun.misc.Cleaner) at
				 * sun.misc.Cleaner.create(Cleaner.java:132) at
				 * java.nio.DirectByteBuffer.<init>(DirectByteBuffer.java:179) at
				 * sun.reflect.GeneratedConstructorAccessor1.newInstance(Unknown Source) at
				 * sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
				 * at java.lang.reflect.Constructor.newInstance(Constructor.java:526) at
				 * sun.nio.ch.Util.newMappedByteBuffer(Util.java:393) at
				 * sun.nio.ch.FileChannelImpl.map(FileChannelImpl.java:943) at
				 * java.anarsoft.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow.getByteBuffer(StreamWrapperWithSlidingWindow.java:112)
				 * at
				 * java.anarsoft.trace.agent.bootstrap.event.gen.MethodExitEventGen.serialize(MethodExitEventGen.java:42)
				 * at
				 * com.anarsoft.trace.agent.runtime.WriteEvent2File.execute(WriteEvent2File.java:58)
				 * at
				 * com.anarsoft.trace.agent.runtime.WriteEvent2File.execute(WriteEvent2File.java:11)
				 * at
				 * java.anarsoft.executorService.internal.manyToOne.QueueReader.processTillStopped(QueueReader.java:70)
				 * at
				 * java.anarsoft.executorService.internal.service.TaskExecutor.run(TaskExecutor.java:30)
				 * at java.lang.Thread.run(Thread.java:745)
				 * 
				 * 
				 * "Reference Handler" daemon prio=10 tid=0x00007f6dc0138800 nid=0x737b waiting
				 * for monitor entry [0x00007f6db8272000] java.lang.Thread.State: BLOCKED (on
				 * object monitor) at
				 * java.anarsoft.trace.agent.bootstrap.callback.field.CallbackStatic.non_volatile_access(CallbackStatic.java:139)
				 * - waiting to lock <0x00000005801a6f20> (a
				 * java.gnu.trove.map.hash.TIntLongHashMap) at
				 * java.anarsoft.trace.agent.bootstrap.callback.field.StrategyImplNonVolatile.field_access_static(StrategyImplNonVolatile.java:33)
				 * at
				 * java.anarsoft.trace.agent.bootstrap.callback.FieldAccessCallback.field_access_static(FieldAccessCallback.java:59)
				 * at sun.misc.Cleaner.remove(Cleaner.java:94) - locked <0x0000000580027648> (a
				 * java.lang.Class for sun.misc.Cleaner) at
				 * sun.misc.Cleaner.clean(Cleaner.java:139) at
				 * java.lang.ref.Reference$ReferenceHandler.run(Reference.java:141)
				 * 
				 */

				// name.startsWith("java/security") ||

				name.startsWith("sun/misc/Unsafe") || // beinhaltet sun.misc.Unsafe
				name.startsWith("java/lang/ref/") || name.startsWith("java/nio/") || // wird in events verwendet
																						// HeapByteBuffer Buffer
				// name.startsWith("java/nio/HeapByteBuffer") ||
				// name.startsWith("java/nio/Buffer") ||
				// name.startsWith("java/nio/Bits") ||
				name.startsWith("sun/reflect/UnsafeQualifiedStatic") || // beinhaltet
																		// sun/reflect/UnsafeQualifiedStaticLongFieldAccessorImpl
																		// sun.reflect.UnsafeQualifiedStaticObjectFieldAccessorImpl
				// name.startsWith("java/net/URLClassLoader") ||

				// für jdk 11

				name.startsWith("jdk") || name.startsWith("java/lang/reflect/Proxy") || name.startsWith("java/security")
				||
				// name.startsWith("java/util/logging") ||

				name.startsWith("java/util/ServiceLoader") ||

				name.startsWith("java/util/Arrays") || name.startsWith("java/util/HashMap")
				|| name.startsWith("java/util/ImmutableCollections") || name.startsWith("java/util/Optional")
				|| name.startsWith("java/util/Objects") || name.startsWith("java/util/AbstractCollection")
				|| name.startsWith("java/util/Collections") || name.startsWith("java/util/HashSet") ||

				// name.startsWith("java/util/Objects") ||
				// name.startsWith("java/util/AbstractSet") ||
				// name.startsWith("java/util/ImmutableCollections") ||
				// name.startsWith("java/util/jar") ||
				// name.startsWith("java/util/Properties") ||
				// name.startsWith("java/util/Hashtable") ||
				// name.startsWith("java/util/Dictionary") ||
				// name.startsWith("java/util/function") ||
				// name.startsWith("java/util/spi") ||
				// name.startsWith("java/util/stream") ||
				// name.startsWith("java/util/zip") ||
				name.startsWith("sun/launcher") ||

				// name.startsWith("java/lang/invoke") ||

				// name.startsWith("java/lang/") ||
				// für jdk 11 funktioniert die prüfung auf den selben class loader nicht
				// daher hier explizit herausgenommen

				// name.startsWith("java") ||
				name.startsWith("com/anarsoft/trace/agent/runtime") || name.startsWith("gnu/trove")
				|| name.startsWith("org/objectweb/asm") 
			//	|| name.startsWith("java/util/concurrent/CompletableFuture") 	
			//	|| name.startsWith("java/util/InvalidPropertiesFormatException") 
		
				

		) {

			// name.endsWith("SynchronizedMap")

			/**
			 * fehler in jdk 11 tritt bei mehreren in
			 * name.equals("java/util/Collections$UnmodifiableNavigableMap") ||
			 * name.equals("java/util/Collections$UnmodifiableSortedMap") ||
			 * name.equals("java/util/Collections$UnmodifiableMap") ||
			 * name.equals("java/util/Collections$UnmodifiableRandomAccessList") ||
			 * name.equals("java/util/Collections$UnmodifiableList") ||
			 * name.equals("java/util/Collections$UnmodifiableNavigableSet") ||
			 * name.equals("java/util/Collections$UnmodifiableSortedSet") ||
			 * name.equals("java/util/Collections$UnmodifiableSet") ||
			 * name.equals("java/util/Collections$UnmodifiableCollection")
			 * 
			 * auf
			 * 
			 * 
			 */

			if (name.equals("java/util/Collections$AsLIFOQueue") || name.equals("java/util/Collections$SetFromMap")
					|| name.equals("java/util/Collections$ReverseComparator2")
					|| name.equals("java/util/Collections$ReverseComparator")
					|| name.equals("java/util/Collections$CopiesList")
					|| name.equals("java/util/Collections$SingletonMap")
					|| name.equals("java/util/Collections$SingletonList")
					|| name.equals("java/util/Collections$SingletonSet")
					|| name.equals("java/util/Collections$EmptyMap") || name.equals("java/util/Collections$EmptyList")
					|| name.equals("java/util/Collections$EmptySet")
					|| name.equals("java/util/Collections$EmptyEnumeration")
					|| name.equals("java/util/Collections$EmptyListIterator")
					|| name.equals("java/util/Collections$EmptyIterator")
					|| name.equals("java/util/Collections$CheckedNavigableMap")
					|| name.equals("java/util/Collections$CheckedSortedMap")
					|| name.equals("java/util/Collections$CheckedMap")
					|| name.equals("java/util/Collections$CheckedRandomAccessList")
					|| name.equals("java/util/Collections$CheckedList")
					|| name.equals("java/util/Collections$CheckedNavigableSet")
					|| name.equals("java/util/Collections$CheckedSortedSet")
					|| name.equals("java/util/Collections$CheckedSet")
					|| name.equals("java/util/Collections$CheckedQueue")
					|| name.equals("java/util/Collections$CheckedCollection")
					|| name.equals("java/util/Collections$SynchronizedNavigableMap")
					|| name.equals("java/util/Collections$SynchronizedSortedMap")
					|| name.equals("java/util/Collections$SynchronizedMap")
					|| name.equals("java/util/Collections$SynchronizedRandomAccessList")
					|| name.equals("java/util/Collections$SynchronizedList")
					|| name.equals("java/util/Collections$SynchronizedNavigableSet")
					|| name.equals("java/util/Collections$SynchronizedSortedSet")
					|| name.equals("java/util/Collections$SynchronizedSet"))

			/*
			 * herausgenommen: || name.equals("java/util/Collections$SynchronizedCollection"
			 * "main" #1 prio=5 os_prio=0 tid=0x00007f0a2400d800 nid=0x365b runnable
			 * [0x00007f0a2ab68000] java.lang.Thread.State: RUNNABLE at
			 * java.lang.Throwable.fillInStackTrace(Native Method) at
			 * java.lang.Throwable.fillInStackTrace(Throwable.java:783) - locked
			 * <0x000000076e89d2d8> (a java.lang.IllegalMonitorStateException) at
			 * java.lang.Throwable.<init>(Throwable.java:250) at
			 * java.lang.Exception.<init>(Exception.java:54) at
			 * java.lang.RuntimeException.<init>(RuntimeException.java:51) at
			 * java.lang.IllegalMonitorStateException.<init>(IllegalMonitorStateException.
			 * java:50) at
			 * java.util.Collections$SynchronizedCollection.add(Collections.java:2035) at
			 * java.lang.ClassLoader.checkPackageAccess(ClassLoader.java:508) at
			 * com.vmlens.test.interleaveReportJdk7.TestRaceBetweenSyncBlocks.runTest(
			 * TestRaceBetweenSyncBlocks.java:38) at
			 * sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at
			 * sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
			 * at
			 * sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.
			 * java:43) at java.lang.reflect.Method.invoke(Method.java:498) at
			 * org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.
			 * java:44)
			 */

			{
				return true;
			} else {
				return false;
			}

		}

		// name.startsWith("com/sun") ||

		if (name.startsWith("sun/instrument") || name.startsWith("sun/tracing") || name.startsWith("sun/tools")
				|| name.startsWith("sun/management") || name.startsWith("sun/dc")) {

			return false;

		}

		//

		// if( ! name.startsWith("java/util/concurrent")
		// && ! name.startsWith("java/util/ResourceBundle")
		// && ! name.startsWith("java/util/spi" )
		// && ! name.startsWith("java/util/Formatter" )
		// && ! name.startsWith("java/util/regex" )
		// && ! name.startsWith("java/util/Collection" )
		// // && ! name.startsWith("java/util/Arrays" ) // muss bleiben
		// /*
		// * import jdk.internal.HotSpotIntrinsicCandidate;
		// import jdk.internal.util.ArraysSupport;
		// */
		//
		//
		// && ! name.startsWith("java/util/StringJoiner" )
		// && ! name.startsWith("java/util/LinkedHashMap" )
		// && ! name.startsWith("java/util/RegularEnumSet" )
		// && ! name.startsWith("java/util/ListResourceBundle")
		// && ! name.startsWith("java/util/StringTokenizer")
		// && ! name.startsWith("java/util/Locale")
		// && ! name.startsWith("java/util/Currency")
		// && ! name.startsWith("java/util/Formattable")
		// && ! name.startsWith("java/util/NoSuchElementException")
		//
		// && ! name.startsWith("java/util/ArrayList")
		// // && ! name.startsWith("java/util/HashMap") // muss bleiben
		// && ! name.startsWith("java/util/Map")
		// && ! name.startsWith("java/util/List")
		// && ! name.startsWith("java/util/Set")
		// && ! name.startsWith("java/util/Iterator")
		// && ! name.startsWith("java/util/zip")
		//
		// && ! name.startsWith("java/util/stream")
		// && ! name.startsWith("java/util/Spliterator")
		// && ! name.startsWith("java/util/Hashtable")
		// && ! name.startsWith("java/util/Vector")
		// && ! name.startsWith("java/util/ArrayDeque")
		//
		//
		// // && ! name.startsWith("java/util/ImmutableCollections$") //muss bleiben
		// /*
		// * import jdk.internal.vm.annotation.Stable;
		// */
		//
		//
		// && ! name.startsWith("java/util/function")
		// && ! name.startsWith("java/util/jar")
		// && ! name.startsWith("java/util/LinkedList")
		//
		// && ! name.startsWith("java/util/WeakHashMap")
		// && ! name.startsWith("java/util/LinkedHashSet")
		// && ! name.startsWith("java/util/AbstractMap")
		// && ! name.startsWith("java/util/AbstractSet")
		//
		//
		// && ! name.startsWith("java/util/StringTokenizer")
		// && ! name.startsWith("java/util/KeyValueHolder")
		// && ! name.startsWith("java/util/Properties")
		// && ! name.startsWith("java/util/DualPivotQuicksort")
		// && ! name.startsWith("java/util/AbstractSequentialList")
		// && ! name.startsWith("java/util/Enumeration")
		// && ! name.startsWith("java/util/Locale")
		// && ! name.startsWith("java/util/EnumSet")
		// && ! name.startsWith("java/util/EnumMap")
		// // && ! name.startsWith("java/util/Optional") //muss bleiben
		// // && ! name.startsWith("java/util/Objects") //muss bleiben
		// && ! name.startsWith("java/util/Dictionary")
		// && ! name.startsWith("java/util/Formattable")
		//
		//
		//
		// && ! name.startsWith("java/util/ResourceBundle")
		// // && ! name.startsWith("java/util/HashSet") //muss bleiben
		// && ! name.startsWith("java/util/Comparator")
		//
		// && ! name.startsWith("java/util/RandomAccess")
		// && ! name.startsWith("java/util/AbstractList")
		// && ! name.startsWith("java/util/Deque")
		// && ! name.startsWith("java/util/Queue")
		// // && ! name.startsWith("java/util/AbstractCollection") //muss bleiben
		// // && ! name.startsWith("java/util/ListIterator")
		// )
		// {
		//
		//
		// if( name.startsWith("java/util") )
		// {
		//
		// System.out.println(skipJavaUtil + " " + name);
		//
		// return false;
		// }
		// }
		//
		// && ! name.equals("java/util/LinkedList")
		/*
		 * Performance f�hrt wahrscheinlich dazu das das schreiben sehr langsam wird ||
		 * 
		 * 
		 * 
		 * if( name.startsWith("com/sun") || name.startsWith("sun") ) {
		 * 
		 * return false;
		 * 
		 * }
		 */

		/**
		 * || name.startsWith("java/util/concurrent" ) AtomicInteger sollte getraced
		 * werden Queue usw nicht
		 * 
		 * 
		 * if( name.startsWith("java/util/concurrent") && !
		 * name.startsWith("java/util/concurrent/atomic")) { return false; }
		 * 
		 */

		if (name.startsWith("java/")) {

			if (isTraceableJavaPackage(name)) {
				return true;
			}

			if ((skipJavaUtil && isUtilPackage(name)) || !isUtilPackage(name)) {
				return false;
			}

		}

		return true;

	}

	/**
	 * hiervon wird alls getraced auch methodcalls
	 * 
	 * @param className
	 * @return
	 */

	public static boolean isTraceableJavaPackage(String className) {
		return className.startsWith("java/applet") || className.startsWith("java/awt")
				|| className.startsWith("java/rmi") || className.startsWith("java/sql")
				|| className.startsWith("java/beans") || className.startsWith("java/math")
				|| className.startsWith("java/text") || className.startsWith("java/net")
				|| className.startsWith("java/security") || className.startsWith("java/nio")
				|| className.startsWith("java/io") || className.startsWith("java/lang/Class")
				|| className.startsWith("java/lang/Package") || className.startsWith("java/lang/reflect")
				|| className.startsWith("java/lang/invoke") || className.startsWith("sun/nio");
	}

	

	
	@Override
	public byte[] transform(ClassLoader loader, String name, Class<?> cl, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {

		try {
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;

			// System.out.println("xx " + name);

			if (loader != null && loader.equals(this.getClass().getClassLoader())) {
				//AgentLogCallback.log("not transformed " + name);
				return null;
			}

			
			/*
			 * EXCEPTION:java.lang.NullPointerException
			at com.anarsoft.trace.agent.runtime.AgentClassFileTransformer.shouldBeTransformed(AgentClassFileTransformer.java:102)
			at com.anarsoft.trace.agent.runtime.AgentClassFileTransformer.transform(AgentClassFileTransformer.java:557)
			at sun.instrument.TransformerManager.transform(TransformerManager.java:188)
			at sun.instrument.InstrumentationImpl.transform(InstrumentationImpl.java:428)
			at sun.misc.Unsafe.defineAnonymousClass(Native Method)
			at java.lang.invoke.InvokerBytecodeGenerator.loadAndInitializeInvokerClass(InvokerBytecodeGenerator.java:284)
			at java.lang.invoke.InvokerBytecodeGenerator.loadMethod(InvokerBytecodeGenerator.java:276)
			at java.lang.invoke.InvokerBytecodeGenerator.generateCustomizedCode(InvokerBytecodeGenerator.java:618)
			at java.lang.invoke.LambdaForm.compileToBytecode(LambdaForm.java:654)
			at java.lang.invoke.DirectMethodHandle.makePreparedLambdaForm(DirectMethodHandle.java:247)
			at java.lang.invoke.DirectMethodHandle.preparedL
			 */
			
			
			
			if( name == null )
			{
				return null;
			}
			
			
//			if( ! name.startsWith("com.vmlens.test") )  
//			{
//				return null;
//			}
			
				
			
			
			//AgentLogCallback.log("transformed " + name);

			
			
			if (!shouldBeTransformed(skipJavaUtil, name)) {
				return null;
			}

			ClassReader classReader = new ClassReader(classfileBuffer); // | ClassWriter.COMPUTE_FRAMES
																		// ClassWriter.COMPUTE_MAXS
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS); // //

			if (name.equals("java/lang/Thread")) {
				ClassVisitorCreateDesc classVisitorCreateDesc = new ClassVisitorCreateDesc(name, filterList);
				classReader.accept(classVisitorCreateDesc, 0);

				ClassVisitor classVisitor = new ClassTransformerTraceClassThread(cw, name, filterList, callBackStrings,
						classVisitorCreateDesc, writeClassDescription);

				classReader.accept(classVisitor, 0);
				return cw.toByteArray();
			}

			if (name.equals("com/vmlens/api/AgentLog")) {
				ClassVisitor classVisitor = new ClassTransformerTraceVmlensApi(cw);
				classReader.accept(classVisitor, 0);
				return cw.toByteArray();
			}

			if (name.equals("com/vmlens/api/Agent")) {
				ClassVisitor classVisitor = new ClassTransformerTraceVmlensApi(cw);
				classReader.accept(classVisitor, 0);
				return cw.toByteArray();
			}

			if (name.equals("com/vmlens/api/AllInterleavings")) {
				ClassVisitor classVisitor = new ClassTransformerTraceVmlensApi(cw);
				classReader.accept(classVisitor, 0);
				return cw.toByteArray();
			}

			// || || name.startsWith("sun")

			if (doNotTraceIn(name)) {

				ClassVisitorCreateDesc classVisitorCreateDesc = new ClassVisitorCreateDesc(name, filterList);

				classReader.accept(classVisitorCreateDesc, 0);

				ClassVisitor classVisitor = new ClassTransformerTraceClassLoader(cw, name, filterList, callBackStrings,
						classVisitorCreateDesc, writeClassDescription);
				classReader.accept(classVisitor, 0);

				return cw.toByteArray();
			}

			if (name.startsWith("java/util/concurrent/")) {

				if (!transformConcurrent(classReader, cw, name, filterList)) {
					return null;
				}

			} else {

				transform(classReader, cw, name, filterList);
			}

			
//			if(name.endsWith("WorkQueue"))
//			{
//				logTransformedClass(name, cw.toByteArray() );
//			}
			
			
			return cw.toByteArray();

		} 
		catch(Throwable e){
			AgentLogCallback.logException(e);
			return null;
		}
		finally {
			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;

		}

	}

	private boolean transformConcurrent(ClassReader classReader, ClassWriter cw, String name, FilterList filterList2) {



		if(name.startsWith("java/util/concurrent/locks/AbstractQueuedSynchronizer$ConditionObject"))
		{
			transform(classReader, cw, name, filterList);
			return true;
		}
		
		
		if (name.startsWith("java/util/concurrent/locks/AbstractQueuedSynchronizer")) {

			THashMap<MethodIdentifier, FactoryMethodTransformer> methodName2Transformer = new THashMap<MethodIdentifier, FactoryMethodTransformer>();

			// String name, String desc
			methodName2Transformer.put(new MethodIdentifier("acquire", "(I)V"),
					new FactoryMethodTransformerMethodExit(callBackStrings.callback_class_lock_statement, "acquire"));

			methodName2Transformer.put(new MethodIdentifier("acquireInterruptibly", "(I)V"),
					new FactoryMethodTransformerMethodExit(callBackStrings.callback_class_lock_statement, "acquire"));

			methodName2Transformer.put(new MethodIdentifier("acquireShared", "(I)V"),
					new FactoryMethodTransformerMethodExit(callBackStrings.callback_class_lock_statement,
							"acquireShared"));

			methodName2Transformer.put(new MethodIdentifier("tryAcquireSharedNanos", "(IJ)Z"),
					new FactoryMethodTransformerMethodExitWithReturnBoolean(
							callBackStrings.callback_class_lock_statement, "tryAcquireSharedBoolean"));

			methodName2Transformer.put(
					new MethodIdentifier("acquireQueued",
							"(Ljava/util/concurrent/locks/AbstractQueuedSynchronizer$Node;I)Z"),
					new FactoryMethodTransformerMethodExit(callBackStrings.callback_class_lock_statement,
							"acquireQueued"));

			methodName2Transformer.put(new MethodIdentifier("acquireSharedInterruptibly", "(I)V"),
					new FactoryMethodTransformerMethodExit(callBackStrings.callback_class_lock_statement,
							"acquireShared"));

			methodName2Transformer.put(new MethodIdentifier("release", "(I)Z"),
					new FactoryMethodTransformerMethodEnterNoArg(callBackStrings.callback_class_lock_statement,
							"release"));

			methodName2Transformer.put(
					new MethodIdentifier("fullyRelease",
							"(Ljava/util/concurrent/locks/AbstractQueuedSynchronizer$Node;)I"),
					new FactoryMethodTransformerMethodEnterNoArg(callBackStrings.callback_class_lock_statement,
							"fullyRelease"));

			methodName2Transformer.put(new MethodIdentifier("releaseShared", "(I)Z"),
					new FactoryMethodTransformerMethodEnterNoArg(callBackStrings.callback_class_lock_statement,
							"releaseShared"));

		
			transformWithClassTransformerTraceMethod(methodName2Transformer, classReader, cw, name, filterList);
			return true;
		}

		if (name.startsWith("java/util/concurrent/locks/AbstractOwnableSynchronizer")) {
			THashMap<MethodIdentifier, FactoryMethodTransformer> methodName2Transformer = new THashMap<MethodIdentifier, FactoryMethodTransformer>();

			// String name, String desc
			methodName2Transformer.put(new MethodIdentifier("setExclusiveOwnerThread", "(Ljava/lang/Thread;)V"),
					new FactoryMethodTransformerMethodEnterObjectArg(callBackStrings.callback_class_lock_statement,
							"setExclusiveOwnerThread"));

			transformWithClassTransformerTraceMethod(methodName2Transformer, classReader, cw, name, filterList);
			return true;
		}

		// java/util/concurrent/locks/ReentrantReadWriteLock$Sync

		if (name.startsWith("java/util/concurrent/locks/ReentrantReadWriteLock$Sync")) {
			THashMap<MethodIdentifier, FactoryMethodTransformer> methodName2Transformer = new THashMap<MethodIdentifier, FactoryMethodTransformer>();

			// String name, String desc
			methodName2Transformer.put(new MethodIdentifier("tryAcquireShared", "(I)I"),
					new FactoryMethodTransformerMethodExitWithReturnInt(callBackStrings.callback_class_lock_statement,
							"tryAcquireShared"));

			methodName2Transformer.put(new MethodIdentifier("tryReleaseShared", "(I)Z"),
					new FactoryMethodTransformerMethodExitWithReturnBoolean(
							callBackStrings.callback_class_lock_statement, "tryReleaseShared"));

			transformWithClassTransformerTraceMethod(methodName2Transformer, classReader, cw, name, filterList);
			return true;
		}

		if (name.startsWith("java/util/concurrent/locks/ReentrantLock$Sync")) {
			THashMap<MethodIdentifier, FactoryMethodTransformer> methodName2Transformer = new THashMap<MethodIdentifier, FactoryMethodTransformer>();

			// String name, String desc
			methodName2Transformer.put(new MethodIdentifier("nonfairTryAcquire", "(I)Z"),
					new FactoryMethodTransformerMethodExitWithReturnBoolean(
							callBackStrings.callback_class_lock_statement, "tryAcquire"));

			transformWithClassTransformerTraceMethod(methodName2Transformer, classReader, cw, name, filterList);
			return true;
		}

		/**
		 * betreffend den /java/util/concurrent collections:
		 *
		 *
		 * https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ArrayBlockingQueue.html
		 * basiert auf locks java/util/concurrent/ConcurrentHashMap basiert auf locks
		 *
		 * https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ConcurrentLinkedDeque.html
		 * auf volatile und cas operationen java/util/concurrent/ConcurrentLinkedQueue
		 * auf volatile und cas operationen java.util.concurrent.ConcurrentSkipListMap
		 * auf volatile ohne locks ConcurrentSkipListSet<E> basiert auf
		 * ConcurrentSkipListMap CopyOnWriteArrayList<E> volatile CopyOnWriteArraySet<E>
		 * volatile
		 *
		 *
		 * LinkedBlockingDeque<E> basiert auf locks LinkedBlockingQueue<E> basiert auf
		 * locks LinkedTransferQueue<E> basiert auf cas und unsafe
		 * PriorityBlockingQueue<E> basiert auf locks SynchronousQueue<E> basiert auf
		 * java.util.concurrent.atomic.AtomicReferenceFieldUpdater
		 * 
		 */

		// if( name.startsWith("java/util/concurrent/ConcurrentLinkedDeque") ||
		// name.startsWith("java/util/concurrent/ConcurrentLinkedQueue") ||
		// name.startsWith("java/util/concurrent/ConcurrentSkipListMap") ||
		// name.startsWith("java/util/concurrent/CopyOnWriteArrayList") ||
		// name.startsWith("java/util/concurrent/CopyOnWriteArraySet") ||
		// name.startsWith("java/util/concurrent/LinkedTransferQueue") ||
		// name.startsWith("java/util/concurrent/SynchronousQueue") )
		// {
		// transform( classReader , cw , name , filterList);
		// return true;
		// }

		// if( name.equals("java/util/concurrent/atomic/AtomicBoolean") ||
		// name.equals("java/util/concurrent/atomic/AtomicInteger") ||
		// name.equals("java/util/concurrent/atomic/AtomicLong") ||
		// name.equals("java/util/concurrent/atomic/AtomicReference") )
		// {
		//
		// transformAtomicClasses( classReader , cw , name , filterList);
		// return true;
		// }

		transform(classReader, cw, name, filterList);
		return true;

		// return false;

	}

	private void transformWithClassTransformerTraceMethod(
			THashMap<MethodIdentifier, FactoryMethodTransformer> methodName2Transformer, ClassReader classReader,
			ClassWriter cw, String name, FilterList filterList2) {

		ClassTransformerTraceMethod classTransformerTraceMethod = new ClassTransformerTraceMethod(cw,
				methodName2Transformer);

		classReader.accept(classTransformerTraceMethod, 0);

	}

	// protected abstract boolean isTransformable(Class<?> cl);

	protected void transform(ClassReader classReader, ClassWriter outputClassWriter, String name,
			FilterList filterList) {

		ClassVisitorCreateDesc classVisitorCreateDesc = new ClassVisitorCreateDesc(name, filterList);

		classReader.accept(classVisitorCreateDesc, ClassReader.EXPAND_FRAMES);

		ClassVisitor classTransformer = createClassTransformer(outputClassWriter, name, filterList,
				classVisitorCreateDesc); // new ClassTransformerForRetransform(outputClassWriter,name,filterList ,
											// classAnalyzedEventList ,callBackStrings,methodIdentifierSet);
		classReader.accept(classTransformer, 0);

	}

	public ClassVisitor createClassTransformer(ClassWriter outputClassWriter, String name, FilterList filterList,
			ClassVisitorCreateDesc classVisitorCreateDesc) {

		return new ClassTransformer(outputClassWriter, name, filterList, callBackStrings, classVisitorCreateDesc,
				writeClassDescription, addInterface && classVisitorCreateDesc.callbackMethodNotGenerated,
				hasGeneratedMethods);
	}

	// byte[] classfileBuffer,
	public static void logTransformedClass(String className, byte[] transformedArray) {

		String logDir = "";

		try {
			String fileName = className.substring(className.lastIndexOf("/") + 1);

			// OutputStream out = new FileOutputStream( logDir + fileName + ".class");
			//
			// out.write(classfileBuffer);
			//
			// out.close();

			OutputStream outTransformed = new FileOutputStream(logDir + fileName + "_trans.class");

			outTransformed.write(transformedArray);

			outTransformed.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}

	}

	public static void main(String[] args) {
		System.out.println(shouldBeTransformed(false, "java/util/Arrays"));
	}

}
