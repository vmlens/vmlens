package com.anarsoft.trace.agent.runtime.process;

public class PluginControllerStateProcessing implements PluginControllerState  {

	
	private int lastReadSlidingWindoWid;
	
	
	
	
	public PluginControllerStateProcessing(int lastReadSlidingWindoWid) {
		super();
		this.lastReadSlidingWindoWid = lastReadSlidingWindoWid;
	}










	private static boolean isTimeout(long agentTimeStamp)
	{
		
		
		if( agentTimeStamp < ( System.currentTimeMillis() - TimeValues.TIMEOUT_VALUE )  )
		{
			return true;
		}
	
	    return false;
	
	}
	
	
	
	
	
	




	@Override
	public PluginControllerState execute(AgentState agentState, PluginCallback pluginCallback) throws Exception {
		
		
		if(agentState == null)
    	{
			 pluginCallback.prozessResultTimeout( lastReadSlidingWindoWid );
    		return new PluginControllerStateIdle();
    	}
    	
    	
    	
    	
    	
    	if( agentState.getState() == AgentController.STATE_STOPPED   )
    	{
    		
    		
    		 pluginCallback.prozessResultNormalStop();
    		 return new PluginControllerStateIdle();
    	}
    	
    	 if(  isTimeout( agentState.getCurrentTimeStamp())  )
		 {
 
    		 
			
			 pluginCallback.prozessResultTimeout( agentState.getSlidingWindowId() );
			 return new PluginControllerStateIdle();
		 }
    	
    	 
    	 
    		 lastReadSlidingWindoWid = agentState.getSlidingWindowId() ;
    		 pluginCallback.agentIsRunning();
			 return this;
    	
	}

}
