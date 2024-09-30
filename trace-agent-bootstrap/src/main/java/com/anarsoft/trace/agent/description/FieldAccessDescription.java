package com.anarsoft.trace.agent.description;

import java.util.Objects;

public class FieldAccessDescription {
	private final String name;
	private final String owner;
	private final int id;
	private final boolean isStatic;
	private final boolean isWrite;
	private final boolean isTraced;
	private final boolean isFinal;

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

	@Override
	public String toString() {
		return "FieldDescription [name=" + name + ", owner=" + owner + ", id="
				+ id + ", isStatic=" + isStatic + ", isWrite=" + isWrite + "]";
	}

	public String name() {
		return name;
	}

	public String owner() {
		return owner;
	}

	public int id() {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FieldAccessDescription that = (FieldAccessDescription) o;
		return id == that.id && isStatic == that.isStatic && isWrite == that.isWrite
				&& isTraced == that.isTraced && isFinal == that.isFinal && Objects.equals(name, that.name)
				&& Objects.equals(owner, that.owner);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(name);
		result = 31 * result + Objects.hashCode(owner);
		result = 31 * result + id;
		result = 31 * result + (isStatic ? 1 : 0);
		result = 31 * result + (isWrite ? 1 : 0);
		result = 31 * result + (isTraced ? 1 : 0);
		result = 31 * result + (isFinal ? 1 : 0);
		return result;
	}
}
