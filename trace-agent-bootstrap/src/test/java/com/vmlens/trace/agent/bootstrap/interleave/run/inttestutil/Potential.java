package com.vmlens.trace.agent.bootstrap.interleave.run.inttestutil;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Potential {

    private final List<PotentialElement> potentialElements;

    public Potential(List<PotentialElement> potentialElements) {
        this.potentialElements = potentialElements;
    }

    public static Integer increment(Object key, Integer value) {
        if(value == null) {
            return  1;
        }
        else {
            return value + 1;
        }
    }


    public void next(Position position, Map<ExpectedElement,Integer> fulfilled) {
        Iterator<PotentialElement> iter = potentialElements.iterator();
        while(iter.hasNext()) {
            PotentialElement element = iter.next();
            if(element.next(position)) {
                if(element.fulfilled()) {
                    fulfilled.compute(element.asExpected(), Potential::increment);
                    iter.remove();
                }
            } else {
                iter.remove();
            }
        }
    }
}
