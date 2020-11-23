package com.vmlens.maven.plugin;

import java.io.File;
import java.util.List;

import com.anarsoft.config.MavenMojo;



public class MavenMojoImpl implements  MavenMojo {
	
	private final AbstractMavenPlugin vmlensPlugin;
	private final String testDir;
	
	
	public MavenMojoImpl(AbstractMavenPlugin vmlensPlugin, String testDir) {
		super();
		this.vmlensPlugin = vmlensPlugin;
		this.testDir = testDir;
	}

	
	
	
	
	
	@Override
	public String getTestDir() {
		return testDir;
	}

	@Override
	public File getReportDir() {
	
		return vmlensPlugin.getReportsDirectory();
	}



	
	public List<String>  getOnlyTraceIn() {
		return vmlensPlugin.trace;
	}
	
	
	public List<String>  getDoNotTraceIn() 	{
		return vmlensPlugin.doNotTrace;
	}



	@Override
	public List<String> getSuppressIssues() {
		return vmlensPlugin.suppress;
	}



	@Override
	public boolean getAgentLog() {
	
		return vmlensPlugin.agentLog;
	}



	@Override
	public boolean getAgentLogPerformance() {
	
		return vmlensPlugin.agentLogPerformance;
	}


	



	@Override
	public boolean getDisableTimeoutCheck() {
	
		return vmlensPlugin.disableTimeoutCheck;
	}



	@Override
	public List<String> getExcludeFromTrace() {
	
		return vmlensPlugin.excludeFromStackTrace;
	}



	@Override
	public boolean getDisableTimeoutWarningCheck() {
	
		return vmlensPlugin.disableTimeoutWarningCheck;
	}



	@Override
	public boolean getAgentExceptionLog() {
	
		if(  vmlensPlugin.disableAgentExceptionLog)
		{
			return false;
		}
		
		if(  vmlensPlugin.callbackType == 1 )
		{
			return false;
		}
		
		return true;
	}
	


}
