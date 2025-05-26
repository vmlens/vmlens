package com.anarsoft.trace.agent.runtime;


import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.ClassFilterAndTransformerStrategyCollectionFactory;
import com.anarsoft.trace.agent.runtime.write.*;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.set.hash.THashSet;
import com.vmlens.trace.agent.bootstrap.AgentRuntime;
import com.vmlens.trace.agent.bootstrap.event.queue.EventQueueSingleton;
import com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEvent;
import com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEventBuilder;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import java.io.File;
import java.io.FileInputStream;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.Properties;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.startProcess;
import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.stopProcess;

public class AgentRuntimeImpl implements AgentRuntime {


    public void run(String args, Instrumentation inst) {
        try {

            File agentFile = new File(
                    AgentRuntimeImpl.class.getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI()
                            .getPath());
            String libPath = agentFile.toString().substring(0,
                    agentFile.toString().length() - "/agent_runtime.jar".length());
            String inputFileName = libPath + "/run.properties";


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

            new LoadClassesAtStart().loadClasses();

            TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList = new TLinkedList<>();
            TLinkedList<TLinkableWrapper<InfoMessageEvent>> infoMessageEventList = new TLinkedList<>();

            WriteClassDescriptionAndWarningDuringStartup writeClassDescriptionDuringStartup =
                    new WriteClassDescriptionAndWarningDuringStartup(
                            classAnalyzedEventList, infoMessageEventList);

            WriteClassDescriptionAndWarningWrapper writeClassDescriptionAndWarningWrapper =
                    new WriteClassDescriptionAndWarningWrapper(writeClassDescriptionDuringStartup);
            AgentClassFileTransformer agentClassFileTransformer = new AgentClassFileTransformer(
                    ClassFilterAndTransformerStrategyCollectionFactory.createFactory(libPath,
                            writeClassDescriptionAndWarningWrapper),
                    writeClassDescriptionAndWarningWrapper);

            startProcess();

            instrument(inst, agentClassFileTransformer, writeClassDescriptionDuringStartup);
            writeClassDescriptionAndWarningWrapper
                    .setWriteClassDescriptionAndWarning(new WriteClassDescriptionAndWarningNormal());

            WriteEventToFile.startWriteEventToFileThread(outputFileName);

            for (TLinkableWrapper<InfoMessageEvent> infoMessageEvent : infoMessageEventList) {
                EventQueueSingleton.eventQueue.offer(infoMessageEvent.element());
            }
            for (TLinkableWrapper<ClassDescription> classDescription : classAnalyzedEventList) {
                EventQueueSingleton.eventQueue.offer(classDescription.element());
            }

            stopProcess();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void retransform(Instrumentation inst,
                             THashSet<String> alreadyTransformed,
                             WriteClassDescriptionAndWarning writeClassDescriptionAndWarning) throws UnmodifiableClassException {
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
            } else {
              //  InfoMessageEventBuilder ordertree = new InfoMessageEventBuilder();
              //  ordertree.add("not transformable:" + cl.getName());
              //  writeClassDescriptionAndWarning.write(ordertree.build());
            }
        }
        Class[] toBeRetransformed = new Class[transformableClasses.size()];
        int i = 0;
        for (TLinkableWrapper<Class> cl : transformableClasses) {
            InfoMessageEventBuilder builder = new InfoMessageEventBuilder();
            builder.add(cl.element().getName());
            writeClassDescriptionAndWarning.write(builder.build());

            toBeRetransformed[i] = cl.element();
            i++;
        }
        if (toBeRetransformed.length > 0) {
             inst.retransformClasses(toBeRetransformed);
        }

    }

    protected void instrument(Instrumentation inst,
                              AgentClassFileTransformer agentClassFileTransformer,
                              WriteClassDescriptionAndWarningDuringStartup writeClassDescriptionDuringStartup) throws Exception {
        inst.addTransformer(agentClassFileTransformer, true);
        THashSet<String> alreadyTransformed = new THashSet<>();
        retransform(inst, alreadyTransformed, writeClassDescriptionDuringStartup);
    }
}
