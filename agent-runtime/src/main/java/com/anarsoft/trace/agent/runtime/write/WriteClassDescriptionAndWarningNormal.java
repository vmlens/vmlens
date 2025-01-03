package com.anarsoft.trace.agent.runtime.write;


import com.anarsoft.trace.agent.description.ClassDescription;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEvent;

public class WriteClassDescriptionAndWarningNormal implements WriteClassDescriptionAndWarning {

    public void write(final ClassDescription classDescription) {
        ThreadLocalWhenInTestAdapterImpl.eventQueue.offer(classDescription);
    }

    @Override
    public void write(InfoMessageEvent infoMessageEvent) {
        ThreadLocalWhenInTestAdapterImpl.eventQueue.offer(infoMessageEvent);
    }
}
