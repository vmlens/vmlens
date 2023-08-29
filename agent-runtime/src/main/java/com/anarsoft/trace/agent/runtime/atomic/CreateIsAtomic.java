package com.anarsoft.trace.agent.runtime.atomic;

import com.vmlens.trace.agent.bootstrap.typeDesc.AtomicMethodWithCallback;
import com.vmlens.trace.agent.bootstrap.util.*;
import java.util.Iterator;


public class CreateIsAtomic implements CreateAtomic {

	private final LinkedList<AtomicMethodWithCallback> atomicMethodWithCallbackList = 
			new LinkedList<AtomicMethodWithCallback>();
	
	
	@Override
	public AtomicMethodWithCallback[] create() {
		
		AtomicMethodWithCallback[] array= new AtomicMethodWithCallback[atomicMethodWithCallbackList.size()];
		int index= 0;
		
		Iterator<AtomicMethodWithCallback> it = atomicMethodWithCallbackList.iterator();
		
		while( it.hasNext() )
		{
			array[index] = it.next();
			
			index++;
		}
 		
		
		
		return array;
	}

	@Override
	public void addCallback(AtomicMethodWithCallback atomicMethodWithCallback) {
		atomicMethodWithCallbackList.add( atomicMethodWithCallback  );
		
	}

}
