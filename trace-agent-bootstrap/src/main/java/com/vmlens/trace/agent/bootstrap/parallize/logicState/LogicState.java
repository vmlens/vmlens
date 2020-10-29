package com.vmlens.trace.agent.bootstrap.parallize.logicState;


import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;





/*
 * 
 * LogicStateSingleThread
 * 		beforeStart
 * 				-> LogicStateNewThreadStarted
 * 		afterOperation
 * 		calculateActive
 * 		threadEnded
 * 		startNewThread
 * 		beginWithNewThread
 * 		
 * LogicStateNewThreadStarted
 * 		afterOperation
 * 		threadEnded
 * 		startNewThread
 *		calculateActive
 *			->  LogicStateMultiThreaded
 *
 *
 */



public abstract class LogicState {

	abstract LogicState calculateActive(ThreadId2State threadId2State ,  long time , long forThreadId,int runId);
	
	
	
	abstract LogicState afterOperation(ThreadId2State threadId2State , long threadId,  long time);
	abstract LogicState threadEnded(long threadId,ThreadId2State threadId2State,long time);
    abstract LogicState startNewThread(long threadId);
    
    
    
    /**
     * das flag gibt an ob das ein thread ist der zum interleave gehört oder unabhängig davon ausserhalb gestartet wurde
     * 
     * @param threadId
     * @param beganTask
     * @param threadId2State
     * @param thread
     * @return
     */
    
    abstract LogicStateAndBeginNewThreadResult beginWithNewThread(long threadId, RunnableOrThreadWrapper beganTask,ThreadId2State threadId2State ,Thread thread  );
    
    
    
    abstract LogicState beforeStart( RunnableOrThreadWrapper startedTaskWrapper, long time );
    
    
	
    abstract boolean isActive(long threadId);
		
	
}
