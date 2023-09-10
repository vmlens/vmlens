package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.util.Constants;

import java.io.DataOutputStream;

public class WhileLoopNameEvent implements StaticEvent {
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
}
