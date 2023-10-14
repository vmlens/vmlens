package com.vmlens.trace.agent.bootstrap.event;

import java.io.DataOutputStream;

public class AgentLogEvent implements StaticEvent  {

    private final String message;

	public AgentLogEvent(String message) {
		super();
		int maxLength = Math.min(  6000 ,  message.length() );
		this.message = message.substring(0, maxLength);
	}

	@Override
	public void serialize(StreamRepository streamRepository) throws Exception {
	
		DataOutputStream stream = streamRepository.agentLog.getStream();
		stream.writeUTF(message);
		stream.flush();
	}
}
