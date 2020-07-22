package com.vmlens.trace.agent.bootstrap.interleave.normalized;



import java.util.Iterator;

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import gnu.trove.list.linked.TLinkedList;
import gnu.trove.set.hash.THashSet;

public class RelationArray {

	public final TLinkableWrapper<PotentialOrder>[] array; 
	public final TLinkedList<TLinkableWrapper<LeftBeforeRight>> startOrders;
	public final TLinkedList<TLinkableWrapper<LeftBeforeRight>> joinThread;
	
	//public final THashSet<Position> expectedJoins;
	
	
	public RelationArray(TLinkableWrapper<PotentialOrder>[] array,
			TLinkedList<TLinkableWrapper<LeftBeforeRight>> startOrders, TLinkedList<TLinkableWrapper<LeftBeforeRight>> joinThread) {
		super();
		this.array = array;
		this.startOrders = startOrders;
		this.joinThread = joinThread;
		
//		THashSet<Position> expect = new THashSet<Position> ();
//		
//		Iterator<TLinkableWrapper<LeftBeforeRight>> it = joinThread.iterator();
//		
//		while( it.hasNext() )
//		{
//			TLinkableWrapper<LeftBeforeRight> current= it.next();
//			expect.add( current.element.right );
//			
//		}
//		
//		expectedJoins = expect;
		
		
	}



	int length()
	{
		return array.length;
	}


	static RelationArray create(TLinkedList<TLinkableWrapper<PotentialOrder>> potentialOrders,	TLinkedList<TLinkableWrapper<LeftBeforeRight>> startOrders, TLinkedList<TLinkableWrapper<LeftBeforeRight>> joinThread )
	{
		return new RelationArray ((TLinkableWrapper<PotentialOrder>[]) potentialOrders
				.toArray(new TLinkableWrapper[0]), startOrders, joinThread);
	}
	
	
	
}

