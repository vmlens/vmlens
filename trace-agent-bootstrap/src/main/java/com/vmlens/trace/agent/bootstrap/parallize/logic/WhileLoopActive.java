package com.vmlens.trace.agent.bootstrap.parallize.logic;


import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.AtomicClassRepo;
import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.event.AgentLogEvent;
import com.vmlens.trace.agent.bootstrap.event.NewSlidingWindowId;
import com.vmlens.trace.agent.bootstrap.event.WhileLoopNameEvent;
import com.vmlens.trace.agent.bootstrap.interleave.InterleaveFacade;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionQueue;
import com.vmlens.trace.agent.bootstrap.parallize.logicState.DecisionStack;
import com.vmlens.trace.agent.bootstrap.util.LoopResultStatusCodes;

import gnu.trove.set.hash.TIntHashSet;

public class WhileLoopActive implements WhileLoop {

	private final InterleaveFacade interleaveFacade = new InterleaveFacade();
	private final int loopId;
	private  int runId = 1;
	private RunStateActive current;
	private boolean isFirst = true;
	private boolean isSecond = true;
	
	private  final TIntHashSet deactivatedSet = new TIntHashSet();
	public final AllInterleavings allInterleavings;
	
	

	
	
	public WhileLoopActive(int loopId,AllInterleavings allInterleavings) {
		super();
		this.loopId = loopId;
		this.allInterleavings = allInterleavings;
		
		for( Class cl :allInterleavings.removeAtomicAnnotationFromClassArray )
		{
			String className = cl.getName().replace('.', '/');
			int id = AtomicClassRepo.getIdOrMinusOne4AtomicClass(className) ;  
		
			
			
			deactivatedSet.add(id);
		}
		
	}


	public   boolean isActivated(int id) {
		
		//AgentLogCallback.log(   "isActivated " + id  + " " +  ! deactivatedSet.contains(id));
		
			return ! deactivatedSet.contains(id);
		
		
	}
	
	

	@Override
	public RunEntity createNextRun(CallbackStatePerThread callbackStatePerThread,Thread forThread) {
		
		
		if( isFirst )
		{
			isFirst  = false;
			
			callbackStatePerThread.queueCollection.putDirect(  new WhileLoopNameEvent(  loopId ,  allInterleavings.name ));
			
			callbackStatePerThread.sendEvent.writeLoopStartEventGen( CallbackState.slidingWindow, loopId);
			
			int currentRunId = runId++;
			
			callbackStatePerThread.sendEvent.writeRunStartEventGen( CallbackState.slidingWindow, loopId, currentRunId);
			current = new RunStateActive( interleaveFacade,this, loopId ,  currentRunId);
			current.logic.startFirstThread(forThread.getId(), forThread);
			return new RunEntity(current,allInterleavings);
		}
		
		if( isSecond )
		{
			isSecond = false;
			interleaveFacade.secondRun();
			int currentRunId = runId++;
			current =  new RunStateActive( interleaveFacade,this, loopId ,  currentRunId);
			current.logic.startFirstThread(forThread.getId(), forThread);
			return new RunEntity(current,allInterleavings);
		}
		
		
		
		if( interleaveFacade.advance()  )
		{
			if( allInterleavings.maximumRuns > 0  )
			{
				if( runId >  allInterleavings.maximumRuns )
				{
					callbackStatePerThread.sendEvent.writeLoopEndEventGen( CallbackState.slidingWindow, loopId , LoopResultStatusCodes.MAXIMUM_RUN_COUNT_REACHED);
					return null;
				}
				
				
			}
			
			if(current.logic.interleaveControlLogic.hasNotTerminatingLoop)
			{
				callbackStatePerThread.sendEvent.writeLoopEndEventGen( CallbackState.slidingWindow, loopId, LoopResultStatusCodes.MAXIMUM_OPERATION_COUNT_REACHED);
				return null;
			}
			
			
			
			
			int currentRunId = runId++;
			
	
			if( currentRunId % 20 == 1 )
			{	
				callbackStatePerThread.queueCollection.putDirect(  new NewSlidingWindowId()  );
			}
			
			callbackStatePerThread.sendEvent.writeRunStartEventGen( CallbackState.slidingWindow, loopId ,currentRunId );
			
			
			
			current =  new RunStateActive( interleaveFacade,this, loopId ,  currentRunId);
			current.logic.startFirstThread(forThread.getId(), forThread);
			return new RunEntity(current,allInterleavings);
	
		}
		else
		{
		
			callbackStatePerThread.sendEvent.writeLoopEndEventGen( CallbackState.slidingWindow, loopId, LoopResultStatusCodes.OK);
			return null;
		}
		
		

	}


	

}
