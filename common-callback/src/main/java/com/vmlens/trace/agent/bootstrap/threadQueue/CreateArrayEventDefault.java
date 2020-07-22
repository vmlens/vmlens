package com.vmlens.trace.agent.bootstrap.threadQueue;

public class CreateArrayEventDefault implements CreateArrayEvent {

	@Override
	public ArrayEvent create(Object[] array, int index) {
		return  new ArrayEvent(array , index);
	}

}
