package com.anarsoft.trace.agent.runtime.write;


import com.anarsoft.trace.agent.description.ClassDescription;
import com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEvent;


public interface WriteClassDescriptionAndWarning {

    void write(ClassDescription classDescription);

    void write(InfoMessageEvent infoMessageEvent);

}
