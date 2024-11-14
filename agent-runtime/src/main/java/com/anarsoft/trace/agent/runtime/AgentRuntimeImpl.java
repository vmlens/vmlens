package com.anarsoft.trace.agent.runtime;


import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.runtime.process.AgentController;
import com.anarsoft.trace.agent.runtime.repositorydeprecated.LoadAtomicClassesFromClasspath;
import com.anarsoft.trace.agent.runtime.util.AgentKeys;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionDuringStartup;
import com.anarsoft.trace.agent.runtime.write.WriteEventToFile;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.set.hash.THashSet;
import com.vmlens.trace.agent.bootstrap.AgentRuntime;
import com.vmlens.trace.agent.bootstrap.Offset2FieldId;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ParallelizeBridgeForCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.AgentLogCallback;

import java.io.File;
import java.io.FileInputStream;
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

			File dir = new File(outputFileName);

			for (File file : dir.listFiles()) {
				if (file.getName().endsWith(".vmlens")) {
					file.delete();
				}
			}

			LoadAtomicClassesFromClasspath.load();
			Offset2FieldId.initialize();

            String enableAgentLogException = properties.getProperty(AgentKeys.AGENT_LOG_EXCEPTION);

            if (enableAgentLogException != null && enableAgentLogException.trim().toLowerCase().equals("true")) {
                AgentLogCallback.ENABLE_EXCEPTION_LOGGING = true;
            }

            AgentController agentController = AgentController.create(outputFileName);

            new LoadClassesAtStart().loadClasses();

            instrument(inst, outputFileName);
            WriteEventToFile.startWriteEventToFileThread(outputFileName, agentController);

        } catch (Throwable e) {
			e.printStackTrace();
		}
	}

    private void retransform(Instrumentation inst,
                             TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList, boolean skipJavaUtil,
                             THashSet<String> alreadyTransformed) throws UnmodifiableClassException {
        WriteClassDescriptionDuringStartup writeClassDescriptionDuringStartup = new WriteClassDescriptionDuringStartup(
                classAnalyzedEventList);

     /*   AgentClassFileTransformer classRetransformer = new AgentClassFileTransformer(
				writeClassDescriptionDuringStartup, new HasGeneratedMethodsAlwaysFalse(),
				ApplyClassTransformerCollectionFactory.retransform());

        inst.addTransformer(classRetransformer, true);
*/
		TLinkedList<TLinkableWrapper<Class>> transformableClasses = new TLinkedList();
		for (Class cl : inst.getAllLoadedClasses()) {
			if (inst.isModifiableClass(cl)) {
				String correctedClassName = cl.getName().replace('.', '/');
				if (!cl.isInterface()) {
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
			if (cl.getElement().getName().startsWith("java/lang")) {
				System.out.println(cl.getElement().getName());
			}
			toBeRetransformed[i] = ((Class) cl.getElement());
			i++;
		}
		if (toBeRetransformed.length > 0) {
			inst.retransformClasses(toBeRetransformed);
		}
        //	inst.removeTransformer(classRetransformer);
	}

    protected void instrument(Instrumentation inst, String outputFileName) throws Exception {
        TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList = new TLinkedList();
        THashSet<String> alreadyTransformed = new THashSet();

        retransform(inst, classAnalyzedEventList, true, alreadyTransformed);
        for (final TLinkableWrapper<ClassDescription> classAnalyzedEvent : classAnalyzedEventList) {

			ParallelizeBridgeForCallbackImpl.eventQueue.offer(classAnalyzedEvent.getElement());

        }
        classAnalyzedEventList = new TLinkedList();

        retransform(inst, classAnalyzedEventList, false, alreadyTransformed);
		for (final TLinkableWrapper<ClassDescription> classAnalyzedEvent : classAnalyzedEventList) {

			ParallelizeBridgeForCallbackImpl.eventQueue.offer(classAnalyzedEvent.getElement());
		}
	/*	inst.addTransformer(new AgentClassFileTransformer(
						new WriteClassDescriptionNormal(), new HasGeneratedMethodsSetBased(), ApplyClassTransformerCollectionFactory.retransform()),
				false);
	*/
	}

}
