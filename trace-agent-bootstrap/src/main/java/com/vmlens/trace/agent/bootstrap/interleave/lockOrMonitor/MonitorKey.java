package com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor;

public class MonitorKey implements LockOrMonitorKey {

    private final int id;

    public MonitorKey(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonitorKey that = (MonitorKey) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id * 234;
    }
}
