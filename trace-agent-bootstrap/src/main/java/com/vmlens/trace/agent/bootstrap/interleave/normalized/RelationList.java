package com.vmlens.trace.agent.bootstrap.interleave.normalized;

import java.util.BitSet;
import java.util.Iterator;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.interleave.CommandList;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorState;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.RelationList.StackNodeRelation;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizeFacade;
import com.vmlens.trace.agent.bootstrap.util.ObjectStack;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import gnu.trove.list.TLinkable;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.set.hash.THashSet;
import gnu.trove.set.hash.TIntHashSet;

/**
 * 
 * bestehend aus einer liste der länge relations * 2 jede relation führ zu zwei
 * orders
 * 
 * 
 * @author thomas
 *
 */

public class RelationList {

	TLinkedList<TLinkableWrapper<PotentialOrder>> potentialOrders = new TLinkedList<TLinkableWrapper<PotentialOrder>>();
	TLinkedList<TLinkableWrapper<LeftBeforeRight>> startOrders = new TLinkedList<TLinkableWrapper<LeftBeforeRight>>();
	TLinkedList<TLinkableWrapper<LeftBeforeRight>> joinOrders = new TLinkedList<TLinkableWrapper<LeftBeforeRight>>();
	private final int[] maxPositionPerThread;
	private final MonitorState[][] threadIndex2Position2MonitorArray;
	private final TIntHashSet knownVolatileFields;

	private final ObjectStack<StackNodeRelation> stack = new ObjectStack<StackNodeRelation>();
	private final THashSet<CommandList> alreadyAdded = 
			new THashSet<CommandList>();
	
	boolean isFirstRun = true;
	
	
	
	public int potentialOrderSize()
	{
		return potentialOrders.size();
	}

	public RelationList( int[] maxPositionPerThread,
			TIntHashSet knownVolatileFields,MonitorState[][] threadIndex2Position2MonitorArray) {
		super();
		this.maxPositionPerThread = maxPositionPerThread;
		this.knownVolatileFields = knownVolatileFields;
		this.threadIndex2Position2MonitorArray = threadIndex2Position2MonitorArray;
	}


//	void addRelation(Position a, Position b) {	
//		
//		System.out.println("add " +  new LeftBeforeRight(a, b));
//		
//		potentialOrders.add(new TLinkableWrapper(new PotentialOrder (new LeftBeforeRight(a, b) ,new LeftBeforeRight(b, a)  )));
//		
//	}
	
	public void addPotentialOrder(PotentialOrder potentialOrder)
	{	
	
		
		
		potentialOrders.add(new TLinkableWrapper( potentialOrder ));
		
	}
	
	
	
    void addStartRelation(Position a, Position b) {		
    	startOrders.add(new TLinkableWrapper(new LeftBeforeRight(a, b)));
	}
	
    
	public void addJoinRelation(Position position, int joinedThreadIndex) {
		LeftBeforeRight leftBeforeRight = new LeftBeforeRight( new Position( joinedThreadIndex , maxPositionPerThread[joinedThreadIndex] -1 )   , position);
		
			joinOrders.add(new TLinkableWrapper(leftBeforeRight));
		
	}
    
    
    

    public final static class StackNodeRelation implements  Cloneable {
		int position;
		final BitSet positions;
		final BitSet disabled;

		private StackNodeRelation(int position, BitSet positions,BitSet disabled) {
			super();
			this.position = position;
			this.positions = positions;
			this.disabled = disabled;
		}

		
		public StackNodeRelation()
		{
			this.position = -1;
			this.positions = new BitSet();
			this.disabled = new BitSet();
			
		}
		
		
		public StackNodeRelation clone()
		{
			return new StackNodeRelation(position, (BitSet) positions.clone(), (BitSet) disabled.clone());
		}
		
		
		public void setAtNextPosition(boolean value)
		{
			position++;
			positions.set(position, value);
		}
		
		
		public boolean get(int index)
		{
			return positions.get(index);
		}
		
		
		public void disableCurrentPosition()
		{
			disabled.set( position , true );
		}


		public boolean isEnabled(int index) {
			return ! disabled.get(index);
		}
		
		
	}

    
    
    private  void add2Stack(RelationArray relationArray  ,ObjectStack<StackNodeRelation>  stack ,StackNodeRelation current )
    {
    	StackNodeRelation clone = current.clone();
		clone.setAtNextPosition(true);
		current.setAtNextPosition(false);
		
		boolean added = false;
		
		if(  getCommandList(	 relationArray  ,   clone ,   clone.position) != null )
		{
			stack.push(clone);
			added = true;
		}
		
		if(  getCommandList(	 relationArray  ,   current ,   current.position) != null )
		{
			stack.push(current);
			added = true;
		}
		
		
		if(  ! added )
		{
		
			
			current.disableCurrentPosition();
			stack.push(current);
		}
    	
    }
    
    
    
    
    public boolean isDone()
    {
    	if(isFirstRun)
    	{
    		return false;
    	}
    	
    	if(! stack.isEmpty())
    	{
    		return false;
    	}
    	
    	return true;
    	
    }
    
    
    
	public TLinkedList<CommandList> create() {
		
		if (ParallizeFacade.ENABLE_LOGGING || ParallizeFacade.ENABLE_PERFORMANCE_LOGGING) {
			AgentLogCallback.log("potentialOrders.size():" + potentialOrders.size());
		}
		
		
		 TLinkedList<CommandList>  result = new  TLinkedList<CommandList> ();
		
		RelationArray relationArray = RelationArray.create(potentialOrders, startOrders,joinOrders);
		
		
		if(isFirstRun)
		{
			if (relationArray.length()  > 0) {	
				 add2Stack( relationArray  ,stack ,new StackNodeRelation() );	
			}
			
			isFirstRun = false;
		}
		
	
		
		
	
		boolean stopProcessing = false;
		int count = 0;
		
		
		while (!stack.isEmpty() && ! stopProcessing ) {
			StackNodeRelation node = stack.poll();
		
			if (  (node.position + 1) < relationArray.length()  ) {
			
				 add2Stack( relationArray  ,  stack ,node);
		
				
			} else {
			
				
				CommandList commandList =  getCommandList(	 relationArray  ,   node ,   node.position);
				
				 
				 if(   commandList != null)
				 {
					 if( ! alreadyAdded.contains(commandList) )
					 {
						 result.add(commandList);
						 alreadyAdded.add(commandList);
					 }
					 
					
				 }
				 else
				 {
				
					 
						AgentLogCallback.logError( "commandList == null");
				 }
					
			}
			
			count++;
		
			if( count > 5000 && result.size() > 5 )
			{
				stopProcessing = true;
			}
			
			
		}
		
		if (ParallizeFacade.ENABLE_PERFORMANCE_LOGGING  || ParallizeFacade.ENABLE_LOGGING) {
			AgentLogCallback.log("command list count " + result.size());
		}
		
	
		
		return result;
	}

	private CommandList getCommandList(	RelationArray relationArray  ,  StackNodeRelation stackNode ,  int position) {

		 OrderedList orderedList = new OrderedList(maxPositionPerThread , stackNode  , relationArray , position  );
		 CommandList commands = orderedList.create(knownVolatileFields, threadIndex2Position2MonitorArray);
		 
		
		 
		return commands;
			
		
	}




}
