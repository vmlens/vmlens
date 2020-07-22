package com.vmlens.trace.agent.bootstrap.parallize.logic.decisionLogic;

import com.vmlens.trace.agent.bootstrap.parallize.ParallizeSingelton;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.Decision;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionActive;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionQueue;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionStack;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionWaiting;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.ThreadId2State;
import com.vmlens.trace.agent.bootstrap.parallize.operation.Operation;

public class ModeNewDecisions implements Mode  {
	
	
	private final DecisionQueue currentSteps;

	
	public ModeNewDecisions(DecisionQueue stackNode) {
		super();
		this.currentSteps = stackNode;
	}


	@Override
	public Decision decide( Operation operation, long threadId,
			ThreadId2State threadId2State) {
		
		

		// 2 weil ja 2 threads zum warten benötigt werden und der erste besonders behandelt wird
		for( int  i = 2 ; i < threadId2State.size() ; i++)
		{
			DecisionWaiting decisionWaiting = new DecisionWaiting(i - 1);
			push2Stack(decisionWaiting, threadId2State);
		}
		
		
		push2Stack(new DecisionActive(), threadId2State);
		
		DecisionWaiting decision = new DecisionWaiting(0);
		currentSteps.enqueue(decision);
		
		return decision;
	}
	
	
	private void push2Stack(Decision decision, ThreadId2State threadId2State)
	{
		DecisionQueue clone = currentSteps.cloneQueue();
		clone.enqueue(decision);
		
		
		//threadId2State.decisionStack.push(clone);
		
		
	}


	@Override
	public Mode beforeDecision() {
		return this;
	}


	@Override
	public DecisionWaiting decide4ThreadEnded(ThreadId2State threadId2State) {
	
		
		
	if(ParallizeSingelton.MULTIPLE_DECISIONS_AT_THREAD_END)
	{
		for(int i = 1; i <  threadId2State.size() ; i++)
		{
			push2Stack( new DecisionWaiting(i) , threadId2State);
		}
	}

	DecisionWaiting decision = new DecisionWaiting(0);
	currentSteps.enqueue(decision);
	
	return decision;
		
	
	}


	@Override
	public Decision decideBewteenKeepGoingOrWakeup(ThreadId2State threadId2State) {
		
		if(ParallizeSingelton.MULTIPLE_DECISIONS_AT_WAKE_UP)
		{	
	      push2Stack(new DecisionActive(), threadId2State );
		}
		
		
		Decision decision =  new DecisionWaiting(0);
		currentSteps.enqueue(decision);
		
		return decision;
		
		
		
	}
		

	
	
	
}
