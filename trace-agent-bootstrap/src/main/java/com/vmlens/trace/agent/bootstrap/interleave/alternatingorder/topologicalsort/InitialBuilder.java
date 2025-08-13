package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.topologicalsort;

import com.vmlens.trace.agent.bootstrap.Pair;
import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.TopologicalOrderIterator;


public class InitialBuilder {

    private final Graph<Position, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);

    public InitialBuilder(ThreadIndexToElementList<Position> actualRun) {
        for(int index = 0; index <= actualRun.maxThreadIndex(); index++) {
            for(int pos = 0; pos < actualRun.getPositionAtThreadIndex(index); pos++ ) {
                graph.addVertex(Position.pos(index,pos));
                if(pos > 1) {
                    graph.addEdge(Position.pos(index,pos-1),Position.pos(index,pos));
                }
            }
        }
    }

    public void addOrder(LeftBeforeRight leftBeforeRight) {
        graph.addEdge(leftBeforeRight.left,leftBeforeRight.right);
    }

    /**
     * calculated run can be null
     * @return
     */
    public Pair<CalculatedRun,UpdateBuilder> build() {
        TopologicalOrderIterator<Position,DefaultEdge> iterator = new TopologicalOrderIterator<>(graph);
        return null;
    }
}
