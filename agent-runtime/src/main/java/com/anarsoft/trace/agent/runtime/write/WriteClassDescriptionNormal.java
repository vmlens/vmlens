package com.anarsoft.trace.agent.runtime.write;


import com.anarsoft.trace.agent.description.ClassDescription;
import com.vmlens.trace.agent.bootstrap.callback.CallbackState;

public class WriteClassDescriptionNormal implements WriteClassDescription {

    public void write(final ClassDescription classDescription) {
        CallbackState.eventQueue.offer(classDescription);
    }
}
