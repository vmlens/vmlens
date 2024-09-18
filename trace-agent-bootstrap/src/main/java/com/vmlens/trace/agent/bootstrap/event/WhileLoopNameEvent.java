package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.util.Constants;

import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import java.util.Objects;

public class WhileLoopNameEvent implements SerializableEvent {
    private final int loopId;
    private final String name;

    public WhileLoopNameEvent(int loopId, String name) {
        super();
        this.loopId = loopId;
        this.name = name;
    }

    @Override
    public void serialize(StreamRepository streamRepository) throws Exception {
        DataOutputStream stream = streamRepository.threadName.getStream();
        stream.writeInt(Constants.TYPE_WHILE_LOOP_NAME_EVENT);
        stream.writeInt(loopId);
        stream.writeUTF(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WhileLoopNameEvent that = (WhileLoopNameEvent) o;

        if (loopId != that.loopId) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = loopId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WhileLoopNameEvent{" +
                "loopId=" + loopId +
                ", name='" + name + '\'' +
                '}';
    }
}
