package com.anarsoft.trace.agent.description;

import com.vmlens.trace.agent.bootstrap.util.Constants;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DeserializeThreadAndLoopDescription {

    public List<ThreadOrLoopDescription> deserialize(DataInputStream inputStream) throws IOException {
        List<ThreadOrLoopDescription> threadOrLoopDescriptions = new LinkedList<ThreadOrLoopDescription>();
        try {
            while (true) {
                int type = inputStream.readInt();
                if (type == Constants.TYPE_THREAD_DESCRIPTION) {
                    threadOrLoopDescriptions.add(ThreadDescription.deserialize(inputStream));
                } else if (type == Constants.TYPE_WHILE_LOOP_DESCRIPTION) {
                    threadOrLoopDescriptions.add(TestLoopDescription.deserialize(inputStream));
                } else {
                    throw new RuntimeException("unknown type: " + type);
                }

            }
        } catch (EOFException ignored) {
        }
        return threadOrLoopDescriptions;
    }
}
