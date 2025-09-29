package com.vmlens.nottraced.agent.write;


import com.vmlens.transformed.agent.bootstrap.description.ClassDescription;
import com.vmlens.transformed.agent.bootstrap.event.warning.InfoMessageEvent;


public interface WriteClassDescriptionAndWarning {

    void write(ClassDescription classDescription);

    void write(InfoMessageEvent infoMessageEvent);

}
