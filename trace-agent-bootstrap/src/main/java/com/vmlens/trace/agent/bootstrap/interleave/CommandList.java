package com.vmlens.trace.agent.bootstrap.interleave;




import java.util.Arrays;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.interleave.operation.OperationTyp;
import com.vmlens.trace.agent.bootstrap.interleave.operation.VolatileFieldAccess;

import gnu.trove.list.TLinkable;
import gnu.trove.set.hash.THashSet;
import gnu.trove.set.hash.TIntHashSet;
import com.vmlens.trace.agent.bootstrap.interleave.normalized.Position;


public class CommandList implements TLinkable<CommandList> {
	
	
	private CommandList  next;
	private CommandList  previous;
	
	 void after(int threadIndex, OperationTyp actualAccess) {
			
		 
	
		 if( actualAccess instanceof VolatileFieldAccess )
		 {
			 VolatileFieldAccess volatileFieldAccess =  (VolatileFieldAccess)actualAccess;
			 
			 if(knownVolatileFields.contains(volatileFieldAccess.fieldId))
			 {
				 currentIndex++;
			 }
			 
			 
		 }
		 else
		 {
			 currentIndex++;
		 }
		 
		
		}
	
	 
	 public void incrementCurrentIndex()
	 {
		 currentIndex++;
	 }
	 
	 
	 @Override
	public String toString() {
		return "CommandList [currentIndex=" + currentIndex + ", threadIndex=" + Arrays.toString(threadIndex) + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(threadIndex);
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommandList other = (CommandList) obj;
		if (!Arrays.equals(threadIndex, other.threadIndex))
			return false;
		return true;
	}




	int currentIndex = 0;
	/**
	 * package visible for test
	 * 
	 * 
	 */
	final int[] threadIndex;
	
	private final TIntHashSet knownVolatileFields;
	
	
	

	public CommandList( int[] threadIndex,TIntHashSet knownVolatileFields) {
		super();
		this.threadIndex = threadIndex;
		this.knownVolatileFields  = knownVolatileFields;
		
			
	}

	
	
	
	
	public CommandList() {
		super();
		this.threadIndex = new int[0];
		this.knownVolatileFields  = new TIntHashSet();
		
	}


	 int activeThreadIndex() {

		 if( currentIndex <  threadIndex.length)
		 {
			 return threadIndex[currentIndex];
		 }
		 
		return -1;
	}


	public CommandList getNext() {
		return next;
	}


	public void setNext(CommandList next) {
		this.next = next;
	}


	public CommandList getPrevious() {
		return previous;
	}


	public void setPrevious(CommandList previous) {
		this.previous = previous;
	}

	
	

}
