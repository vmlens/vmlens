package com.anarsoft.trace.agent.runtime.atomic;

import com.vmlens.trace.agent.bootstrap.typeDesc.AtomicMethodWithCallback;

public interface CreateAtomic {

	AtomicMethodWithCallback[] create();
	void addCallback(AtomicMethodWithCallback atomicMethodWithCallback);

}
