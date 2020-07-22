package com.vmlens.trace.agent.bootstrap.interleave.normalized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.vmlens.trace.agent.bootstrap.interleave.operation.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;
import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorPosition;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorState;
import com.vmlens.trace.agent.bootstrap.interleave.actualAccess.Comparator4LockAccess;
import com.vmlens.trace.agent.bootstrap.interleave.operation.ThreadJoin;
import com.vmlens.trace.agent.bootstrap.interleave.operation.ThreadStarted;
import gnu.trove.map.hash.THashMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.map.hash.TObjectIntHashMap;
import gnu.trove.set.hash.TIntHashSet;
import gnu.trove.iterator.TIntObjectIterator;
import gnu.trove.iterator.TLongObjectIterator;
import gnu.trove.iterator.TObjectIntIterator;
import gnu.trove.list.linked.TLinkedList;

public class RelationMap {

	public final TIntObjectHashMap<TLinkedList<PositionAndOperation>> volatileAccess = new TIntObjectHashMap<TLinkedList<PositionAndOperation>>();
	public final TIntObjectHashMap<TLinkedList<PositionAndOperation>> atomicAccess = new TIntObjectHashMap<TLinkedList<PositionAndOperation>>();
	public final TIntObjectHashMap<TLinkedList<PositionAndOperation>> monitorAccess = new TIntObjectHashMap<TLinkedList<PositionAndOperation>>();
	
	public final TObjectIntHashMap<MonitorPosition>  monitorKey2Count = new TObjectIntHashMap<MonitorPosition>();
	
	public final TLinkedList<PositionAndOperation> tasks = new TLinkedList<PositionAndOperation>();
	
	public final TLinkedList<PositionAndOperation> callBackAccess = new TLinkedList<PositionAndOperation>();
	
	public final TLinkedList<PositionAndOperation> exclusiveLockExitAccess = new TLinkedList<PositionAndOperation>();
	public final TLinkedList<PositionAndOperation> sharedLockEnterAccess = new TLinkedList<PositionAndOperation>();
	
	public final TLinkedList<PositionAndOperation> startThread = new TLinkedList<PositionAndOperation>();
	public final TLinkedList<PositionAndOperation> joinThread = new TLinkedList<PositionAndOperation>();
	
	public final TLongObjectHashMap<TLinkedList<PositionAndOperation>> volatileArrayAccess = new TLongObjectHashMap<TLinkedList<PositionAndOperation>>();

	private final int[] maxPositionPerThread;
	
	private final MonitorState[][] threadIndex2Position2MonitorArray;
	
	
	
	

	public RelationMap(int[] maxPositionPerThread, MonitorState[][] threadIndex2Position2MonitorArray) {
		super();
		this.maxPositionPerThread = maxPositionPerThread;
		this.threadIndex2Position2MonitorArray = threadIndex2Position2MonitorArray;
	}

	public RelationList create() {
		
		TIntHashSet knownVolatileFields = new TIntHashSet();
		
		
		for( int i :  volatileAccess.keys() )
		{
			knownVolatileFields.add(i);
		}
		
		
		RelationList orderList = new RelationList(maxPositionPerThread,knownVolatileFields,threadIndex2Position2MonitorArray);




		addFromValueCollection(orderList, volatileAccess , "volatileAccess");
		
		addFromList(orderList, tasks , "tasks" );
		
		addFromList(orderList, callBackAccess , "callBackAccess" );
		addFromValueCollection(orderList, atomicAccess , "atomicAccess");
		
		addFromList(orderList, exclusiveLockExitAccess, "exclusiveLockExitAccess");
		addFromList(orderList, sharedLockEnterAccess, "sharedLockEnterAccess");
		
		addFromValueCollection(orderList, monitorAccess , "monitorAccess");

		addFromValueCollection(orderList, volatileArrayAccess);
		
		
		Iterator<PositionAndOperation> startIterator = startThread.iterator();
		
		while( startIterator.hasNext() )
		{
			PositionAndOperation current = startIterator.next();
			
			orderList.addStartRelation(  current.position , new Position( ((ThreadStarted) current.operation).startedThreadIndex , 0 )    );
				
		}
		
		Iterator<PositionAndOperation> joinIterator = joinThread.iterator();
		
		while( joinIterator.hasNext() )
		{
			PositionAndOperation current = joinIterator.next();
			
			orderList.addJoinRelation(  current.position ,  ((ThreadJoin) current.operation).joinedThreadIndex   );
			
		}
		
		
		
		

		return orderList;
	}

	//

	private void addFromValueCollection(RelationList orderList,TIntObjectHashMap<TLinkedList<PositionAndOperation>> values, String name) {
		 TIntObjectIterator<TLinkedList<PositionAndOperation>> it = values.iterator();

		while (it.hasNext()) {
			it.advance();
			
			
			
			addFromList(orderList, it.value() ,name + it.key());
		}

	}
	
	
	
	
	private void addFromValueCollection(RelationList orderList,TLongObjectHashMap<TLinkedList<PositionAndOperation>> values) {
		 TLongObjectIterator<TLinkedList<PositionAndOperation>> it = values.iterator();

		while (it.hasNext()) {
			it.advance();
			
			
			
			addFromList(orderList, it.value() , "volatile array " + it.key());
		}

	}
	
	
	
	
	

//	public void setMaxPositionPerThread(int[] maxPositionPerThread) {
//		this.maxPositionPerThread = maxPositionPerThread;
//	}

	private static void addFromList(RelationList orderList, TLinkedList<PositionAndOperation> accessList,String name) {
		
		
		int startSize = orderList.potentialOrderSize();
		
		PositionAndOperation[] posArray = (PositionAndOperation[]) accessList.toArray(new PositionAndOperation[0]);

		Arrays.sort( posArray , new Comparator4PositionAndOperation() );
		
		int startIndex = 0;

		while (startIndex < posArray.length) {
			int endIndex = 0;
			int currentThreadIndex = -1;

			for (endIndex = startIndex; endIndex < posArray.length; endIndex++) {
				int newIndex = posArray[endIndex].position.threadIndex;

				if (currentThreadIndex == -1) {
					currentThreadIndex = newIndex;
				} else if (currentThreadIndex != newIndex) {
					break;
				}
			}

			for (int i = startIndex; i < endIndex; i++) {
				for (int j = endIndex; j < posArray.length; j++) {
					PositionAndOperation a = posArray[i];
					PositionAndOperation b = posArray[j];
							
					if (a.operation.createsSyncRelation(b.operation)) {
						
						a.operation.addPotentialRelation(orderList , a.position,b.operation ,  b.position    );
						
		
					}
				}

			}

			startIndex = endIndex;

		}
		
		if(ParallizeFacade.ENABLE_LOGGING  )
		{
			AgentLogCallback.log( name +  " " + ( orderList.potentialOrderSize() -  startSize ));
		}
		

	}
	
	
	private static boolean isSameFor(TIntObjectHashMap<TLinkedList<PositionAndOperation>>  myValues,TIntObjectHashMap<TLinkedList<PositionAndOperation>> other )
	{
		if(  myValues.size() < other.size())
		{
			if(ParallizeFacade.ENABLE_LOGGING ||  ParallizeFacade.ENABLE_PERFORMANCE_LOGGING )
			{
				AgentLogCallback.log( "volatileAccess.size() smaller " + myValues.size() + " " + other.size());
			}
			
			
			return  false;
		}
		
		
		TIntObjectIterator<TLinkedList<PositionAndOperation>> iterator = myValues.iterator();
		
		while(iterator.hasNext())
		{
			iterator.advance();
			
			if(   other.contains(iterator.key()) )
			{
				TLinkedList<PositionAndOperation> otherList = other.get(iterator.key());
				
				if( iterator.value().size() < otherList.size())
				{
					if(ParallizeFacade.ENABLE_LOGGING||  ParallizeFacade.ENABLE_PERFORMANCE_LOGGING)
					{
						AgentLogCallback.log( "  iterator.value().size smaller " +   iterator.value().size() + " " +  otherList.size() );
					}
					
					
					return false;
				}
				
				
			}
			else
			{
				if(ParallizeFacade.ENABLE_LOGGING||  ParallizeFacade.ENABLE_PERFORMANCE_LOGGING)
				{
					AgentLogCallback.log( "not there "  + iterator.key());
				}
				
				
				return false;
			}
			
			
			
		}
		
		return true;
		
	}
	
	

	public boolean isSame(RelationMap relationMap) {
	
		
		if( ! isSameFor( volatileAccess , relationMap.volatileAccess ) )
		{
			if(ParallizeFacade.ENABLE_LOGGING||  ParallizeFacade.ENABLE_PERFORMANCE_LOGGING)
			{
				AgentLogCallback.log( "not same volatileAccess " );
			}
			
			return false;
		}
		
		
		if( ! isSameFor( atomicAccess , relationMap.atomicAccess ) )
		{
			if(ParallizeFacade.ENABLE_LOGGING||  ParallizeFacade.ENABLE_PERFORMANCE_LOGGING)
			{
				AgentLogCallback.log( "not same atomicAccess " );
			}
			
			return false;
		}
		
		
	
		
		
		if(callBackAccess.size()<relationMap.callBackAccess.size()  )
		{
			return false;
		}
		
		
		
		
		if(  monitorKey2Count.size() < relationMap.monitorKey2Count.size())
		{
			if(ParallizeFacade.ENABLE_LOGGING ||  ParallizeFacade.ENABLE_PERFORMANCE_LOGGING )
			{
				AgentLogCallback.log( "monitorKey2Count.size() smaller " + monitorKey2Count.size() + " " + relationMap.monitorKey2Count.size());
			}
			
			
			return  false;
		}

		TObjectIntIterator<MonitorPosition> iter = monitorKey2Count.iterator();
		
		while( iter.hasNext() )
		{
			iter.advance();
			
			if( iter.value() < relationMap.monitorKey2Count.get(iter.key()) )
			{
				
				if(ParallizeFacade.ENABLE_LOGGING ||  ParallizeFacade.ENABLE_PERFORMANCE_LOGGING )
				{
					AgentLogCallback.log( "monitorKey2Count count smaller " + iter.key() );
				}
				
				return  false;
				
			}
		}
		
		
		
		if(exclusiveLockExitAccess.size() < relationMap.exclusiveLockExitAccess.size()  )
		{
			return false;
		}
		
		
		if(startThread.size() < relationMap.startThread.size()  )
		{
			if(ParallizeFacade.ENABLE_LOGGING||  ParallizeFacade.ENABLE_PERFORMANCE_LOGGING)
			{
				AgentLogCallback.log( "startThread.size smaller " +startThread.size() + " " + relationMap.startThread.size());
			}
			
			
			return false;
		}
		
		return true;
	}

}
