package com.vmlens.trace.agent.bootstrap.parallize.logic.decisionLogic;

import com.vmlens.trace.agent.bootstrap.parallize.logicState.Decision;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionActive;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionWaiting;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.ThreadId2State;
import com.vmlens.trace.agent.bootstrap.parallize.operation.Operation;

public class DecisionLogicTimeout implements DecisionLogic {

	@Override
	public Decision decide(Operation operation, long threadId,
			ThreadId2State threadId2State) {
		
		return new DecisionActive();
	}

	@Override
	public DecisionWaiting decide4ThreadEnded(ThreadId2State threadId2State) {
		
		return new DecisionWaiting(0);
	}

	@Override
	public Decision decideBewteenKeepGoingOrWakeup(ThreadId2State threadId2State) {
		
		return new DecisionActive();
	}

}
