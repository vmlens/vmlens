package com.vmlens.trace.agent.bootstrap.interleave.createThreadIds;

import com.vmlens.trace.agent.bootstrap.interleave.syncAction.SyncAction;

public class LeftBeforeRight {
	
	private final SyncAction left;
	private final SyncAction right;
	
	public LeftBeforeRight(SyncAction left, SyncAction right) {
		super();
		this.left = left;
		this.right = right;
	}

	public boolean isLeftBeforeRight(SyncAction inLeft, SyncAction inRight) {
		
		if( inLeft.threadIndex == left.threadIndex && inLeft.position <= left.position   )
		{
			if(  inRight.threadIndex == right.threadIndex && inRight.position >= right.position  ) 	{
				return true;
			}
		}
		
		
		return false;
	}
	

	
	public LeftBeforeRight reverse() {
		return new LeftBeforeRight(right,left);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
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
		LeftBeforeRight other = (LeftBeforeRight) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return   left.threadIndex + "," +  left.position + "<" +  right.threadIndex + "," +  right.position ;
	}
	
	
}
