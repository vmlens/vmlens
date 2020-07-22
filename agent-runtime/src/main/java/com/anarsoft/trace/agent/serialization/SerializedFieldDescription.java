package com.anarsoft.trace.agent.serialization;

import com.vmlens.shaded.gnu.trove.list.TLinkableAdapter;

public class SerializedFieldDescription  extends TLinkableAdapter<SerializedFieldDescription> {
	
	private final int id;
	private final int access;
	private final String name;
	private final String desc;
	private final String signature;
	
	public SerializedFieldDescription( int id , int access, String name, String desc, String signature) {
		super();
		this.id = id;
		this.access = access;
		this.name = name;
		this.desc = desc;
		this.signature = signature;
	}

	public int getAccess() {
		return access;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public String getSignature() {
		return signature;
	}

	public int getId() {
		return id;
	}
	
	
	
	
	
	
}
