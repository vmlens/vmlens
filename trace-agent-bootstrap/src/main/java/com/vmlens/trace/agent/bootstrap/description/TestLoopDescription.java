package com.vmlens.trace.agent.bootstrap.description;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

public class TestLoopDescription implements SerializableEvent, ThreadOrLoopDescription {
    private final int loopId;
    private final String name;
    private final int reportAsSummaryThreshold;

    public TestLoopDescription(int loopId, String name, int reportAsSummaryThreshold) {
        super();
        this.loopId = loopId;
        this.name = name;
        this.reportAsSummaryThreshold = reportAsSummaryThreshold;
    }

    static TestLoopDescription deserialize(DataInputStream inputStream) throws IOException {
        int loopId = inputStream.readInt();
        String name = inputStream.readUTF();
        int reportAsSummaryThreshold = inputStream.readInt();
        return new TestLoopDescription(loopId, name,reportAsSummaryThreshold);
    }

    @Override
    public void serialize(StreamRepository streamRepository) throws Exception {
        DataOutputStream stream = streamRepository.threadName.getStream();
        serialize(stream);
    }

    @Override
    public void serialize(DataOutputStream stream) throws IOException {
        stream.writeInt(Constants.TYPE_WHILE_LOOP_DESCRIPTION);
        stream.writeInt(loopId);
        stream.writeUTF(name);
        stream.writeInt(reportAsSummaryThreshold);
    }

    @Override
    public void accept(ThreadOrLoopDescriptionVisitor visitor) {
        visitor.visit(this);
    }

    public int loopId() {
        return loopId;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestLoopDescription that = (TestLoopDescription) o;

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
