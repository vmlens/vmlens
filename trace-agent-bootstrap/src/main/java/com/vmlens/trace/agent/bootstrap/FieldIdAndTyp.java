package com.vmlens.trace.agent.bootstrap;

public class FieldIdAndTyp {
	
	public final int id;
	public final FieldTyp fieldTyp;
	
	public FieldIdAndTyp(int id, FieldTyp fieldTyp) {
		super();
		this.id = id;
		this.fieldTyp = fieldTyp;
	}

	@Override
	public String toString() {
		return "FieldIdAndTyp [id=" + id + ", fieldTyp=" + fieldTyp + "]";
	}
	
	
	
	
	

}
