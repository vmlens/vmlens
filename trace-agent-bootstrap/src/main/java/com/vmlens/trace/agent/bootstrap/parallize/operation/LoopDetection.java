package com.vmlens.trace.agent.bootstrap.parallize.operation;

import com.vmlens.trace.agent.bootstrap.parallize.logicState.Decision;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionActive;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionWaiting;

import gnu.trove.map.hash.THashMap;

public class LoopDetection {

	
	private final THashMap<Operation,LoopElement>   operation2LoopElement = new THashMap<Operation,LoopElement>();
	
	
	
	
	
//	private Decision getDecision4Loop(int threadCount)
//	{
//		if(threadCount < 2)
//		{
//			return new DecisionActive();
//		}
//		
//		
//		
//		int temp = loopIndex;
//		loopIndex++;
//		
//		
//		int actionIndex = temp / newDecisionEvery;
//		
//		int decisionIndex = actionIndex % threadCount;
//		
//		if(decisionIndex == 0)
//		{
//			return new DecisionActive();
//		}
//		else
//		{
//			return new DecisionWaiting(decisionIndex - 1);
//		}
//		
//		
//		
//		
//		
//		
//		
//	}
//	
	
	
	
	public Decision getExisting(Operation operation, int threadCount)
	{
		LoopElement current = operation2LoopElement.get(operation);
		
		if( current == null )
		{
			return null;
		}
		
		
	    return current.getNext(threadCount);
		
	
	}





	public void setDecision(Operation operation, Decision decision) {
		operation2LoopElement.put( operation , new LoopElement(decision) );
		
	}
}
