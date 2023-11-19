package com.vmlens.trace.agent.bootstrap.util;

import gnu.trove.list.linked.TIntLinkedList;

public class IntStack {

	
	private final TIntLinkedList list = new TIntLinkedList();

    public boolean isEmpty() {
		return list.isEmpty();
	}


	public void push(int in)
	{
		list.add( in );
	}
	
	
	public void poll()
	{
		 list.removeAt( list.size() -1   );
	}
	
	
	public int peek()
	{
		return  list.get( list.size() -1   );
	}


	public int[] toArray() {
		return list.toArray();
	}


	@Override
	public String toString() {
		return "IntStack [list=" + list + "]";
	}
	
	
	
}
