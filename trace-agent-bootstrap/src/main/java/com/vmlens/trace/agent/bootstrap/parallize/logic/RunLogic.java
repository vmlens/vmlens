package com.vmlens.trace.agent.bootstrap.parallize.logic;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.interleave.InterleaveFacade;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeSingelton;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.InterleaveControlLogic;

public class RunLogic extends RunLogicAbstract {

	public RunLogic(RunStateActive runStateActive, InterleaveFacade interleaveFacade) {
		super(runStateActive,interleaveFacade);
		
	}

	@Override
	protected void waitTillActive(long threadId) throws InterruptedException
	 {
		 
		 long startedWaiting = System.currentTimeMillis();
		 
		 while(interleaveControlLogic.needs2Wait(threadId , System.currentTimeMillis()))
			{
				ParallizeSingelton.SINGLE_LOCK.wait(10);
				
				if(  System.currentTimeMillis() > startedWaiting + InterleaveControlLogic.TIMEOUT +   2000 )
				{
					
					
					
					AgentLogCallback.logTimeout(interleaveControlLogic.threadId2State ,interleaveControlLogic.logicState );
					
					return;
				}
				
				
			}
		 
	 }

	@Override
	protected void notifyMonitor() {
		 ParallizeSingelton.SINGLE_LOCK.notifyAll();
		
	}

	
	
	

	

	
	
	
}
