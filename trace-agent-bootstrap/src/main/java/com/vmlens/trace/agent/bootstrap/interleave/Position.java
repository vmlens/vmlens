package com.vmlens.trace.agent.bootstrap.interleave;

public class Position implements Comparable<Position> {

    public final int threadIndex;
    public final int positionInThread;

    public Position(int threadIndex, int positionInThread) {
        this.threadIndex = threadIndex;
        this.positionInThread = positionInThread;
    }

    @Override
    public String toString() {
        return "p(" + threadIndex + "," +
                positionInThread + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (threadIndex != position.threadIndex) return false;
        return positionInThread == position.positionInThread;
    }

    @Override
    public int hashCode() {
        int result = threadIndex;
        result = 31 * result + positionInThread;
        return result;
    }

    public static Position p(int threadIndex, int positionInThread) {
        return new Position(threadIndex, positionInThread);
    }

    @Override
    public int compareTo(Position other) {
        if (threadIndex != other.threadIndex) {
            return Integer.compare(threadIndex, other.threadIndex);
        }
        if (positionInThread != other.positionInThread) {
            return Integer.compare(positionInThread, other.positionInThread);
        }
        return 0;
    }
}
