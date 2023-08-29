package com.anarsoft.trace.agent.runtime.repository;

public class MethodKeyWithPosition {

	private final String className;
	private final String methodName;
	private final String methodDesc;
	private final int position;
	
	public MethodKeyWithPosition(String className, String methodName, String methodDesc, int position) {
		super();
		this.className = className;
		this.methodName = methodName;
		this.methodDesc = methodDesc;
		this.position = position;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((methodDesc == null) ? 0 : methodDesc.hashCode());
		result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
		result = prime * result + position;
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
		MethodKeyWithPosition other = (MethodKeyWithPosition) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (methodDesc == null) {
			if (other.methodDesc != null)
				return false;
		} else if (!methodDesc.equals(other.methodDesc))
			return false;
		if (methodName == null) {
			if (other.methodName != null)
				return false;
		} else if (!methodName.equals(other.methodName))
			return false;
		if (position != other.position)
			return false;
		return true;
	}
	
	
	
}
