package com.vmlens.trace.agent.bootstrap.interleave.normalized;

public class MemoryLocation {
	
	private final int objectId;
	private final int indexOrFieldId;
	
	public MemoryLocation(int objectId, int indexOrFieldId) {
		super();
		this.objectId = objectId;
		this.indexOrFieldId = indexOrFieldId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + indexOrFieldId;
		result = prime * result + objectId;
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
		MemoryLocation other = (MemoryLocation) obj;
		if (indexOrFieldId != other.indexOrFieldId)
			return false;
		if (objectId != other.objectId)
			return false;
		return true;
	}
	
	
	

}
