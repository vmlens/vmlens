package com.vmlens.trace.agent.bootstrap.description;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AutomaticTestDescription implements SerializableEvent, ThreadLoopOrAutomaticTestDescription {

    private final int id;
    private final String className;

    public AutomaticTestDescription(int id,
                                    String className) {
        this.id = id;
        this.className = className;
    }
    
    static AutomaticTestDescription deserialize(DataInputStream inputStream) throws IOException {
        int id = inputStream.readInt();
        String className = inputStream.readUTF();
        return new AutomaticTestDescription(id, className);
    }

    public int id() {
        return id;
    }

    public String className() {
        return className;
    }

    @Override
    public void accept(ThreadOrLoopDescriptionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void serialize(DataOutputStream stream) throws IOException {
        stream.writeInt(Constants.TYPE_AUTOMATIC_TEST_DESCRIPTION);
        stream.writeInt(id);
        stream.writeUTF(className);
    }

    @Override
    public void serialize(StreamRepository streamRepository) throws Exception {
        DataOutputStream stream = streamRepository.threadName.getStream();
        serialize(stream);
    }
}
