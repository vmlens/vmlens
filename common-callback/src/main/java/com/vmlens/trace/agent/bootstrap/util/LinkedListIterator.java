package com.vmlens.trace.agent.bootstrap.util;

import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {

	private final LinkedList<T> list;
	private LinkedListElement<T> current;
	
	
	
	
	public LinkedListIterator(LinkedList<T> list) {
		super();
		this.list = list;
		
		current = list.tail;
		
	}

	@Override
	public boolean hasNext() {
		
		return current != null;
	}

	@Override
	public T next() {
		
		T result = current.element;
		current = current.next;
		
		return result;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
