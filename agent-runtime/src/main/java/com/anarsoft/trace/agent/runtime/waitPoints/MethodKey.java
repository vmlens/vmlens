package com.anarsoft.trace.agent.runtime.waitPoints;

public class MethodKey {

	
	private final String className;
	private final String methodName;
	private final String methodDesc;
	public MethodKey(String className, String methodName, String methodDesc) {
		super();
		this.className = className.replace('.', '/');
		this.methodName = methodName;
		this.methodDesc = methodDesc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((methodDesc == null) ? 0 : methodDesc.hashCode());
		result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
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
		MethodKey other = (MethodKey) obj;
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
		return true;
	}
	@Override
	public String toString() {
		return "MethodKey [className=" + className + ", methodName=" + methodName + ", methodDesc=" + methodDesc + "]";
	}
	
	
	
}
