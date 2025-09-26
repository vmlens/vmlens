package com.vmlens.nottraced.agent;


import com.vmlens.agent.AgentRuntime;
import com.vmlens.nottraced.agent.applyclasstransformer.ClassFilterAndTransformerStrategyCollectionFactory;
import com.vmlens.nottraced.agent.write.*;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.set.hash.THashSet;
import com.vmlens.transformed.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl;
import com.vmlens.transformed.agent.bootstrap.description.ClassDescription;
import com.vmlens.transformed.agent.bootstrap.event.queue.EventQueueSingleton;
import com.vmlens.transformed.agent.bootstrap.event.warning.InfoMessageEvent;
import com.vmlens.transformed.agent.bootstrap.event.warning.InfoMessageEventBuilder;
import com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

import static com.vmlens.transformed.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.decrementInsideVMLens;
import static com.vmlens.transformed.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.incrementInsideVMLens;

public class AgentRuntimeImpl implements AgentRuntime {

    private static final String EVENT_DIRECTORY = "/vmlens/";

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

            String outputFileName = libPath + EVENT_DIRECTORY;

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

            String filterFileName =  libPath + "/filter.txt";
            ClassFilterFromFile classFilterFromFile = ClassFilterFromFile.create(filterFileName);


            new LoadClassesAtStart().loadClasses();
            /*
             *    Exception in thread "surefire-forkedjvm-command-thread" java.lang.NullPointerException: Cannot invoke "com.vmlens.trace.agent.bootstrap.callback.callbackaction.CheckIsThreadRun.isThreadRun()" because "this.checkIsThreadRun" is null
	         *       at com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl.process(CallbackActionProcessorImpl.java:97)
	         *       at com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionProcessorImpl.processWithCheckNewThread(CallbackActionProcessorImpl.java:75)
	         *       at com.vmlens.trace.agent.bootstrap.callback.MethodCallback.methodEnter(MethodCallback.java:37)
	         *       at org.apache.maven.surefire.booter.CommandReader$CommandRunnable.run(CommandReader.java)
	         *       at java.base/java.lang.Thread.run(Thread.java:1447)
             */
            CallbackActionProcessorImpl callbackActionProcessorImpl = new CallbackActionProcessorImpl();

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
                    writeClassDescriptionAndWarningWrapper,classFilterFromFile);

            incrementInsideVMLens();

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

            decrementInsideVMLens();
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
        agentClassFileTransformer.isInTransform();
    }
}
