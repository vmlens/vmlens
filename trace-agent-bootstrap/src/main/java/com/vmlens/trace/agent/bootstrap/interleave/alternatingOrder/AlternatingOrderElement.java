package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;

import java.util.Objects;

public class AlternatingOrderElement implements Comparable<AlternatingOrderElement> {

    private final LeftBeforeRight alternativeOne;
    private final LeftBeforeRight alternativeTwo;

    public AlternatingOrderElement(LeftBeforeRight alternativeOne, LeftBeforeRight alternativeTwo) {
        if(alternativeOne.compareTo(alternativeTwo) > 0) {
            this.alternativeOne = alternativeOne;
            this.alternativeTwo = alternativeTwo;
        } else {
            this.alternativeOne = alternativeTwo;
            this.alternativeTwo = alternativeOne;
        }
    }
    public LeftBeforeRight order(boolean firstAlternative) {
        if(firstAlternative) {
            return alternativeOne;
        }
        return alternativeTwo;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlternatingOrderElement that = (AlternatingOrderElement) o;

        if (!Objects.equals(alternativeOne, that.alternativeOne))
            return false;
        return Objects.equals(alternativeTwo, that.alternativeTwo);
    }
    @Override
    public int hashCode() {
        int result = alternativeOne != null ? alternativeOne.hashCode() : 0;
        result = 31 * result + (alternativeTwo != null ? alternativeTwo.hashCode() : 0);
        return result;
    }
    @Override
    public int compareTo(AlternatingOrderElement other) {
        if( alternativeOne.compareTo(other.alternativeOne) > 0) {
            return alternativeOne.compareTo(other.alternativeOne);
        }
        return alternativeTwo.compareTo(other.alternativeTwo);
    }

    @Override
    public String toString() {
        if (alternativeOne.equals(alternativeTwo.inverse())) {
            return "both" + alternativeOne;
        }
        return alternativeOne + "or" + alternativeTwo;
    }
}
