package com.anarsoft.trace.agent.runtime.process;

public interface PluginControllerState {

	
	PluginControllerState execute(AgentState agentState,PluginCallback pluginCallback) throws Exception;
	
	
}
