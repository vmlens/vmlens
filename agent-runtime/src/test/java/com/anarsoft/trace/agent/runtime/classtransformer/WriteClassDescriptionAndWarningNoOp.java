package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEvent;

public class WriteClassDescriptionAndWarningNoOp implements WriteClassDescriptionAndWarning {

    @Override
    public void write(ClassDescription classDescription) {

    }

    @Override
    public void write(InfoMessageEvent infoMessageEvent) {

    }
}
