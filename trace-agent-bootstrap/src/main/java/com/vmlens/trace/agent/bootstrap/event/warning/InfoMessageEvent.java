package com.vmlens.trace.agent.bootstrap.event.warning;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

public class InfoMessageEvent implements SerializableEvent {

    private final String[] text;

    public InfoMessageEvent(String[] text) {
        this.text = text;
    }

    public static InfoMessageEvent deserialize(DataInputStream stream) throws IOException {
        int arrayLength = stream.readInt();
        String[] text = new String[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            text[i] = stream.readUTF();
        }
        return new InfoMessageEvent(text);
    }

    @Override
    public void serialize(StreamRepository streamRepository) throws Exception {
        DataOutputStream stream = streamRepository.agentLog.getStream();
        serialize(stream);
    }

    public void serialize(DataOutputStream stream) throws IOException {
        stream.writeInt(text.length);
        for (String line : text) {
            stream.writeUTF(line);
        }
    }

    public void writeToStream(PrintStream output) {
        for (String line : text) {
            output.println(line);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoMessageEvent that = (InfoMessageEvent) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(text);
    }

    @Override
    public String toString() {
        return "InfoMessageEvent{" +
                "text=" + Arrays.toString(text) +
                '}';
    }
}
