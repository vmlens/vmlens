package com.vmlens.trace.agent.bootstrap.parallize.logic.decisionLogic;

import com.vmlens.trace.agent.bootstrap.parallize.logicState.Decision;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionStack;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionWaiting;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.ThreadId2State;
import com.vmlens.trace.agent.bootstrap.parallize.operation.Operation;

public interface Mode {
	Decision decide( Operation operation, long threadId, ThreadId2State threadId2State);
	Mode beforeDecision();
	DecisionWaiting decide4ThreadEnded(ThreadId2State threadId2State);
	Decision decideBewteenKeepGoingOrWakeup(ThreadId2State threadId2State);
	
	
}
