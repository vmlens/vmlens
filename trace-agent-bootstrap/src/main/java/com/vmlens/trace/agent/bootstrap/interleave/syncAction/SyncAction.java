package com.vmlens.trace.agent.bootstrap.interleave.syncAction;

public abstract class SyncAction {

	public static final int VOLATILE_FIELD = 0;
	
	
	
	
	public final int threadIndex;
	public final int position;
	
	protected SyncAction(int threadIndex, int position) {
		super();
		this.threadIndex = threadIndex;
		this.position = position;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + position;
		result = prime * result + threadIndex;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (! (obj instanceof SyncAction))
			return false;
		SyncAction other = (SyncAction) obj;
		if (position != other.position)
			return false;
		if (threadIndex != other.threadIndex)
			return false;
		return true;
	}
	
	
	public abstract int category();
	public abstract int id();
	public abstract boolean createsSyncRelation( SyncAction other );
	
}
