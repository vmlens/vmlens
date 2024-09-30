package com.anarsoft.trace.agent.description;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.util.Constants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;


public class ThreadDescription implements SerializableEvent, ThreadOrLoopDescription {

    private final int threadIndex;
    private final long threadId;
    private final String threadName;


    public ThreadDescription(int threadIndex, long threadId, String threadName) {
        super();
        this.threadIndex = threadIndex;
        this.threadId = threadId;
        this.threadName = threadName;

    }

    static ThreadDescription deserialize(DataInputStream inputStream) throws IOException {
        int threadIndex = inputStream.readInt();
        long threadId = inputStream.readLong();
        String threadName = inputStream.readUTF();

        return new ThreadDescription(threadIndex, threadId, threadName);
    }

    @Override
	public void serialize(StreamRepository streamRepository) throws Exception {
        DataOutputStream stream = streamRepository.threadName.getStream();
        serialize(stream);
    }

    @Override
    public void serialize(DataOutputStream stream) throws IOException {
        stream.writeInt(Constants.TYPE_THREAD_DESCRIPTION);
        stream.writeInt(threadIndex);
        stream.writeLong(threadId);
        stream.writeUTF(threadName);
    }

    @Override
    public void accept(ThreadOrLoopDescriptionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreadDescription that = (ThreadDescription) o;
        return threadIndex == that.threadIndex && threadId == that.threadId
                && Objects.equals(threadName, that.threadName);
    }

    @Override
    public int hashCode() {
        int result = threadIndex;
        result = 31 * result + (int) (threadId ^ (threadId >>> 32));
        result = 31 * result + Objects.hashCode(threadName);
        return result;
    }

}
