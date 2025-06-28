package com.vmlens.report.assertion;

public class Position {

    private final int threadIndex;
    private final int runPosition;

    public Position(int threadIndex, int runPosition) {
        this.threadIndex = threadIndex;
        this.runPosition = runPosition;
    }

    @Override
    public String toString() {
        return "Position{" +
                "threadIndex=" + threadIndex +
                ", runPosition=" + runPosition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (threadIndex != position.threadIndex) return false;
        return runPosition == position.runPosition;
    }

    @Override
    public int hashCode() {
        int result = threadIndex;
        result = 31 * result + runPosition;
        return result;
    }

    public int threadIndex() {
        return threadIndex;
    }

    public int runPosition() {
        return runPosition;
    }
}
