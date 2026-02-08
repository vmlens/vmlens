package com.vmlens.trace.agent.bootstrap.event.stream;


import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class StreamWrapperWithLoopIdAndRunId extends AbstractStreamWrapper {

    public static final String EVENT_FILE_POSTFIX = ".vmlens";
    protected DataOutputStream stream;
    protected final String eventDir;
    protected final String name;

    public StreamWrapperWithLoopIdAndRunId(
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


}
