package com.anarsoft.trace.agent.description;

import com.vmlens.trace.agent.bootstrap.OptionalByte;
import com.vmlens.trace.agent.bootstrap.OptionalShort;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.util.Constants;

import java.io.DataOutputStream;


public class ThreadNameDescription implements SerializableEvent {

    private final long threadId;
    private final String threadName;
    private final boolean hasMappedThreadId;
    private final byte mappedThreadId;
    private final boolean hasShortThreadId;
    private final short shortThreadId;

    public ThreadNameDescription(long threadId, String threadName, OptionalByte optionalByte, OptionalShort optionalShort) {
        super();
        this.threadId = threadId;
        this.threadName = threadName;
        this.hasMappedThreadId = optionalByte.isHasByte();
        this.mappedThreadId = optionalByte.getTheValue();
        this.hasShortThreadId = optionalShort.isHasShort();
        this.shortThreadId = optionalShort.getTheValue();
    }

	public void serialize(StreamRepository streamRepository) throws Exception {
        DataOutputStream stream = streamRepository.threadName.getStream();
        stream.writeInt(Constants.TYPE_THREAD_NAME_EVENT);
        stream.writeLong(threadId);
        stream.writeUTF(threadName);
        stream.writeBoolean(hasMappedThreadId);
        stream.writeByte(mappedThreadId);
        stream.writeBoolean(hasShortThreadId);
        stream.writeShort(shortThreadId);
        stream.flush();
    }

}
