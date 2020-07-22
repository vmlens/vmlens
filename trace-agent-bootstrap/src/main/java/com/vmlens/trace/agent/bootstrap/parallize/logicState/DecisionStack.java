package com.vmlens.trace.agent.bootstrap.parallize.logicState;

import gnu.trove.list.linked.TLinkedList;

public class DecisionStack {

	private TLinkedList<DecisionQueue> list = new TLinkedList<DecisionQueue>();
	
	
	public boolean isEmpty() {
		
		return list.isEmpty();
	}

	
	public void push(DecisionQueue decisionNode)
	{
		list.addLast(decisionNode);
	}
	
	
	public DecisionQueue pop()
	{
		return list.removeLast();
	}
	
}
