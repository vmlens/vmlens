package com.vmlens.trace.agent.bootstrap.testFixture;

import com.vmlens.trace.agent.bootstrap.event.StaticEvent;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


public class StaticEventAndId {
    private final StaticEvent event;
    private final int id;

    public StaticEventAndId(StaticEvent event, int id) {
        this.event = event;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StaticEventAndId that = (StaticEventAndId) o;

        if (id != that.id) return false;

        if (event.getClass() != that.event.getClass()) {
            return false;
        }

        return new EqualsBuilder().reflectionEquals(event, that.event);
    }

    @Override
    public int hashCode() {
        int result = event != null ? event.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(event) + ",id:" + id;
    }
}
