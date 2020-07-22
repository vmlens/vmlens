package com.vmlens.trace.agent.bootstrap.mode;



public interface AgentMode {

	boolean processInterleaveAnnotation();
	boolean processSharedAnnotation();
	String asPropertyString();
	
	
	boolean processFields();
	boolean processMonitors();
	
//	int getFieldCallBackId(boolean isWrite, FieldTyp fieldTyp);
	
	boolean syncActionSameAsField4TraceCheck();
	
	
	
	boolean isInterleave();
	boolean isState();
	
}
