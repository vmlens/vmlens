package com.anarsoft.trace.agent.runtime.repositorydeprecated;




public class MethodInClassIdentifier {
	
	private final String className;
	private final String methodName;
	private final String desc;
	
	
	
	
	
	
	public String getClassName() {
		return className;
	}


	public String getMethodName() {
		return methodName;
	}


	public String getDesc() {
		return desc;
	}


	public MethodInClassIdentifier(String className, String methodName, String desc) {
		super();
		this.className = className;
		this.methodName = methodName;
		this.desc = desc;
	}


	
	
	
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
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
		MethodInClassIdentifier other = (MethodInClassIdentifier) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
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
		return "MethodInClassIdentifier [className=" + className + ", methodName=" + methodName + ", desc=" + desc
				+ "]";
	}
	
	

	
	
	
	

}


