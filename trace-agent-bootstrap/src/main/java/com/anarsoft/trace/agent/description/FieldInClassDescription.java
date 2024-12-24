package com.anarsoft.trace.agent.description;


import java.util.Objects;

public class FieldInClassDescription {
	
	private final int id;
	private final int access;
	private final String name;
	private final String desc;
	private final String signature;

    public FieldInClassDescription(int id, int access, String name, String desc, String signature) {
		super();
		this.id = id;
		this.access = access;
		this.name = name;
		this.desc = desc;
		this.signature = signature;
	}

	public int access() {
		return access;
	}

	public String name() {
		return name;
	}

	public String desc() {
		return desc;
	}

	public String signature() {
		return signature;
	}

	public int id() {
		return id;
	}

	@Override
	public String toString() {
		return "FieldInClassDescription{" +
				"id=" + id +
				", access=" + access +
				", name='" + name + '\'' +
				", desc='" + desc + '\'' +
				", signature='" + signature + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FieldInClassDescription that = (FieldInClassDescription) o;
		return id == that.id && access == that.access && Objects.equals(name, that.name)
				&& Objects.equals(desc, that.desc) && Objects.equals(signature, that.signature);
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + access;
		result = 31 * result + Objects.hashCode(name);
		result = 31 * result + Objects.hashCode(desc);
		result = 31 * result + Objects.hashCode(signature);
		return result;
	}
}
