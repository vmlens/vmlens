package com.vmlens.trace.agent.bootstrap.parallize.logic;

public class RunnableOrThreadWrapper  {
	
	public final Object runnable;

	public RunnableOrThreadWrapper(Object runnable) {
		super();
		this.runnable = runnable;
	}


	public boolean isSame(RunnableOrThreadWrapper other) {
		
		
			return runnable == other.runnable;
			

	}

	@Override
	public String toString() {
		return "RunnableWrapper [runnable=" + runnable + "]";
	}

	
	
	
}
