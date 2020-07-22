package com.vmlens.util;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import com.vmlens.trace.agent.bootstrap.util.LinkedList;



public class TestLinkedList {

	@Test
	public void test() {
		LinkedList<String>  list = new LinkedList<String>();
		
		list.add("one");
		list.add("two");
		list.add("three");
		
		Iterator it = list.iterator();
		
		while( it.hasNext() )
		{
			System.out.println( it.next() );
		}
		
		
		
	}

}
