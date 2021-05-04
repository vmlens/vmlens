package com.vmlens.trace.agent.bootstrap.interleave;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.interleave.actualAccess.Comparator4ActualAccess;
import com.vmlens.trace.agent.bootstrap.interleave.actualAccess.Comparator4LockAccess;
import com.vmlens.trace.agent.bootstrap.interleave.actualAccess.LockAccess;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.*;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;
import com.vmlens.trace.agent.bootstrap.util.IntStack;

import gnu.trove.iterator.TIntObjectIterator;
import gnu.trove.iterator.TObjectIntIterator;
import gnu.trove.iterator.hash.TObjectHashIterator;
import gnu.trove.list.linked.TIntLinkedList;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.THashMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.map.hash.TObjectIntHashMap;
import gnu.trove.set.hash.TIntHashSet;


/*
 * 
 * aus der thread list müssen wir eine map von Operation -> Position erzeugen
 * 
 * 
 * @author thomas
 *
 */



public class NormalizedList {
	
	
	static NormalizedList create(NormalizedThread[]  threadList,TLinkedList<LockAccess> lockList )
	{
		THashMap<ThreadIndexAndOperation,TLinkedList<PositionAndOperation>> operation2PositionList = 
				new THashMap<ThreadIndexAndOperation,TLinkedList<PositionAndOperation>> ();
	
		int[] maxPositionPerThread = new int[threadList.length];
		
	
	
		int threadIndex = 0;
		
		
		for( NormalizedThread current :  threadList)
		{
			
			Iterator<NormalizedAccess> elemIter = current.iterator();
			
			int position = 0;
			
			while(elemIter.hasNext())
			{
				NormalizedAccess element = elemIter.next();
					
				ThreadIndexAndOperation threadIndexAndOperation = new ThreadIndexAndOperation(threadIndex, element.operationTyp);
						
				TLinkedList<PositionAndOperation> positionList = operation2PositionList.get( threadIndexAndOperation  );
				
				if(  positionList == null  ) {
				
					positionList = new TLinkedList<PositionAndOperation> ();
					operation2PositionList.put( threadIndexAndOperation  ,positionList );
					
				}
				
				positionList.add(  new PositionAndOperation(new Position(threadIndex,position), element.operationTyp));
				
				if( positionList.size() > ParallizeFacade.MAX_OPERATION_LIST_SIZE   )
				{	
					if(ParallizeFacade.ENABLE_LOGGING)
					{
						AgentLogCallback.log( "loop removed for " +  element.operationTyp );
					}
					
					
					positionList.remove(1);
				}
							
				position++;
			}
			
			maxPositionPerThread[threadIndex] = position;
			
			threadIndex++;
			
		}
	
		
		
		
		
		MonitorState[][] threadIndex2Position2MonitorArray = new MonitorState[threadList.length][];
		
		
		for( int i = 0 ; i < maxPositionPerThread.length; i++)
		{
			threadIndex2Position2MonitorArray[i] = new MonitorState[maxPositionPerThread[i]];
		}
		
		
		
		LockAccess[] array = lockList.toArray(new LockAccess[0]);
		
		

		Arrays.sort( array , new Comparator4LockAccess() );
		
		int currentIndex = 0;
		int currentPosition = 0;
		IntStack monitorStack  = new IntStack();
		IntStack exclusiveStack  = new IntStack();
		IntStack sharedStack  = new IntStack();
		
		for(LockAccess access : array  )
		{
			if( currentIndex != access.threadIndex )
			{
				 int[]  monitorArray =  monitorStack.toArray();
				 int[]  exclusiveArray =  exclusiveStack.toArray();
				 int[]  sharedArray =  sharedStack.toArray();
				 
				 
				 for( int i = currentPosition ; i < maxPositionPerThread[currentIndex] ; i++)
				 {
					 threadIndex2Position2MonitorArray[currentIndex][i] = new MonitorState(monitorArray, exclusiveArray ,sharedArray );
				 }
		
				 
				 currentIndex = access.threadIndex;
				 currentPosition = access.position;
				 monitorStack  = new IntStack();
				 exclusiveStack  = new IntStack();
				 sharedStack  = new IntStack();
			}
			
			
			if(  currentPosition   != access.position)
			{
				 int[]  monitorArray =  monitorStack.toArray();
				 int[]  exclusiveArray =  exclusiveStack.toArray();
				 int[]  sharedArray =  sharedStack.toArray();
				 
				 for( int i = currentPosition ; i < access.position ; i++)
				 {
					 threadIndex2Position2MonitorArray[currentIndex][i] =  new MonitorState(monitorArray, exclusiveArray ,sharedArray );
				 }
				 
				 currentPosition = access.position;
				 
			
			}
				
			
			access.operation.execute(monitorStack, exclusiveStack , sharedStack);
				
		}
		
		
		 int[]  monitorArray =  monitorStack.toArray();
		 int[]  exclusiveArray =  exclusiveStack.toArray();
		 int[]  sharedArray =  sharedStack.toArray();
		 
		 for( int i = currentPosition ; i < maxPositionPerThread[currentIndex] ; i++)
		 {
			 threadIndex2Position2MonitorArray[currentIndex][i] =  new MonitorState(monitorArray, exclusiveArray ,sharedArray );
		 }
	 
		 
		 
		return new NormalizedList(operation2PositionList,maxPositionPerThread,threadIndex2Position2MonitorArray);
	}
	
	
	
	public final RelationMap relationMap;
	
	
	

	public NormalizedList(THashMap<ThreadIndexAndOperation,TLinkedList<PositionAndOperation>> operation2PositionList, int[] maxPositionPerThread,MonitorState[][] threadIndex2Position2MonitorArray ) {
		super();
		
		
		TIntObjectHashMap<MonitorInfo> monitorId2MonitorInfo = new  TIntObjectHashMap<MonitorInfo>();
		
		Iterator<Entry<ThreadIndexAndOperation,TLinkedList<PositionAndOperation>>> iter = operation2PositionList.entrySet().iterator();
		
		while(  iter.hasNext() ) 
		{
			Entry<ThreadIndexAndOperation,TLinkedList<PositionAndOperation>> current = iter.next();
			
			Iterator<PositionAndOperation> posIter = current.getValue().iterator();
			
			
			while(  posIter.hasNext() )
			{
				 PositionAndOperation pos = posIter.next();
				
				 pos.operation.prefill(monitorId2MonitorInfo, pos.position.threadIndex);
				
			}

			
		}

		
		MonitorIdAndInfo[] monitorIdAndInfoArray = new MonitorIdAndInfo[ monitorId2MonitorInfo.size() ];
		
		int index = 0;
		TIntObjectIterator<MonitorInfo> it = monitorId2MonitorInfo.iterator();
		
		while( it.hasNext() )
		{
			it.advance();
			
			
			monitorIdAndInfoArray[index] = new MonitorIdAndInfo(it.key() , it.value());
			
			
			index++;
		}
		
		Arrays.sort(monitorIdAndInfoArray, new Comparator4MonitorIdAndInfo());
		TObjectIntHashMap<MonitorPosition> monitorPosition2Id = new TObjectIntHashMap<MonitorPosition>();
		
		for(MonitorIdAndInfo obj :monitorIdAndInfoArray )
		{
			TObjectHashIterator<MonitorPosition> i = obj.monitorInfo.positionSet.iterator();
			while( i.hasNext() )
			{
				MonitorPosition c = i.next();
				if( ! monitorPosition2Id.containsKey(c) )
				{
					monitorPosition2Id.put(c, obj.id);
				}
				
				
			}
		
		
		}
		
		
		TObjectIntIterator<MonitorPosition> i = monitorPosition2Id.iterator();
		TIntHashSet takeMonitorIds = new TIntHashSet();
		
		
		while( i.hasNext() )
		{
			i.advance();
			takeMonitorIds.add(i.value());
			
		}
		
		
		
		
		
		 relationMap = new RelationMap(maxPositionPerThread,threadIndex2Position2MonitorArray);
		
	    iter = operation2PositionList.entrySet().iterator();
		
		while(  iter.hasNext() ) 
		{
			 Entry<ThreadIndexAndOperation, TLinkedList<PositionAndOperation>> current = iter.next();
			
			 Iterator<PositionAndOperation> posIter = current.getValue().iterator();
			
			
			while(  posIter.hasNext() )
			{
				 PositionAndOperation pos = posIter.next();
				
				 pos.operation.fill(relationMap, pos.position,takeMonitorIds);
				
			}
			
			
			
			
		}
		
		
		
		
		
	}
 
	
	
	
	
	
	
	
	boolean isSame(NormalizedList other)
	{
	   return	relationMap.isSame( other.relationMap );
	}
	
	
	
	
}
