package com.vmlens.trace.agent.bootstrap;

public class OffsetAndClassName {
	
	
	private final long offset;
	private final String className;
	
	public OffsetAndClassName(long offset, String className) {
		super();
		this.offset = offset;
		this.className = className.replace('.', '/');
	}

	public long getOffset() {
		return offset;
	}

	public String getClassName() {
		return className;
	}

	@Override
	public String toString() {
		return "OffsetAndClassName [offset=" + offset + ", className="
				+ className + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result + (int) (offset ^ (offset >>> 32));
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
		OffsetAndClassName other = (OffsetAndClassName) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (offset != other.offset)
			return false;
		return true;
	}
	
	
	
	
	
	
	

}
