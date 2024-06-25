package com.vmlens.trace.agent.bootstrap.event;

import java.io.DataOutputStream;
import java.nio.ByteBuffer;

public class AgentLogEvent implements SerializableEvent {

    private final String message;

    public AgentLogEvent(String message) {
        super();
        int maxLength = Math.min(6000, message.length());
        this.message = message.substring(0, maxLength);
    }

    @Override
    public void serialize(StreamRepository streamRepository) throws Exception {
        DataOutputStream stream = streamRepository.agentLog.getStream();
        stream.writeUTF(message);
        stream.flush();
    }

    @Override
    public void serialize(ByteBuffer buffer) throws Exception {
        // ToDo
    }
}
