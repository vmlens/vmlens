package com.anarsoft.trace.agent.runtime;


import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import java.util.concurrent.ThreadFactory;

public class WriteEvent2FileThreadFactory implements ThreadFactory  {

	@Override
	public Thread newThread(Runnable arg0) {
		
		Thread thread = new Thread(arg0,CallbackStatePerThread.ANARSOFT_THREAD_NAME);
		thread.setDaemon(true);
	
	    return thread;
	}

}
