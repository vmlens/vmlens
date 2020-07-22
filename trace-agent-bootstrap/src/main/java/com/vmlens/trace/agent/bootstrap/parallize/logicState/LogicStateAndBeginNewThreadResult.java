package com.vmlens.trace.agent.bootstrap.parallize.logicState;

public class LogicStateAndBeginNewThreadResult {
	
	public final BeginNewThreadResult beginNewThreadResult;
	public final LogicState logicState;
	
	public LogicStateAndBeginNewThreadResult(BeginNewThreadResult beginNewThreadResult, LogicState logicState) {
		super();
		this.beginNewThreadResult = beginNewThreadResult;
		this.logicState = logicState;
	}
	
	
	

}
