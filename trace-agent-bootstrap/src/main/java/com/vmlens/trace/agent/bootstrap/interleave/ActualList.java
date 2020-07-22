package com.vmlens.trace.agent.bootstrap.interleave;


import java.util.Arrays;

import com.vmlens.trace.agent.bootstrap.interleave.actualAccess.ActualAccess;
import com.vmlens.trace.agent.bootstrap.interleave.actualAccess.Comparator4ActualAccess;
import com.vmlens.trace.agent.bootstrap.interleave.actualAccess.LockAccess;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.NormalizedAccess;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.NormalizedThread;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.Position;
import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;

import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.TIntIntHashMap;
import gnu.trove.map.hash.TIntObjectHashMap;


public class ActualList {

	private TLinkedList<ActualAccess> accessList = new TLinkedList<ActualAccess>();
	private TLinkedList<LockAccess> lockList = new TLinkedList<LockAccess>();
	
	private final TIntIntHashMap threadIndex2ThreadPosition = new TIntIntHashMap();
	private final TIntObjectHashMap<MonitorLockEnterStack> threadIndex2MonitorLockEnterStack = new TIntObjectHashMap<MonitorLockEnterStack>();
	
	
	
	private int currentPosition = 0;
	private  int lockPosition = 0;
	
	int maxThreadIndex= 0;
	
	NormalizedList normalize()
	{
		ActualAccess[] array= accessList.toArray(new ActualAccess[0]);
		
		Arrays.sort( array , new Comparator4ActualAccess() );
		NormalizedThread[]  threadArray = new NormalizedThread[maxThreadIndex + 1];
		int currentThreadIndex = 0;
		TLinkedList<NormalizedAccess> current = new TLinkedList<NormalizedAccess>();
		threadArray[0]  =    new NormalizedThread( current );
		
		for( ActualAccess access :  array)
		{
			
			
			if(  access.threadIndex != currentThreadIndex )
			{
				
				
				
				
				currentThreadIndex =  access.threadIndex;
				current = new TLinkedList<NormalizedAccess>();
				threadArray[access.threadIndex  ]    = new NormalizedThread( current );
				
				
			}
			
			current.addLast(  access.create()  );
			
			
		}
		
		for( int i = 0 ; i < threadArray.length ; i++)
		{
			if(threadArray[i] == null)
			{
				 current = new TLinkedList<NormalizedAccess>();
				threadArray[i]    = new NormalizedThread( current );
			}
		}
		
		return  NormalizedList.create(threadArray,lockList);
	}
	
	

	void newThreadBegan(int threadIndex)
	{
		 if(maxThreadIndex < threadIndex)
		 {
			 maxThreadIndex = threadIndex;
		 }
	}
	

	void addLock(int threadIndex, LockOperation operation)
	{
		
	
		
		 if(maxThreadIndex < threadIndex)
		 {
			 maxThreadIndex = threadIndex;
		 }
		 
		 int positionInThread =   threadIndex2ThreadPosition.get(threadIndex);
		 
		 lockList.add( new   LockAccess( threadIndex , operation,positionInThread,  lockPosition) );
		 
		 
		 MonitorLockEnterStack monitorLockEnterStack = threadIndex2MonitorLockEnterStack.get( threadIndex );
		 
		 if( monitorLockEnterStack == null )
		 {
			 monitorLockEnterStack = new MonitorLockEnterStack();
			 threadIndex2MonitorLockEnterStack.put(threadIndex ,monitorLockEnterStack );
		 }
		 
		 operation.add2Stack(monitorLockEnterStack ,threadIndex ,positionInThread  );
		 
		 lockPosition++;
	}
	
	
		


	 void add(int threadIndex, OperationTyp actualAccess){
		
		
			
		 
		 
		 if(maxThreadIndex < threadIndex)
		 {
			 maxThreadIndex = threadIndex;
		 }
		
		 
		 int perThreadPosition = threadIndex2ThreadPosition.get(threadIndex) ;
		 
		 MonitorLockEnterStack monitorLockEnterStack = threadIndex2MonitorLockEnterStack.get( threadIndex );
		 
		 if( monitorLockEnterStack == null )
		 {
			 monitorLockEnterStack = new MonitorLockEnterStack();
			 threadIndex2MonitorLockEnterStack.put(threadIndex ,monitorLockEnterStack );
		 }
		 
		 actualAccess.setDataFromMonitorStack(monitorLockEnterStack ,threadIndex ,  perThreadPosition);
		 
		 threadIndex2ThreadPosition.put( threadIndex ,perThreadPosition + 1);
		 
		 accessList.add( new  ActualAccess(threadIndex , actualAccess , currentPosition) );
		 currentPosition++;
	}



//	 void onLockEnterExit(int threadIndex) {
//		
//		 MonitorLockEnterStack monitorLockEnterStack = threadIndex2MonitorLockEnterStack.get( threadIndex );
//		 if( monitorLockEnterStack == null )
//		 {
//			 monitorLockEnterStack = new MonitorLockEnterStack();
//			 threadIndex2MonitorLockEnterStack.put(threadIndex ,monitorLockEnterStack );
//		 }
//		 
//		 int positionInThread =   threadIndex2ThreadPosition.get(threadIndex);
//		 
//		 monitorLockEnterStack.lock.add(new TLinkableWrapper( new Position(threadIndex,positionInThread) ));
//
//	}
	
	
}
