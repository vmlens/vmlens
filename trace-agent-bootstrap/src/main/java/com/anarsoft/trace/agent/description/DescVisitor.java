package com.anarsoft.trace.agent.description;

public interface DescVisitor {
	
	void visitClassDescription(String name);
	void visitMethodDescription(String name,int id,String desc,int access);
	void visitFieldAccessDescription(String name, String owner, int id,
			boolean isStatic, boolean isWrite,boolean isTraced,boolean isFinal);
	void visitFieldDescription(String name, int id, String desc, int access);

}
