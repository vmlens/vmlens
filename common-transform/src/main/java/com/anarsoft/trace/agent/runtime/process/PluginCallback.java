package com.anarsoft.trace.agent.runtime.process;

public interface PluginCallback {

	void agentIsWaiting() throws Exception;
	void agentIsRunning() throws Exception;
	void prozessResultNormalStop()  throws Exception;
	void prozessResultTimeout(int lastSlidingWindowId)  throws Exception;
	
}
