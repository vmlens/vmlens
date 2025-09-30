package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import gnu.trove.set.hash.THashSet;
import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.alg.cycle.SzwarcfiterLauerSimpleCycles;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.TopologicalOrderIterator;

import java.util.List;


public class CalculatedRunBuilder {

    private final Graph<Position, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
    private final int count;

    public CalculatedRunBuilder(ThreadIndexToElementList<Position> actualRun) {
        int counter = 0;
        for(int index = 0; index <= actualRun.maxThreadIndex(); index++) {
            for(int pos = 0; pos < actualRun.getPositionAtThreadIndex(index); pos++ ) {
                graph.addVertex(Position.pos(index,pos));
                counter++;
                if(pos >= 1) {
                    graph.addEdge(Position.pos(index,pos-1),Position.pos(index,pos));
                }
            }
        }
        this.count = counter;
    }

    public void addOrder(LeftBeforeRight leftBeforeRight) {
        graph.addEdge(leftBeforeRight.left,leftBeforeRight.right);
    }
    /**
     * calculated run can be null
     * @return
     */
    public CalculatedRun build() {
        Position[] array = new Position[count];
        TopologicalOrderIterator<Position,DefaultEdge> iterator = new TopologicalOrderIterator<>(graph);
        int index = 0;
        try{
            while(iterator.hasNext()) {
                array[index] =   iterator.next();
                index++;
            }
            return new CalculatedRun(array);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }

    }

    public THashSet<Position> buildCycles(THashSet<Position> previousStartingPoints) {
        if(previousStartingPoints.isEmpty()) {
           return buildAllCycles();
        }

        for(Position pos: previousStartingPoints) {
            if(new CycleDetector<>(graph).detectCyclesContainingVertex(pos)) {
                return previousStartingPoints;
            }
        }
      return new THashSet<>();
    }

    public THashSet<Position> buildAllCycles() {
        THashSet<Position> startingPoints = new THashSet<>();
        List<List<Position>> cycleList = new SzwarcfiterLauerSimpleCycles<>(graph).findSimpleCycles();
        for(List<Position>  list : cycleList ) {
            startingPoints.addAll(list);
        }
        return startingPoints;
    }

}
