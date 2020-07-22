package com.vmlens.trace.agent.bootstrap.interleave.normalized;

public class PotentialOrderTwice implements PotentialOrder {

	private final LeftBeforeRight a;
	private final LeftBeforeRight b;
	
	public PotentialOrderTwice(LeftBeforeRight a, LeftBeforeRight b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public void addEdge(GraphNode[][] graph, boolean takeA) {
		
		if(takeA)
		{
			OrderedList.addEdge(graph,a.left , a.right);
			
			OrderedList.addEdge(graph, a.right , new Position( a.left.threadIndex ,a.left.position + 1  ));
			
		}
		else
		{
			OrderedList.addEdge(graph,b.left , b.right);
			
			OrderedList.addEdge(graph, b.right , new Position( b.left.threadIndex ,b.left.position + 1  ));
			
		}
		
		
	}
	
	
}
