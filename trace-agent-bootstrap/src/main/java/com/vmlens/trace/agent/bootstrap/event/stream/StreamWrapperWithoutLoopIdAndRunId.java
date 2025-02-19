package com.vmlens.trace.agent.bootstrap.event.stream;


import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

import static com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId.EVENT_FILE_POSTFIX;

public class StreamWrapperWithoutLoopIdAndRunId extends AbstractStreamWrapper {
	protected DataOutputStream stream;
	protected final String eventDir;
	protected final String name;

    public StreamWrapperWithoutLoopIdAndRunId(
            String eventDir, String name) {
        this.eventDir = eventDir;
        this.name = name;
    }

	public DataOutputStream getStream() throws Exception {
        if (stream == null) {
            stream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(
                    eventDir + "/" + name + EVENT_FILE_POSTFIX)));
        }

        return stream;
    }

    public void close() throws Exception {
        if (stream != null) {
            stream.close();
        }
    }

    public void flush() {

    }


}