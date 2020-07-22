package com.vmlens.trace.agent.bootstrap.interleave.normalized;

import java.util.Iterator;

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import gnu.trove.list.linked.TLinkedList;

public class GraphNode {
	TLinkedList<TLinkableWrapper<GraphEdge>> incomingNodes = new TLinkedList<TLinkableWrapper<GraphEdge>>();
	TLinkedList<TLinkableWrapper<GraphEdge>> outgoingNodes = new TLinkedList<TLinkableWrapper<GraphEdge>>();

	final Position position;
	boolean taken = false;

	public GraphNode(Position position) {
		super();
		this.position = position;
	}

	boolean hasIncomingNodes() {
		Iterator<TLinkableWrapper<GraphEdge>> it = incomingNodes.iterator();

		while (it.hasNext()) {
			TLinkableWrapper<GraphEdge> current = it.next();
			if (!current.element.deleted) {
				return true;
			}

		}

		return false;

	}

	@Override
	public String toString() {
		return "GraphNode [incomingNodes=" + incomingNodes + ", outgoingNodes=" + outgoingNodes + ", position="
				+ position + ", taken=" + taken + "]";
	}
}
