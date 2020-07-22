package com.vmlens.trace.agent.bootstrap.parallize.logic.decisionLogic;

import com.vmlens.trace.agent.bootstrap.parallize.logicState.Decision;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionQueue;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionWaiting;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.ThreadId2State;
import com.vmlens.trace.agent.bootstrap.parallize.operation.Operation;

public class ModeReplay implements Mode {
	
	private final DecisionQueue currentSteps;
	private final DecisionQueue startSteps;

	public ModeReplay(DecisionQueue stackNode) {
		super();
		this.currentSteps = stackNode;
		this.startSteps = stackNode.cloneQueue();
	}

	@Override
	public Decision decide(Operation operation, long threadId,
			ThreadId2State threadId2State) {
		
		return currentSteps.dequeue();
	}

	@Override
	public Mode beforeDecision() {
		
		if(currentSteps.isEmpty())
		{
			return new ModeNewDecisions(startSteps);
		}
		else
		{
			return this;
		}
		
		
		
	}

	@Override
	public DecisionWaiting decide4ThreadEnded(ThreadId2State threadId2State) {
		Decision decision = currentSteps.dequeue();
		
		if( decision instanceof DecisionWaiting )
		{
			return (DecisionWaiting) decision;
		}
		
		
		return new DecisionWaiting(0);
		
	}

	@Override
	public Decision decideBewteenKeepGoingOrWakeup(ThreadId2State threadId2State) {
		
		return currentSteps.dequeue();
	}
	
}
