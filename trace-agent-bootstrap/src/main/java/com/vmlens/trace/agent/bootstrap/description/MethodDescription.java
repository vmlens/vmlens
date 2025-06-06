package com.vmlens.trace.agent.bootstrap.description;


import java.util.Objects;

public class MethodDescription {

	private final String name;
	private final String desc;
	private final int id;
	private final int access;
	private final int lineNumber;

    public MethodDescription(String name, int id,
							 String desc, int access, int lineNumber) {
		super();
		this.name = name;
		this.desc = desc;
		this.id = id;
		this.access = access;
		this.lineNumber = lineNumber;
	}

	public int lineNumber() {
		return lineNumber;
	}

	public String name() {
		return name;
	}

	public int id() {
		return id;
	}



	public String desc() {
		return desc;
	}

	public int access() {
		return access;
	}

	@Override
	public String toString() {
		return "MethodDescription{" +
				"name='" + name + '\'' +
				", desc='" + desc + '\'' +
				", id=" + id +
				", access=" + access +
				", lineNumber=" + lineNumber +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MethodDescription that = (MethodDescription) o;
		return id == that.id && access == that.access && lineNumber == that.lineNumber
                && Objects.equals(name, that.name) && Objects.equals(desc, that.desc);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(name);
		result = 31 * result + Objects.hashCode(desc);
		result = 31 * result + id;
		result = 31 * result + access;
		result = 31 * result + lineNumber;
		return result;
	}
}
