package com.vmlens.trace.agent.bootstrap.util;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {

	
	public LinkedListElement<T> head;
	public LinkedListElement<T> tail;
	
	public void add(T in)
	{
		if( head == null )
		{
			LinkedListElement<T> start = new LinkedListElement<T>(in);
			head = start;
			tail = start;
		}
		else
		{
			head.next = new LinkedListElement<T>(in);
			head = head.next;
			
		}
	}

	@Override
	public Iterator<T> iterator() {
	
		return new LinkedListIterator<T>(this);
	}
	
	
	public int size()
	{
		
		int size = 0;
		
		LinkedListElement<T> current = tail;
		
		while( current != null )
		{
			size++;
			
			
			current = current.next;
		}
		
		
		return size;
	}
	
	
	
	
//	public void foreach(Consumer<T> consumer)
//	{
//		LinkedListElement<T> current = tail;
//		
//		while( current != null )
//		{
//			consumer.accept( current.element  );
//			
//			
//			current = current.next;
//		}
//		
//		
//		
//	}
	
	
	
}
