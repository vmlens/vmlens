package com.vmlens.trace.agent.bootstrap.interleave.normalized;

public class PotentialOrderSingle implements PotentialOrder {

	private final LeftBeforeRight a;
	private final LeftBeforeRight b;
	
	public PotentialOrderSingle(LeftBeforeRight a, LeftBeforeRight b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return "PotentialOrder [a=" + a + ", b=" + b + "]";
	}

	@Override
	public void addEdge(GraphNode[][] graph, boolean takeA) {
		
		if(takeA)
		{
			OrderedList.addEdge(graph,a.left , a.right);
		}
		else
		{
			OrderedList.addEdge(graph,b.left , b.right);
		}
		
		
		
	}
	
	
}
