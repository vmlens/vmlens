package com.vmlens.trace.agent.bootstrap.parallize.logic.decisionLogic;

import com.vmlens.trace.agent.bootstrap.parallize.logicState.Decision;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionQueue;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionWaiting;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.ThreadId2State;
import com.vmlens.trace.agent.bootstrap.parallize.operation.Operation;

public class DecisionLogicNormal implements DecisionLogic {
	
	
	private Mode mode;
	
	
	

	public DecisionLogicNormal(DecisionQueue startNode) {
		super();
		
		
		if(startNode.isEmpty())
		{
			mode = new ModeNewDecisions( startNode );
		}
		else
		{
			mode = new  ModeReplay(startNode);
		}
		

		
	}

	
	public Decision decide( Operation operation, long threadId, ThreadId2State threadId2State)
	{
		Decision fromLoopDetection  = threadId2State.getExisting(threadId, operation );
		
		if(   fromLoopDetection != null )
		{
			return fromLoopDetection;
		}
		
		mode = mode.beforeDecision();
		
		
		Decision decision =  mode.decide(operation, threadId, threadId2State);
		
		threadId2State.setDecision4Loop(threadId , operation ,  decision);
		
		
		return decision;
	}


	@Override
	public DecisionWaiting decide4ThreadEnded(ThreadId2State threadId2State) {
		mode = mode.beforeDecision();
		
		
		
		return mode.decide4ThreadEnded(threadId2State);
	}


	@Override
	public Decision decideBewteenKeepGoingOrWakeup(ThreadId2State threadId2State) {
		mode = mode.beforeDecision();
		return mode.decideBewteenKeepGoingOrWakeup(threadId2State);
	}


	
	


	
	
	

}
