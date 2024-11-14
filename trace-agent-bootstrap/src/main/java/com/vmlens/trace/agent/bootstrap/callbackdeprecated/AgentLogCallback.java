package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ParallelizeBridgeForCallbackImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

import java.io.PrintWriter;
import java.io.StringWriter;

import static com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade.parallelize;

public class AgentLogCallback {

    public static volatile boolean ENABLE_EXCEPTION_LOGGING = false;

    private static volatile AgentApiCallbackImpl agentApiCallbackImpl = new AgentApiCallbackImpl();

    public static void setAgentApiCallbackImpl(AgentApiCallbackImpl agentApiCallbackImpl) {
        AgentLogCallback.agentApiCallbackImpl = agentApiCallbackImpl;
    }

    public static void logException(Throwable exception) {
        if (ENABLE_EXCEPTION_LOGGING) {

            ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            exception.printStackTrace(writer);
            // Fixme
            //  callbackStatePerThread.offer(new AgentLogEvent("EXCEPTION:" + stringWriter.toString()));
        }
    }
	
	public static void logError(String message)
	{
		if(ENABLE_EXCEPTION_LOGGING) {

            ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter);
            new Exception().printStackTrace(writer);
            // Fixme
            //  callbackStatePerThread.offer(new AgentLogEvent("EXCEPTION:" + message + "  " + stringWriter.toString()));


        }
	}
	

	public static void log(Class location, String text) {
        String message = location.getSimpleName() + ":" + text;

        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();
        // Fixme
        //  callbackStatePerThread.offer(new AgentLogEvent(message));

    }

    public static void close(Object obj) {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();
        parallelize().close(callbackStatePerThread, obj);
    }

    public static boolean hasNext(Object obj) {
        return agentApiCallbackImpl.hasNext(obj);
    }

}
