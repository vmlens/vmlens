package com.vmlens.trace.agent.bootstrap.interleave.inttest.util;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

import java.util.List;

public class PotentialElement {

    private final List<IntTestOperation> intTestOperations;
    private int currentPosition;

    public PotentialElement(List<IntTestOperation> intTestOperations) {
        this.intTestOperations = intTestOperations;
    }

    public ExpectedElement asExpected() {
        return new ExpectedElement(intTestOperations);
    }

    public boolean next(Position position) {
        if(intTestOperations.get(currentPosition).asPosition().equals(position)) {
            currentPosition++;
            return true;
        }

        boolean contained = false;
        for(int i = currentPosition; i < intTestOperations.size(); i++) {
            if(intTestOperations.get(i).asPosition().equals(position)) {
                contained = true;
            }
        }
        return ! contained;
    }

    public boolean fulfilled() {
        return currentPosition == intTestOperations.size();
    }
}
