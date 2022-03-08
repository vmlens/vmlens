package com.vmlens.trace.agent.bootstrap.parallize.logicState;

import java.io.PrintWriter;
import java.lang.Thread.State;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;
import com.vmlens.trace.agent.bootstrap.interleave.InterleaveFacade;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;
import com.vmlens.trace.agent.bootstrap.interleave.operation.ThreadJoin;

import gnu.trove.iterator.TIntLongIterator;
import gnu.trove.iterator.TLongObjectIterator;
import gnu.trove.map.hash.TIntLongHashMap;
import gnu.trove.map.hash.TLongIntHashMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.procedure.TLongObjectProcedure;
import gnu.trove.set.hash.TLongHashSet;

public class ThreadId2State {

	final TLongHashSet blockedThreads = new TLongHashSet();
	private final TLongObjectHashMap<ThreadState> map = new TLongObjectHashMap<ThreadState>();
	private final TLongIntHashMap startedThreadIds = new TLongIntHashMap();
	
	
	
	
	//private LoopList index2ThreadId = new LoopList();
	public static final int LOOP_DETECTION_UNDECIDED = -1;

	private final TIntLongHashMap index2ThreadId = new TIntLongHashMap();

	private int maxIndex = 0;

	private final InterleaveFacade interleaveFacade;

	public ThreadId2State(InterleaveFacade interleaveFacade) {
		super();
		this.interleaveFacade = interleaveFacade;
	}

	
	public long[] activeThreadIds() 
	{
		return map.keys();
	}
	
	
	
	public int incrementOperationCount(long threadId)
	{
		ThreadState threadState = map.get(threadId);
		
		if(threadState == null)
		{
			return 0;
		}
		
		threadState.operationCount++;
		
		return threadState.operationCount;
		
		
	}
	
	
	public long getActiveThreadId4ThreadEnded() {

		int result = interleaveFacade.activeThreadIndex();

		if (result == -1) {
			
			if(index2ThreadId.contains(0))
			{ 
				return index2ThreadId.get(0);
			}
			
			
			TIntLongIterator it = index2ThreadId.iterator();
			
		    it.advance();	
		
			return it.value();
		
		} else {
			
			if(! index2ThreadId.contains(result))
			{
				//AgentLogCallback.log("not there: " + result);
				interleaveFacade.incrementCurrentIndex();
				
				return getActiveThreadId4ThreadEnded();
			}
			
			
			return index2ThreadId.get(result);
		}

	}
	
	
	private static final int MAX_OPERATIONS_IN_THREAD = 10;
	
	
	private long getActiveThreadIdAtEnd(long forThreadId)
	{
		
	

		if(  ! interleaveFacade.isSecondRun )
		{
			long mainThreadId= index2ThreadId.get(0);
			ThreadState threadState = map.get(mainThreadId);
			
			if(threadState != null)
			{
				if( threadState.timesReturnedForEndOfActiveThreadIndex < MAX_OPERATIONS_IN_THREAD )
				{
					 threadState.timesReturnedForEndOfActiveThreadIndex++;
					 return mainThreadId;
				}
				
				
				// threadState.timesReturnedForEndOfActiveThreadIndex = 0;
			}
			
		}
		
		
	

	
		ThreadState  threadState = map.get(forThreadId);
		
		if( threadState.timesReturnedForEndOfActiveThreadIndex < MAX_OPERATIONS_IN_THREAD )
		{
			 threadState.timesReturnedForEndOfActiveThreadIndex++;
			 return forThreadId;
		}
		
		
		TLongObjectIterator<ThreadState> iter = map.iterator();
		while( iter.hasNext() )
		{
			iter.advance();
			threadState = iter.value();
			
			
			if( threadState.timesReturnedForEndOfActiveThreadIndex < MAX_OPERATIONS_IN_THREAD )
			{
				 threadState.timesReturnedForEndOfActiveThreadIndex++;
				 return iter.key();
			}
			
			
			
		}
		
		iter = map.iterator();
		while( iter.hasNext() )
		{
			iter.advance();
			threadState = iter.value();
			threadState.timesReturnedForEndOfActiveThreadIndex = 0;
			
			
		}
		
		return forThreadId;
		
	//}	
		
		
	}
	
	
	public void setAtThreadJoin(long forThreadId,long joinWithThreadId)
	{
		ThreadState threadState = map.get(forThreadId);
		if( threadState != null )
		{
			threadState.joinWithThreadId = joinWithThreadId;
		}
		
		
	}
	
	

	public long getActiveThreadId4AfterOperation(long forThreadId) {

		int result = interleaveFacade.activeThreadIndex();

		if (result == -1) {
			return getActiveThreadIdAtEnd(forThreadId);
		} else {
			
			if( ! index2ThreadId.contains(result))
			{
				return forThreadId;
			}
			
			return index2ThreadId.get(result);
		}

	}
	
	
	
	public void setActivated(long threadId) {
		ThreadState state = map.get(threadId);

		if (state != null) {
			if (state.thread.getState() != State.RUNNABLE) {
				System.err.println(state.thread.getState());
				(new Exception()).printStackTrace();
			}

			state.activated = true;
			state.joinWithThreadId  = -1L;
		}

	}

	public void setDeactivated(long threadId) {
		ThreadState state = map.get(threadId);

		if (state != null) {

			state.activated     = false;
			state.joinWithThreadId  = -1L;
		}
	}

	public void debug(final PrintWriter writer) {

		writer.println(map.size());
		map.forEachEntry(new TLongObjectProcedure<ThreadState>() {

			@Override
			public boolean execute(long a, ThreadState b) {

				writer.println(a + " " + b + " " + b.thread.getState());

				return true;
			}

		});

	}

	boolean isActive(long threadId, LogicStateProcessing logicStateProcessing , int runId) {
		ThreadState state = map.get(threadId);
		if (state == null) {
			
			AgentLogCallback.logTimeoutWarning(this , logicStateProcessing , runId );
			
			return false;
		}
		if (!state.activated) {
			return true;
		}
		if (state.thread.getState() == State.RUNNABLE) {
			return true;
		}
		StackTraceElement[] elemArray = state.thread.getStackTrace();
		if (elemArray.length > 0) {
			if (elemArray[0].getClassName().startsWith("com.vmlens.trace.agent")) {
				return true;
			}

		}
		
		/*
		 * wegen:
		 * 	sun.misc.Unsafe.park(Native Method)
			com.vmlens.trace.agent.bootstrap.threadQueue.QueueCollection.park(QueueCollection.java:69)
			
			darf aber nicht com.vmlens.trace.agent sein wegen:
			java.lang.Object.wait(Native Method)
			com.vmlens.trace.agent.bootstrap.callback.SynchronizedStatementCallback.waitCall(SynchronizedStatementCallback.java:28)
			
			wird dann nicht als active erkannt
			
		 */
		
		
		if (elemArray.length > 1) {
			if (elemArray[1].getClassName().startsWith("com.vmlens.trace.agent.bootstrap.threadQueue.QueueCollection")) {
				return true;
			}

		}
		
		
		if( state.joinWithThreadId != -1L )
		{
			if( ! map.contains( state.joinWithThreadId)  )
			{
				
				if(ParallizeFacade.ENABLE_LOGGING)
				{
					AgentLogCallback.log( "joinWithThreadId "+   threadId+ " " + +state.joinWithThreadId );
				}
				
				return true;	
			}
		
			
			// the3se case does not get logged, since it is expected
			return false;
			
		}
		
		AgentLogCallback.logTimeoutWarning(this , logicStateProcessing , runId );
		
		return false;

	}



	int activeThreadCount() {
		return map.size();
	}

	public int size() {
		return map.size();
	}

	boolean isSingleThreaded() {
		return map.size() < 2;
	}

	boolean removeThread(long threadId) {
		
		if( ! map.contains(threadId) )
		{
			AgentLogCallback.logError("not there " + threadId);
			
			return true;
		}
		
		ThreadState state = map.get(threadId);
		state.addedCount--;
		if (state.addedCount == 0) {
			int index = map.get(threadId).index;

			index2ThreadId.remove(index);
			map.remove(threadId);
			
			return true;
			
		}
		
		return false;
	}

	boolean addThread(long threadId, Thread thread) {
		ThreadState state = map.get(threadId);
		if (state == null) {
			index2ThreadId.put(maxIndex, threadId);
			ThreadState threadState = new ThreadState(maxIndex, thread);
			map.put(threadId, threadState);
			maxIndex++;
			
			return true;
			
		} else {
			state.addedCount++;
			
			return false;
		}
	}



	void setTimeout(long activeThreadId) {
		// TODO Auto-generated method stub

	}

	void after(long threadId, OperationTyp operation)  {
		
		if( ! map.contains(threadId))
		{
		
			AgentLogCallback.logThreadId2StateError(this);
			return;
		}
		
		
		
		if(operation instanceof ThreadJoin)
		{
			
			ThreadJoin threadJoin = (ThreadJoin)  operation;
			
			/*
			 * 	public int waitingThreadIndex;
	public int joinedThreadIndex;
	
			 */
			
			 if( startedThreadIds.contains(threadJoin.joinedThreadId) )
			 {
				 
				 ThreadState state = map.get(threadId);
				 
				 state.position++;
				 
				 int from =state.index;
				// state.atThreadJoin = true;
				 
				 int to = startedThreadIds.get(threadJoin.joinedThreadId);
				 
				 threadJoin.waitingThreadIndex = from;
				 threadJoin.joinedThreadIndex = to;
				 
				 
				 interleaveFacade.afterOperation(from,threadJoin);
			 }
		}
		else
		{
			ThreadState state = map.get(threadId);
			 state.position++;
			//	state.atThreadJoin = false;
				
				interleaveFacade.afterOperation(state.index, operation);
		}
		
	}

	
	void afterThreadStart(long startingThreadId, long startedThreadId) 
	{
		if (ParallizeFacade.ENABLE_LOGGING) {
			AgentLogCallback.log("{\"loopId\":0,\"runId\":0,\"threadId\":"
					+startingThreadId + ",\"actualOperation\":{\"jsonClass\":\"StartThread\",\"threadId\":" + startedThreadId +"}}");
		}
 
		
		startedThreadIds.put(startedThreadId,   map.get(startedThreadId).index);
		
		ThreadState state =   map.get(startingThreadId);
		state.position++;
		int startingIndex = state.index;
				
		interleaveFacade.afterThreadStart(startingIndex ,   map.get(startedThreadId).index );
		
		interleaveFacade.newThreadBegan( map.get(startedThreadId).index);
		
	//	interleaveFacade.after( map.get(startedThreadId).index  , new BeginWithThread());
		
		
	}

	


	 void lockOperation(long threadId, LockOperation operation) {
		 
		 if( ! map.contains(threadId))
			{
			
				AgentLogCallback.logThreadId2StateError(this);
				return;
			}
		 
		 
			interleaveFacade.lockOperation(map.get(threadId).index, operation);
		
	}


	 
	 

	
}
