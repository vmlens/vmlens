package com.anarsoft.trace.agent.description;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.util.Constants;

import java.io.DataOutputStream;


public class ThreadNameDescription implements SerializableEvent {

    private final int threadIndex;
    private final long threadId;
    private final String threadName;


    public ThreadNameDescription(int threadIndex, long threadId, String threadName) {
        super();
        this.threadIndex = threadIndex;
        this.threadId = threadId;
        this.threadName = threadName;

    }

	public void serialize(StreamRepository streamRepository) throws Exception {
        DataOutputStream stream = streamRepository.threadName.getStream();
        stream.writeInt(Constants.TYPE_THREAD_NAME_EVENT);
        stream.writeInt(threadIndex);
        stream.writeLong(threadId);
        stream.writeUTF(threadName);
    }

}
