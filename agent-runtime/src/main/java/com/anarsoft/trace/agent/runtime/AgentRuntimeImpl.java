package com.anarsoft.trace.agent.runtime;

import com.anarsoft.trace.agent.runtime.filter.FilterBuilder;
import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethodsAlwaysFalse;
import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethodsSetBased;
import com.anarsoft.trace.agent.runtime.process.AgentController;
import com.anarsoft.trace.agent.runtime.util.AgentKeys;
import com.anarsoft.trace.agent.runtime.waitPoints.FilterList;
import com.anarsoft.trace.agent.runtime.waitPoints.LoadAtomicClassesFromClasspath;
import com.anarsoft.trace.agent.serialization.ClassDescription;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.set.hash.THashSet;
import com.vmlens.trace.agent.bootstrap.AgentRuntime;
import com.vmlens.trace.agent.bootstrap.Offset2FieldId;
import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.mode.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.Properties;

public class AgentRuntimeImpl implements AgentRuntime {

	private void deleteFile(String fileName) {
		File finished = new File(fileName);
		if (finished.exists()) {
			finished.delete();
		}
	}

	public void run(String args, Instrumentation inst) {
		try {
			String inputFileName = args;
			if ((args == null) || (args.trim().equals("")) || (args.trim().startsWith("startManually"))) {
				File agentFile = new File(
						AgentRuntimeImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
				String libPath = agentFile.toString().substring(0,
						agentFile.toString().length() - "/agent_runtime.jar".length());
				inputFileName = libPath + "/run.properties";
			}
			// else if( ! args.contains("/") && ! args.contains("\\") )
			// {
			// File agentFile = new
			// File(AgentRuntimeImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			// String libPath = agentFile.toString().substring(0,
			// agentFile.toString().length() - "/agent_runtime.jar".length());
			// inputFileName = libPath + args;
			// }

			if (!new File(inputFileName).exists()) {
				File agentFile = new File(
						AgentRuntimeImpl.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
				String libPath = agentFile.toString().substring(0,
						agentFile.toString().length() - "/agent_runtime.jar".length());
				String correctedName = libPath + "/" + args;

				if (new File(correctedName).exists()) {
					inputFileName = correctedName;
				}

			}

			Properties properties = new Properties();

			properties.load(new FileInputStream(inputFileName));

			String outputFileName = properties.getProperty("eventDir");
			if (outputFileName == null) {
				throw new RuntimeException("eventDir is missing in vmlens agent properties");
			}

			File outputDir = new File(outputFileName);

			System.err.println("writing events to " + outputDir.getAbsolutePath());

			if (!outputDir.exists()) {
				outputDir.mkdirs();
			}

			boolean startManually = Boolean.parseBoolean(properties.getProperty("vmlens.startManually", "false"));

			if ((args != null) && (args.trim().startsWith("startManually"))) {
				startManually = true;
			}

			// System.err.println("startManually " +startManually);

			/*
			 * val START_FILE = "/start"; val STOP_FILE = "/stop"; val FINISHED_FILE =
			 * "/finished"; val SHUTDOWN_FILE = "/shutdown";
			 */

			deleteFile(outputFileName + "/start");
			deleteFile(outputFileName + "/stop");
			deleteFile(outputFileName + "/finished");
			deleteFile(outputFileName + "/shutdown");

			File dir = new File(outputFileName);

			for (File file : dir.listFiles()) {
				if (file.getName().endsWith(".vmlens")) {
					file.delete();
				} else if (file.getName().endsWith(".state")) {
					file.delete();
				}

			}

			File fileOut = new File(outputDir.getAbsolutePath() + "/properties.vmlens");
			PrintStream out = new PrintStream(fileOut);

			String agentModeString = properties.getProperty("vmlens.mode");

			AgentMode mode = new AgentModeInterleave();

			if (ModeNames.MONITOR.equals(agentModeString)) {
				mode = new AgentModeMonitor();
			} else if (ModeNames.STATE.equals(agentModeString)) {
				mode = new AgentModeState();
			}

			CallbackState.mode = mode;

			String prop = properties.getProperty("vmlens.trace");

			if (prop == null) {
				out.println("vmlens.trace=");
			} else {
				out.println("vmlens.trace=" + prop);
			}

			out.println("vmlens.mode=" + mode.asPropertyString());

			// funktioniert nicht für jdk 6
			// properties.save( out , "" );
			out.close();

			LoadAtomicClassesFromClasspath.load();

			Offset2FieldId.initialize();

			/*
			 * vmlens.excludeFromTrace vmlens.doNotTraceIn vmlens.onlyTraceIn
			 */
			// FilterState excludeFromTrace, FilterState doNotTraceIn, FilterState
			// onlyTraceIn

			// FilterList(FilterState excludeFromTrace, FilterState
			// excludeThroughDoNotTraceIn, FilterState doNotTraceIn,
			// FilterState onlyTraceIn, WaitPointMap schedulingInfoClassMap)

			FilterList filterList = new FilterList(
					FilterBuilder.createExcludeFilter(properties.getProperty(AgentKeys.EXCLUDE_FROM_TRACE)),
					FilterBuilder.createExcludeFilter(properties.getProperty(AgentKeys.DO_NOT_TRACE_IN)),
					FilterBuilder.createOnlyWhenSetFilter(properties.getProperty(AgentKeys.DO_NOT_TRACE_IN)),
					FilterBuilder.createOnlyWhenSetFilter(properties.getProperty(AgentKeys.TRACE)), mode);

			// LinkedNode<EventSink<Event,Object>> serializeEventNode = new
			// LinkedNode<EventSink<Event,Object>>(new WriteEvent2File(outputFileName) );

			String enableAgentLog = properties.getProperty(AgentKeys.AGENT_LOG);
			String enableAgentLogPerformance = properties.getProperty(AgentKeys.AGENT_LOG_PERFORMANCE);
			String enableAgentLogException = properties.getProperty(AgentKeys.AGENT_LOG_EXCEPTION);


			if (enableAgentLogException != null && enableAgentLogException.trim().toLowerCase().equals("true")) {
				AgentLogCallback.ENABLE_EXCEPTION_LOGGING = true;
			}
			

//			String maximumRunCount = properties.getProperty(AgentKeys.MAXIMUM_RUN_COUNT);
//
//			if (maximumRunCount != null) {
//				try {
//					ParallizeFacade.MAX_RUN_COUNT = Integer.parseInt(maximumRunCount);
//				} catch (NumberFormatException e) {
//
//				}
//
//			}
//
//			String maximumOperationCount = properties.getProperty(AgentKeys.MAXIMUM_OPERATION_COUNT);
//
//			if (maximumOperationCount != null) {
//				try {
//					ParallizeFacade.MAX_OPERATION_COUNT = Integer.parseInt(maximumOperationCount);
//				} catch (NumberFormatException e) {
//
//				}
//
//			}

			// PrintEvent.setStrater(new WriteEvent2FileStarterImpl(outputFileName));

			CallbackState.doNotcheckStackTraceBasedDoTrace = !filterList.onlyTraceIn.isDefined();

			CallbackState.syncActionSameAsField4TraceCheck = mode.syncActionSameAsField4TraceCheck();

			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;

			//

			AgentController agentController = AgentController.create(outputFileName);

			// Thread.setDefaultUncaughtExceptionHandler( new
			// VmlensUncaughtExceptionHandler() );
			
			this.getClass().getClassLoader().loadClass("com.vmlens.trace.agent.bootstrap.callback.SendEventDoNotSend");
		
			

//			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.concurrent.collections.ConcurrentHashMapComputeIfAbsentOnly");
//			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.concurrent.collections.KeyValue");
//			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.concurrent.collections.FunctionForJDK7");
			
			this.getClass().getClassLoader().loadClass("com.vmlens.trace.agent.bootstrap.callback.getState.Class2GetStateMap");
			
			this.getClass().getClassLoader().loadClass("com.vmlens.trace.agent.bootstrap.callback.getState.CreateGetState");
			
			
			this.getClass().getClassLoader().loadClass("com.anarsoft.trace.agent.runtime.filter.FilterBuilder");
			this.getClass().getClassLoader().loadClass("com.anarsoft.trace.agent.runtime.filter.FilterState");
			this.getClass().getClassLoader()
					.loadClass("com.anarsoft.trace.agent.runtime.filter.FilterStateBasedOnList");
			this.getClass().getClassLoader().loadClass("com.anarsoft.trace.agent.runtime.util.AntPatternMatcher");

			this.getClass().getClassLoader().loadClass("com.vmlens.trace.agent.bootstrap.callback.FieldAccessCallback");
			this.getClass().getClassLoader()
					.loadClass("com.vmlens.trace.agent.bootstrap.callback.SynchronizedStatementCallback");
			this.getClass().getClassLoader().loadClass("com.vmlens.trace.agent.bootstrap.callback.ArrayAccessCallback");

			this.getClass().getClassLoader()
					.loadClass("com.vmlens.trace.agent.bootstrap.callback.field.CallbackObject");
			this.getClass().getClassLoader()
					.loadClass("com.vmlens.trace.agent.bootstrap.callback.field.CallbackStatic");
			this.getClass().getClassLoader().loadClass("com.vmlens.trace.agent.bootstrap.callback.field.Strategy");
			this.getClass().getClassLoader()
					.loadClass("com.vmlens.trace.agent.bootstrap.callback.field.StrategyImplNonVolatile");
			this.getClass().getClassLoader()
					.loadClass("com.vmlens.trace.agent.bootstrap.callback.field.StrategyImplVolatile");

			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.TLinkableAdapter");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.linked.TLinkedList");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.set.hash.THashSet");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.TLinkable");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.map.hash.THashMap");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.map.hash.TIntObjectHashMap");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.procedure.TIntObjectProcedure");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.set.hash.THashSet");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.map.hash.TObjectIntHashMap");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.set.hash.THashSet");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.map.hash.THashMap");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.set.hash.TIntHashSet");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.map.hash.TObjectIntHashMap");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.map.hash.TIntObjectHashMap");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.linked.TLinkedList");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.procedure.TIntObjectProcedure");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.procedure.TIntProcedure");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.set.hash.TIntHashSet");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.procedure.TIntObjectProcedure");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.TLinkable");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.linked.TLinkedList");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.list.linked.TLinkedList");
			this.getClass().getClassLoader().loadClass("com.vmlens.shaded.gnu.trove.map.hash.TObjectIntHashMap");

			// this.getClass().getClassLoader().loadClass("java.util.HashSet");

			this.getClass().getClassLoader().loadClass("org.objectweb.asm.ConstantDynamic");
			// this.getClass().getClassLoader().loadClass("org.objectweb.asm.commons.Method");
			// this.getClass().getClassLoader().loadClass("org.objectweb.asm.commons.JSRInlinerAdapter");
			// this.getClass().getClassLoader().loadClass("org.objectweb.asm.commons.AdviceAdapter");
			//

			// this.getClass().getClassLoader().loadClass("java.util.HashMap");
			
			this.getClass().getClassLoader()
			.loadClass("com.vmlens.trace.agent.bootstrap.callback.StackTraceBasedFilterCallback");

			/**
			 * Exception in thread "main" java.lang.NoClassDefFoundError: Could not
			 * initialize class java.util.concurrent.locks.LockSupport at
			 * java.util.concurrent.locks.LockSupport.parkNanos(LockSupport.java:349) at
			 * java.anarsoft.executorService.internal.manyToOne.WithBackPressure.writeOne(WithBackPressure.java:44)
			 * at
			 * java.anarsoft.executorService.internal.manyToOne.QueueManyWritersForThreadLocal.accept(QueueManyWritersForThreadLocal.java:62)
			 * at
			 * java.anarsoft.trace.agent.bootstrap.callback.SynchronizedStatementCallback.monitorExit(SynchronizedStatementCallback.java:366)
			 * at java.lang.ClassLoader.loadClass(ClassLoader.java:437) at
			 * sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:308) at
			 * java.lang.ClassLoader.loadClass(ClassLoader.java:358) at
			 * java.lang.ClassLoader.defineClass1(Native Method)
			 */

			this.getClass().getClassLoader().loadClass("java.util.concurrent.locks.LockSupport");

			instrument(inst, outputFileName, filterList);

			CallbackState.queueFacade.start(
					new WriteEvent2File(outputFileName, new StreamRepository(outputFileName, 0), agentController),
					new WriteEvent2FileThreadFactory());

		} catch (Throwable e) {
			e.printStackTrace();
		}

		CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;

		CallbackState.slidingWindow = 1;

	}

	private void retransform(Instrumentation inst, TransformConstants callBackStrings, FilterList filterList,
			TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList, boolean skipJavaUtil,
			THashSet<String> alreadyTransformed) throws UnmodifiableClassException {
		WriteClassDescriptionDuringStartup writeClassDescriptionDuringStartup = new WriteClassDescriptionDuringStartup(
				classAnalyzedEventList);

		AgentClassFileTransformer classRetransformer = new AgentClassFileTransformer(filterList, callBackStrings,
				skipJavaUtil, writeClassDescriptionDuringStartup, false, new HasGeneratedMethodsAlwaysFalse());

		inst.addTransformer(classRetransformer, true);

		TLinkedList<TLinkableWrapper<Class>> transformableClasses = new TLinkedList();
		for (Class cl : inst.getAllLoadedClasses()) {
			if (inst.isModifiableClass(cl)) {
				String correctedClassName = cl.getName().replace('.', '/');
				if ((AgentClassFileTransformer.shouldBeTransformed(skipJavaUtil, correctedClassName))
						&& (!cl.isInterface())) {
					if (!alreadyTransformed.contains(correctedClassName)) {
						transformableClasses.add(new TLinkableWrapper(cl));
						alreadyTransformed.add(correctedClassName);
					}
				}
			}
		}
		Class[] toBeRetransformed = new Class[transformableClasses.size()];
		int i = 0;
		for (TLinkableWrapper<Class> cl : transformableClasses) {
			toBeRetransformed[i] = ((Class) cl.getElement());
			i++;
		}
		if (toBeRetransformed.length > 0) {
			inst.retransformClasses(toBeRetransformed);
		}
		inst.removeTransformer(classRetransformer);
	}

	protected void instrument(Instrumentation inst, String outputFileName, FilterList filterList) throws Exception {
		TransformConstants callBackStrings = new TransformConstants(
				"com/vmlens/trace/agent/bootstrap/callback/FieldAccessCallback",
				"com/vmlens/trace/agent/bootstrap/callback/SynchronizedStatementCallback",
				"com/vmlens/trace/agent/bootstrap/callback/LockStatementCallback",
				"com/vmlens/trace/agent/bootstrap/ThreadIdForField",
				"com/vmlens/trace/agent/bootstrap/callback/MethodCallback", "_pAnarsoft_", "_pAnarsoft_set_",
				"_pAnarsoft_get_");

		TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList = new TLinkedList();

		THashSet<String> alreadyTransformed = new THashSet();
		THashSet<String> withoutOwner = new THashSet();

		retransform(inst, callBackStrings, filterList, classAnalyzedEventList, true, alreadyTransformed);

		CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();

		for (final TLinkableWrapper<ClassDescription> classAnalyzedEvent : classAnalyzedEventList) {

			CallbackState.queueFacade.putDirect(classAnalyzedEvent.getElement());

		}
		classAnalyzedEventList = new TLinkedList();

		retransform(inst, callBackStrings, filterList, classAnalyzedEventList, false, alreadyTransformed);
		for (final TLinkableWrapper<ClassDescription> classAnalyzedEvent : classAnalyzedEventList) {

			CallbackState.queueFacade.putDirect(classAnalyzedEvent.getElement());
		}
		inst.addTransformer(new AgentClassFileTransformer(filterList, callBackStrings, false,
				new WriteClassDescriptionNormal(), true, new HasGeneratedMethodsSetBased()), false);
		

	}
}
