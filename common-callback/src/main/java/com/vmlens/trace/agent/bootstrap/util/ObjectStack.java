package com.vmlens.trace.agent.bootstrap.util;

import java.util.Iterator;

import gnu.trove.list.linked.TLinkedList;

public class ObjectStack<T> {

	private TLinkedList<TLinkableWrapper<T>> linkedList = new  TLinkedList<TLinkableWrapper<T>>();
	
	
	
	
	public Iterator<TLinkableWrapper<T>> iterator() {
		return linkedList.iterator();
	}


	public boolean isEmpty() {
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
	
	
	
}
