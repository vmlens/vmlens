package com.vmlens.trace.agent.bootstrap.event;

import java.io.DataOutputStream;
import java.nio.ByteBuffer;


public class StackTraceEvent implements StaticEvent   {


    public static final int MIN_LENGTH = 4;

    private final long threadId;
    private final StackTraceElement[] stackTraceElementArray;

    public StackTraceEvent(long threadId, StackTraceElement[] stackTraceElementArray) {
        this.threadId = threadId;
        this.stackTraceElementArray = stackTraceElementArray.clone();
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        DataOutputStream stream = streamRepository.stackTrace.getStream();
        stream.writeLong(threadId);
        stream.writeInt(stackTraceElementArray.length - MIN_LENGTH);
        for (int i = MIN_LENGTH; i < stackTraceElementArray.length; i++) {
            stream.writeUTF(convertNullToEmptyString(stackTraceElementArray[i].getClassName()));
            stream.writeUTF(convertNullToEmptyString(stackTraceElementArray[i].getMethodName()));
            stream.writeUTF(convertNullToEmptyString(stackTraceElementArray[i].getFileName()));
            stream.writeInt(stackTraceElementArray[i].getLineNumber()); //stackTraceElementArray[i].getLineNumber()
        }
    }


    public String convertNullToEmptyString(String in) {
        if (in == null) {
            return "";
        } else {
            return in;
        }
    }

    @Override
    public void serialize(ByteBuffer buffer) throws Exception {
        // ToDo
    }


}
