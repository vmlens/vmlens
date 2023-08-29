package com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor;

public class Monitor implements LockOrMonitor {
    private final MonitorKey monitorKey;

    public Monitor(int id) {
        this.monitorKey = new MonitorKey(id);
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
    public boolean startsAlternatingOrder(LockOrMonitor lockOrMonitor) {
        return true;
    }
}
