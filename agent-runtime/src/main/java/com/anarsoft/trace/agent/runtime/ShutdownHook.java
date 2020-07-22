package com.anarsoft.trace.agent.runtime;


import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.anarsoft.trace.agent.runtime.process.AgentController;




public class ShutdownHook extends Thread {

	
	private final String eventDir;
    private final CleanCallbackStatePerThreadRecovery cleanCallbackStatePerThreadRecovery;
	
	public ShutdownHook(String eventDir,AgentController agentController) {

			super(CallbackStatePerThread.ANARSOFT_THREAD_NAME);
			this.eventDir = eventDir;
			this.cleanCallbackStatePerThreadRecovery = new CleanCallbackStatePerThreadRecovery(agentController);
	}
	
	
	private static boolean alreadyAdded = false;
	
	
	private void startCleanThread()
	{
		  cleanCallbackStatePerThreadRecovery.start();
	}
	
	
	public static synchronized void addShutdownHook(String eventDir,AgentController agentController)
	{
		if( ! alreadyAdded )
		{
			ShutdownHook shutdownHook = new ShutdownHook(eventDir,agentController);
			Runtime.getRuntime().addShutdownHook(shutdownHook);
			shutdownHook.startCleanThread();  
		
			  
			alreadyAdded = true;
		}
	}
	
	
	public  void stopProcessing(String eventDir )
	{
            try{
			
            	cleanCallbackStatePerThreadRecovery.stopped = true;
            	cleanCallbackStatePerThreadRecovery.interrupt();
            	
			
			if(  	CallbackState.slidingWindow  > 0)
			{
				System.err.println("agent stopped ");
				
				CallbackState.queueFacade.stop();
	
				System.err.println("agent ended ");
			}
			
			
			
	    		
	    		
		
			
			
			

		    
		    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	
	
	
	@Override
	public void run() {
		
		
		  CallbackState.callbackStatePerThread.get().stackTraceBasedDoNotTrace++;	
	
		stopProcessing(eventDir);
		
		
		
		
	}

}
