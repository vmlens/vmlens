package com.vmlens.trace.agent.bootstrap.description;

import java.io.DataOutputStream;
import java.io.IOException;

public interface ThreadOrLoopDescription {

    void accept(ThreadOrLoopDescriptionVisitor visitor);

    // For Test
    void serialize(DataOutputStream dataOutputStream) throws IOException;

}
