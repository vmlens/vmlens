package com.anarsoft.trace.agent.serialization;



public class FieldAccessDescription {
	private String name;
	private String owner;
	private int id;
	private boolean isStatic;
	private boolean isWrite;
	private boolean isTraced;
	private boolean isFinal;

	public FieldAccessDescription(String name, String owner, int id,
			boolean isStatic, boolean isWrite,boolean isTraced,boolean isFinal) {
		super();
		this.name = name;
		this.owner = owner;
		this.id = id;
		this.isStatic = isStatic;
		this.isWrite = isWrite;
		this.isTraced = isTraced;
		this.isFinal = isFinal;
	}




	public FieldAccessDescription() {
		super();
	}


	@Override
	public String toString() {
		return "FieldDescription [name=" + name + ", owner=" + owner + ", id="
				+ id + ", isStatic=" + isStatic + ", isWrite=" + isWrite + "]";
	}

	public String getName() {
		return name;
	}

	public String getOwner() {
		return owner;
	}

	public int getId() {
		return id;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public boolean isWrite() {
		return isWrite;
	}




	public boolean isTraced() {
		return isTraced;
	}




	public boolean isFinal() {
		return isFinal;
	}







}
