package com.vmlens.trace.agent.bootstrap.description;

import com.vmlens.trace.agent.bootstrap.event.LatestWrittenLoopAndRunId;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;


public class ThreadDescription implements SerializableEvent, ThreadOrLoopDescription {

    private final int loopId;
    private final int runId;
    private final int threadIndex;
    private final long threadId;
    private final String threadName;

    public ThreadDescription(int loopId, int runId, int threadIndex, long threadId, String threadName) {
        this.loopId = loopId;
        this.runId = runId;
        this.threadIndex = threadIndex;
        this.threadId = threadId;
        this.threadName = threadName;
    }

    static ThreadDescription deserialize(DataInputStream inputStream) throws IOException {
        int loopId = inputStream.readInt();
        int runId = inputStream.readInt();
        int threadIndex = inputStream.readInt();
        long threadId = inputStream.readLong();
        String threadName = inputStream.readUTF();

        return new ThreadDescription(loopId, runId, threadIndex, threadId, threadName);
    }

    @Override
	public void serialize(StreamRepository streamRepository, LatestWrittenLoopAndRunId latestWrittenLoopAndRunId) throws Exception {
        DataOutputStream stream = streamRepository.threadName.getStream();
        serialize(stream);
    }

    @Override
    public void serialize(DataOutputStream stream) throws IOException {
        stream.writeInt(Constants.TYPE_THREAD_DESCRIPTION);
        stream.writeInt(loopId);
        stream.writeInt(runId);
        stream.writeInt(threadIndex);
        stream.writeLong(threadId);
        stream.writeUTF(threadName);
    }

    @Override
    public void accept(ThreadOrLoopDescriptionVisitor visitor) {
        visitor.visit(this);
    }

    public int loopId() {
        return loopId;
    }

    public int runId() {
        return runId;
    }

    public int threadIndex() {
        return threadIndex;
    }

    public long threadId() {
        return threadId;
    }

    public String threadName() {
        return threadName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreadDescription that = (ThreadDescription) o;
        return loopId == that.loopId && runId == that.runId && threadIndex == that.threadIndex && threadId == that.threadId && Objects.equals(threadName, that.threadName);
    }

    @Override
    public int hashCode() {
        int result = loopId;
        result = 31 * result + runId;
        result = 31 * result + threadIndex;
        result = 31 * result + (int) (threadId ^ (threadId >>> 32));
        result = 31 * result + Objects.hashCode(threadName);
        return result;
    }

    @Override
    public String toString() {
        return "ThreadDescription{" +
                "loopId=" + loopId +
                ", runId=" + runId +
                ", threadIndex=" + threadIndex +
                ", threadId=" + threadId +
                ", threadName='" + threadName + '\'' +
                '}';
    }
}
