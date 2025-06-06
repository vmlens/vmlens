package com.vmlens.nottraced.agent.write;

import com.vmlens.trace.agent.bootstrap.description.ClassDescription;
import com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEvent;

public class WriteClassDescriptionAndWarningWrapper implements WriteClassDescriptionAndWarning {

    private WriteClassDescriptionAndWarning delegate;

    public WriteClassDescriptionAndWarningWrapper(WriteClassDescriptionAndWarning delegate) {
        this.delegate = delegate;
    }

    @Override
    public synchronized void write(ClassDescription classDescription) {
        delegate.write(classDescription);
    }

    @Override
    public synchronized void write(InfoMessageEvent infoMessageEvent) {
        delegate.write(infoMessageEvent);
    }

    public synchronized void setWriteClassDescriptionAndWarning(WriteClassDescriptionAndWarning delegate) {
        this.delegate = delegate;
    }

}
