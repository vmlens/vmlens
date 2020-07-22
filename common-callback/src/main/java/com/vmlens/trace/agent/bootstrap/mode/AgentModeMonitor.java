package com.vmlens.trace.agent.bootstrap.mode;




public class AgentModeMonitor implements AgentMode  {

	@Override
	public boolean processInterleaveAnnotation() {
		
		return false;
	}

	@Override
	public boolean processSharedAnnotation() {
	
		return true;
	}

	@Override
	public String asPropertyString() {
		return ModeNames.MONITOR;
	}

	@Override
	public boolean processFields() {
		
		return false;
	}

	@Override
	public boolean processMonitors() {
		return true;
	}

	
	
	@Override
	public boolean syncActionSameAsField4TraceCheck() {
		return true;
	}

	@Override
	public boolean isInterleave() {
		
		return false;
	}

	@Override
	public boolean isState() {
		
		return false;
	}

}
