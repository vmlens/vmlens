package com.vmlens.trace.agent.bootstrap.interleave.lock;

public class Monitor implements Lock {
    private final MonitorKey monitorKey;

    public Monitor(long objectHashCode) {
        this.monitorKey = new MonitorKey(objectHashCode);
    }

    public MonitorKey key() {
        return monitorKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Monitor monitor = (Monitor) o;

        return monitorKey.equals(monitor.monitorKey);
    }

    @Override
    public int hashCode() {
        return monitorKey.hashCode();
    }

    @Override
    public boolean startsAlternatingOrder(Lock lockOrMonitor) {
        return true;
    }
}
