package com.vmlens;

import java.io.File;
import java.util.List;

import com.anarsoft.config.MavenMojo;

public class MavenMojoForGradle implements MavenMojo {

	private final InterleaveTask interleaveTask;
	
	
	
	public MavenMojoForGradle(InterleaveTask interleaveTask) {
		super();
		this.interleaveTask = interleaveTask;
	}

	@Override
	public boolean getAgentExceptionLog() {
		
		return false;
	}

	@Override
	public boolean getAgentLog() {
		return false;
	}

	@Override
	public boolean getAgentLogPerformance() {
		return false;
	}

	@Override
	public boolean getDisableTimeoutCheck() {
		
		return true;
	}

	@Override
	public boolean getDisableTimeoutWarningCheck() {
		return true;
	}

	@Override
	public List<String> getDoNotTraceIn() {
		
		return interleaveTask.doNotTraceIn;
	}

	@Override
	public List<String> getExcludeFromTrace() {
		
		return interleaveTask.excludeFromTrace;
	}

	
	@Override
	public List<String> getOnlyTraceIn() {
		
		return  interleaveTask.onlyTraceIn;
	}

	@Override
	public List<String> getSuppressIssues() {
		return  interleaveTask.suppress;
	}

	@Override
	public File getReportDir() {
		return  interleaveTask.reportDirectory;
	}

	@Override
	public String getTestDir() {
		return null;
	}

}
