package com.vmlens.trace.agent.bootstrap.interleave.normalized;

public class GraphEdge {
	final Position outgoingTarget;
	boolean deleted;

	public GraphEdge(Position outgoingTarget) {
		super();
		this.outgoingTarget = outgoingTarget;
	}

	@Override
	public String toString() {
		return "GraphEdge [outgoingTarget=" + outgoingTarget + ", deleted=" + deleted + "]";
	}

}
