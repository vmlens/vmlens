package com.vmlens.trace.agent.bootstrap.parallize.logicState;

import java.util.Iterator;

import gnu.trove.list.TLinkable;
import gnu.trove.list.linked.TLinkedList;

public class DecisionQueue implements TLinkable<DecisionQueue>  {

	private final TLinkedList<Decision> list; // = new TLinkedList<Decision>();
	
	
	public DecisionQueue()
	{
		list = new TLinkedList<Decision>();
	}
	
	
	public DecisionQueue(TLinkedList<Decision> list) {
		super();
		this.list = list;
	}


	private DecisionQueue next;
	private DecisionQueue previous;

	public DecisionQueue getNext() {
		return next;
	}
	public void setNext(DecisionQueue next) {
		this.next = next;
	}
	public DecisionQueue getPrevious() {
		return previous;
	}
	public void setPrevious(DecisionQueue previous) {
		this.previous = previous;
	}
	
	
	public boolean isEmpty()
	{
		return list.isEmpty();
	}
	
	
	
	public void enqueue(Decision decision)
	{
		list.addFirst(decision);
	}
	
	
	public Decision dequeue()
	{
		return list.removeLast();
	}
	
	
	public DecisionQueue cloneQueue()
	{
		TLinkedList<Decision> clone = new TLinkedList<Decision>();
		
		Iterator<Decision> it = list.iterator();
		
		while(it.hasNext())
		{
			clone.add(  it.next().cloneDecision() );
		}
		
		
		
		return new DecisionQueue( clone);
	}


	@Override
	public String toString() {
		return "DecisionQueue [list=" + list + "]";
	}
	
	
	
}
