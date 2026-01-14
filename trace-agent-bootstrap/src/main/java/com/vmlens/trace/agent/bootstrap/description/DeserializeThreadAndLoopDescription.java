package com.vmlens.trace.agent.bootstrap.description;


import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DeserializeThreadAndLoopDescription {

    public List<ThreadLoopOrAutomaticTestDescription> deserialize(DataInputStream inputStream) throws IOException {
        List<ThreadLoopOrAutomaticTestDescription> threadLoopOrAutomaticTestDescriptions = new LinkedList<ThreadLoopOrAutomaticTestDescription>();
        try {
            while (true) {
                int type = inputStream.readInt();
                if (type == Constants.TYPE_THREAD_DESCRIPTION) {
                    threadLoopOrAutomaticTestDescriptions.add(ThreadDescription.deserialize(inputStream));
                } else if (type == Constants.TYPE_WHILE_LOOP_DESCRIPTION) {
                    threadLoopOrAutomaticTestDescriptions.add(TestLoopDescription.deserialize(inputStream));
                }
                else if (type == Constants.TYPE_AUTOMATIC_TEST_DESCRIPTION) {
                    threadLoopOrAutomaticTestDescriptions.add(AutomaticTestDescription.deserialize(inputStream));
                } else {
                    throw new RuntimeException("unknown type: " + type);
                }
            }
        } catch (EOFException ignored) {
        }
        return threadLoopOrAutomaticTestDescriptions;
    }
}
