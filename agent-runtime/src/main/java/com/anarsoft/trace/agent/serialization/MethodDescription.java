package com.anarsoft.trace.agent.serialization;



public class MethodDescription {

	private String name;
	private String desc;
	private int id;
	private int access;
	private int lineNumber;
	
	
	
	private FieldAccessDescription[]  fieldArray;

	
	


	public MethodDescription(String name, int id, FieldAccessDescription[] fieldArray, String desc,int access,int lineNumber) {
		super();
		this.name = name;
		this.desc = desc;
		this.id = id;
		this.fieldArray = fieldArray;
	
		
		
		this.access = access;
		this.lineNumber = lineNumber;
		

		
	}


	
	








	public MethodDescription() {
		super();
		// TODO Auto-generated constructor stub
	}





	public int getLineNumber() {
		return lineNumber;
	}





	@Override
	public String toString() {
		return "MethodDescription [name=" + name + ", id=" + id
				+ ", fieldArray="  + "]";
	}


	public String getName() {
		return name;
	}


	public int getId() {
		return id;
	}


	public FieldAccessDescription[] getFieldArray() {
		return fieldArray;
	}





	public String getDesc() {
		return desc;
	}





	public int getAccess() {
		return access;
	}








}
