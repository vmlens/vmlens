package com.anarsoft.trace.agent.runtime.process;

public class PluginControllerStateIdle implements PluginControllerState {

	private final long creationTime = System.currentTimeMillis();

	@Override
	public PluginControllerState execute(AgentState agentState, PluginCallback pluginCallback) {
		
		if(   agentState == null)
		{
			return this;
		}
		
		if( agentState.getStartTimestamp() <=  creationTime )
    	{
    		
			return this;
	
    	}
		
		return new PluginControllerStateProcessing( agentState.getSlidingWindowId() );
	}
	
	
}
