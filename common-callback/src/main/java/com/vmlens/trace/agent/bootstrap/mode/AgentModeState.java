package com.vmlens.trace.agent.bootstrap.mode;




public class AgentModeState  implements AgentMode  {

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
		return ModeNames.STATE;
	}

	@Override
	public boolean processFields() {
		return true;
	}

	@Override
	public boolean processMonitors() {
		
		return false;
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
		
		return true;
	}
	
}
