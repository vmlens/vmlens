package com.vmlens.trace.agent.bootstrap.interleave.normalized;

import com.vmlens.trace.agent.bootstrap.interleave.CommandList;
import com.vmlens.trace.agent.bootstrap.interleave.MonitorState;
import com.vmlens.trace.agent.bootstrap.util.ObjectStack;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.TLinkable;
import gnu.trove.list.linked.TIntLinkedList;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.set.hash.TIntHashSet;

import java.util.Iterator;

public class OrderedList implements TLinkable<OrderedList> {

	private OrderedList next;
	private OrderedList previous;

	private final int[] maxPositionPerThread;
	private final RelationList.StackNodeRelation stackNode;
	private final int position;
	private final RelationArray relationArray;

	public OrderedList(int[] maxPositionPerThread, RelationList.StackNodeRelation stackNode, RelationArray relationArray,
                       int position) {
		super();
		this.maxPositionPerThread = maxPositionPerThread;
		this.stackNode = stackNode;
		this.position = position;
		this.relationArray = relationArray;
	}

	public OrderedList getNext() {
		return next;
	}

	public void setNext(OrderedList next) {
		this.next = next;
	}

	public OrderedList getPrevious() {
		return previous;
	}

	public void setPrevious(OrderedList previous) {
		this.previous = previous;
	}

	
	

	private final static class StackNode  {

		private StackNode next;
		private StackNode previous;

		public StackNode getNext() {
			return next;
		}

		public final Position position;

		public StackNode(Position position) {
			super();
			this.position = position;
		}

		public void setNext(StackNode next) {
			this.next = next;
		}

		public StackNode getPrevious() {
			return previous;
		}

		public void setPrevious(StackNode previous) {
			this.previous = previous;
		}

		@Override
		public String toString() {
			return "StackNode [position=" + position + "]";
		}

	}

	private GraphNode[][] createGraph() {

		GraphNode[][] graph = (GraphNode[][]) new GraphNode[maxPositionPerThread.length][];

		for (int x = 0; x < maxPositionPerThread.length; x++) {

			graph[x] = new GraphNode[maxPositionPerThread[x]];

			if (maxPositionPerThread[x] == 0) {
				graph[x] = new GraphNode[1];
				graph[x][0] = new GraphNode(new Position(x, 0));
			}

			GraphNode prevoiusInThread = null;

			for (int y = 0; y < maxPositionPerThread[x]; y++) {
				graph[x][y] = new GraphNode(new Position(x, y));

				if (prevoiusInThread != null) {
					GraphEdge graphEdge = new GraphEdge(graph[x][y].position);
					graph[x][y].incomingNodes.add(new TLinkableWrapper(graphEdge));
					prevoiusInThread.outgoingNodes.add(new TLinkableWrapper(graphEdge));
				}

				prevoiusInThread = graph[x][y];
			}

		}
		

		Iterator<TLinkableWrapper<LeftBeforeRight>> iterator = relationArray.startOrders.iterator();

		while (iterator.hasNext()) {
			LeftBeforeRight current = iterator.next().element;
			addEdge(graph, current.left , current.right);
		}
		
	

		Iterator<TLinkableWrapper<LeftBeforeRight>> iteratorJoin = relationArray.joinThread.iterator();

		while (iteratorJoin.hasNext()) {
			
			LeftBeforeRight current = iteratorJoin.next().element;
			addEdge(graph,current.left , current.right);
		}

		
		
		for (int i = 0; i <= position; i++) {
			
			if( isEnabled(i))
			{
				 PotentialOrder current = relationArray.array[i].element;
					
				 current.addEdge(graph, stackNode.get(i));
				 
			
					
				
			}
			
		

		}

		return graph;

	}

	public static void addEdge(GraphNode[][] graph, Position left , Position right  ) {

		
	if( left.position != -1 &&  right.position != -1)
	{
		GraphEdge graphEdge = new GraphEdge(right);
		graph[left.threadIndex][left.position].outgoingNodes
				.add(new TLinkableWrapper(graphEdge));
		graph[right.threadIndex][right.position].incomingNodes
				.add(new TLinkableWrapper(graphEdge));
	}
//	else
//	{
//		AgentLogCallback.logException( new Exception("left.position " +  left.position + " right.position " + right.position) );
//	}
	
	}

//	private LeftBeforeRight getAtIndex(int index) {
//
//		int positionInArray = index * 2;
//
//		if (stackNode.get(index)) {
//			return relationArray.array[positionInArray].element;
//		} else {
//			return relationArray.array[positionInArray + 1].element;
//		}
//	}
//	
	private boolean isEnabled(int index)
	{
		return stackNode.isEnabled(index);
	}
	
	

	/*
	 * 
	 * kann null zurückliefern wenn keine liste erzeugt werden konnte
	 * 
	 * @param normalizedList
	 * @return
	 */

	public CommandList create(TIntHashSet knownVolatileFields, MonitorState[][] threadIndex2Position2MonitorArray) {

		GraphNode[][] graph = createGraph();

		/*
		 * https://en.wikipedia.org/wiki/Topological_sorting
		 * 
		 * L ← Empty list that will contain the sorted elements S ← Set of all nodes
		 * with no incoming edge while S is non-empty do remove a node n from S add n to
		 * tail of L for each node m with an edge e from n to m do remove edge e from
		 * the graph if m has no other incoming edges then insert m into S if graph has
		 * edges then return error (graph has at least one cycle) else return L (a
		 * topologically sorted order)
		 * 
		 * Reflecting the non-uniqueness of the resulting sort, the structure S can be
		 * simply a set or a queue or a stack
		 */

		ObjectStack<StackNode> stack = new ObjectStack<StackNode>();

		int maxSize = 0;

		for (int x = 0; x < graph.length; x++) {
			for (int y = 0; y < graph[x].length; y++) {
				GraphNode node = graph[x][y];

				if (!node.hasIncomingNodes()) {
					node.taken = true;
					stack.push(new StackNode(new Position(x, y)));
				}

				maxSize++;
			}
		}

		TLinkedList<TLinkableWrapper<Position>> result = new TLinkedList<TLinkableWrapper<Position>>();

		int currentThreadIndex = 0;

		while (!stack.isEmpty()) {
			StackNode current = null;

			 Iterator<TLinkableWrapper<StackNode>> iter = stack.iterator();

			while (iter.hasNext() && current == null) {
				StackNode x = iter.next().element;

				if (x.position.threadIndex == currentThreadIndex) {
					iter.remove();
					current = x;
				}

			}

			if (current == null) {
				current = stack.poll();
			}

			currentThreadIndex = current.position.threadIndex;

			result.add(new TLinkableWrapper(current.position));

			TLinkedList<TLinkableWrapper<GraphEdge>> nToM = graph[current.position.threadIndex][current.position.position].outgoingNodes;
			Iterator<TLinkableWrapper<GraphEdge>> it = nToM.iterator();

			Position first = null;

			while (it.hasNext()) {
				TLinkableWrapper<GraphEdge> e = it.next();
				e.element.deleted = true;

				GraphNode node = graph[e.element.outgoingTarget.threadIndex][e.element.outgoingTarget.position];

				if (!node.hasIncomingNodes()) {
					// sollte dasselbe sein wie e.element.outgoingTarget
					node.taken = true;

					// if( node.position.threadIndex == current.position.threadIndex )
					// {
					// first = node.position;
					// }
					// else
					{

						stack.push(new StackNode(node.position));
					}

				}

			}

			if (first != null) {
				stack.push(new StackNode(first));
			}

		}

		for (int x = 0; x < graph.length; x++) {
			for (int y = 0; y < graph[x].length; y++) {
				GraphNode node = graph[x][y];
			}
		}

		/*
		 * prüfen ob es zyklen gab (alles berechnet)
		 * 
		 */

		for (int x = 0; x < graph.length; x++) {
			for (int y = 0; y < graph[x].length; y++) {
				GraphNode node = graph[x][y];

				if (!node.taken) {
					return null;
				}

			}
		}

		/*
		 * array aus last position pro thread index steps erzeugen
		 * 
		 */
		int[] lastPosition = new int[maxPositionPerThread.length];

		TIntLinkedList threadIds = new TIntLinkedList();

		Iterator<TLinkableWrapper<Position>> it = result.iterator();

		while (it.hasNext()) {
			TLinkableWrapper<Position> current = it.next();
			int i = lastPosition[current.element.threadIndex];
			for (; i <= current.element.position; i++) {
				threadIds.add(current.element.threadIndex);

			}

			lastPosition[current.element.threadIndex] = i;

		}

		TIntIterator iter = threadIds.iterator();

		while (iter.hasNext()) {
			int index = iter.next();

		}
		// check if possible

		iter = threadIds.iterator();
		int[] currentPosition = new int[maxPositionPerThread.length];

		for (int i = 0; i < currentPosition.length; i++) {
			currentPosition[i] = -1;
		}

		while (iter.hasNext()) {
			int index = iter.next();

			if (currentPosition[index] == -1) {
				currentPosition[index] = 0;
			} else {
				currentPosition[index]++;

				// check if possible
				MonitorState monitors = threadIndex2Position2MonitorArray[index][currentPosition[index]];

				if (monitors != null) {
					for (int i = 0; i < currentPosition.length; i++) {
						if (i != index && currentPosition[i] != -1) {
							
							if( currentPosition[i] < threadIndex2Position2MonitorArray[i].length )
							{
								MonitorState other = threadIndex2Position2MonitorArray[i][currentPosition[i]];

								if (other != null) {

									if (monitors.contains(other)) {
										
										return null;

									}
								}
							}
							
				

						}

					}

				}

			}

		}

		return new CommandList(threadIds.toArray(), knownVolatileFields);

	}

}
