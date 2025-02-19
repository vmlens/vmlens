package com.anarsoft.trace.agent.runtime.write;


import com.anarsoft.trace.agent.description.ClassDescription;
import com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEvent;

import static com.vmlens.trace.agent.bootstrap.event.queue.EventQueueSingleton.eventQueue;

public class WriteClassDescriptionAndWarningNormal implements WriteClassDescriptionAndWarning {

    public void write(final ClassDescription classDescription) {
        eventQueue.offer(classDescription);
    }

    @Override
    public void write(InfoMessageEvent infoMessageEvent) {
        eventQueue.offer(infoMessageEvent);
    }
}
