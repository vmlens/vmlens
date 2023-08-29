package com.anarsoft.trace.agent.runtime.atomic;

import com.vmlens.trace.agent.bootstrap.typeDesc.AtomicMethodWithCallback;

public class CreateNotAtomic implements CreateAtomic {

	@Override
	public AtomicMethodWithCallback[] create() {
		return null;
	}

	@Override
	public void addCallback(AtomicMethodWithCallback atomicMethodWithCallback) {
	
		
	}

}
