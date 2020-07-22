package com.vmlens.trace.agent.bootstrap.mode;




public class AgentModeInterleave implements AgentMode {

	@Override
	public boolean processInterleaveAnnotation() {
	
		return true;
	}

	@Override
	public boolean processSharedAnnotation() {
	
		return false;
	}

	
	@Override
	public String asPropertyString() {
		return ModeNames.INTERLEAVE;
	}

	@Override
	public boolean processFields() {
		
		return true;
	}

	@Override
	public boolean processMonitors() {
		return true;
	}

	
	
	
	

	@Override
	public boolean syncActionSameAsField4TraceCheck() {
		return false;
	}

	@Override
	public boolean isInterleave() {
	
		return true;
	}

	@Override
	public boolean isState() {
	
		return false;
	}
	
	
	
	
}
