package com.vmlens.trace.agent.bootstrap.util;

import gnu.trove.list.linked.TIntLinkedList;

public class IntStack {

	
	private final TIntLinkedList list = new TIntLinkedList();
	
	/*
	 * public boolean isEmpty() {
		return linkedList.isEmpty();
	}


	public void push(T in)
	{
		linkedList.add( new TLinkableWrapper<T>(in) );
	}
	
	
	public T poll()
	{
		return linkedList.remove( linkedList.size() -1   ).element;
	}
	
	
	
	 */
	
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
