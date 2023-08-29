package com.anarsoft.trace.agent.runtime;


import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethodsAlwaysFalse;
import com.anarsoft.trace.agent.runtime.filter.HasGeneratedMethodsSetBased;
import com.anarsoft.trace.agent.runtime.process.AgentController;
import com.anarsoft.trace.agent.runtime.util.AgentKeys;
import com.anarsoft.trace.agent.runtime.repository.LoadAtomicClassesFromClasspath;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionDuringStartup;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionNormal;
import com.anarsoft.trace.agent.runtime.write.WriteEvent2File;
import com.anarsoft.trace.agent.runtime.write.WriteEvent2FileThreadFactory;
import com.anarsoft.trace.agent.serialization.ClassDescription;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.set.hash.THashSet;
import com.vmlens.trace.agent.bootstrap.AgentRuntime;
import com.vmlens.trace.agent.bootstrap.Offset2FieldId;
import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

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


			LoadAtomicClassesFromClasspath.load();
			Offset2FieldId.initialize();

			String enableAgentLogException = properties.getProperty(AgentKeys.AGENT_LOG_EXCEPTION);


			if (enableAgentLogException != null && enableAgentLogException.trim().toLowerCase().equals("true")) {
				AgentLogCallback.ENABLE_EXCEPTION_LOGGING = true;
			}
			

			CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;

			AgentController agentController = AgentController.create(outputFileName);

			
			this.getClass().getClassLoader().loadClass("com.vmlens.trace.agent.bootstrap.callback.SendEventDoNotSend");
			this.getClass().getClassLoader().loadClass("com.vmlens.trace.agent.bootstrap.callback.getState.Class2GetStateMap");
			
			this.getClass().getClassLoader().loadClass("com.vmlens.trace.agent.bootstrap.callback.getState.CreateGetState");
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
			this.getClass().getClassLoader().loadClass("org.objectweb.asm.ConstantDynamic");
			this.getClass().getClassLoader()
			.loadClass("com.vmlens.trace.agent.bootstrap.callback.StackTraceBasedFilterCallback");
			this.getClass().getClassLoader().loadClass("java.util.concurrent.locks.LockSupport");

            instrument(inst, outputFileName);

			CallbackState.queueFacade.start(
					new WriteEvent2File(outputFileName, new StreamRepository(outputFileName, 0), agentController),
					new WriteEvent2FileThreadFactory());

		} catch (Throwable e) {
			e.printStackTrace();
		}

		CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace--;

		CallbackState.slidingWindow = 1;

	}

    private void retransform(Instrumentation inst,
                             TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList, boolean skipJavaUtil,
                             THashSet<String> alreadyTransformed) throws UnmodifiableClassException {
        WriteClassDescriptionDuringStartup writeClassDescriptionDuringStartup = new WriteClassDescriptionDuringStartup(
                classAnalyzedEventList);

        AgentClassFileTransformer classRetransformer = new AgentClassFileTransformer(
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

    protected void instrument(Instrumentation inst, String outputFileName) throws Exception {
        TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList = new TLinkedList();
        THashSet<String> alreadyTransformed = new THashSet();

        retransform(inst, classAnalyzedEventList, true, alreadyTransformed);
        for (final TLinkableWrapper<ClassDescription> classAnalyzedEvent : classAnalyzedEventList) {

            CallbackState.queueFacade.putDirect(classAnalyzedEvent.getElement());

        }
        classAnalyzedEventList = new TLinkedList();

        retransform(inst, classAnalyzedEventList, false, alreadyTransformed);
		for (final TLinkableWrapper<ClassDescription> classAnalyzedEvent : classAnalyzedEventList) {

			CallbackState.queueFacade.putDirect(classAnalyzedEvent.getElement());
		}
        inst.addTransformer(new AgentClassFileTransformer(false,
                new WriteClassDescriptionNormal(), true, new HasGeneratedMethodsSetBased()), false);
		

	}
}
