package com.vmlens.trace.agent.bootstrap.parallize.logicState;

import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallize.operation.Operation;

/**
 * 
 * Die idee ist dass beim start der startende thread activ war, daher können wir 
 * auch warten bis der gestartete thread activ wird und zwischen den beiden entscheiden
 * wer laufen soll
 * 
 * 
 * @author thomas
 *
 */

public abstract class LogicStateProcessing extends LogicState {

	@Override
	LogicState beforeStart(RunnableOrThreadWrapper startedTaskWrapper,long time) {
		
		return new LogicStateNewThreadStarted(startedTaskWrapper,time);
	}

	@Override
	LogicState startNewThread(long threadId) {
		
		//throw new RuntimeException("should not be called");
		
		// kann im tiemout fall auftreten
		
		return this;
		
	}

	@Override
	LogicStateAndBeginNewThreadResult beginWithNewThread(long threadId, RunnableOrThreadWrapper beganTask,ThreadId2State threadId2State  ,Thread thread ) {
		
		// ein thread, task ausserhalb unseres tests wurde gestartet, einfach nicht beachten
		
		return new LogicStateAndBeginNewThreadResult(BeginNewThreadResult.UNKNOWN_THREAD, this);

	}



	@Override
	LogicState afterOperation(ThreadId2State threadId2State, long threadId, long time) {
		
		if( threadId2State.isSingleThreaded()  )
		{
			return new LogicStateSingleThread();
		}
		
		
		return    new LogicStateManyThreads(threadId2State.getActiveThreadId4AfterOperation(threadId) , time);
	}

	

}
