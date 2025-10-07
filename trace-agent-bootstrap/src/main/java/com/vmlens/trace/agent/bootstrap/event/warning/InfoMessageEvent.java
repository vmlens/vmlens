package com.vmlens.trace.agent.bootstrap.event.warning;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class InfoMessageEvent implements SerializableEvent {

    private final String[] text;

    public InfoMessageEvent(String[] text) {
        this.text = text;
    }


    public static InfoMessageEvent fromException(int loopId, Exception exp) {
        String[] text = new String[exp.getStackTrace().length + 2];
        text[0] = "Loop:" + loopId;
        text[1] = exp.getMessage();
        int index = 2;
        for(StackTraceElement element :  exp.getStackTrace()) {
            text[index] = element.getClassName() + "." + element.getMethodName();
            index++;
        }
        return new InfoMessageEvent(text);
    }


    public static InfoMessageEvent deserialize(DataInputStream stream) throws IOException {
        int arrayLength = stream.readInt();
        String[] text = new String[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            text[i] = stream.readUTF();
        }
        return new InfoMessageEvent(text);
    }

    private static String shortenToUtfLimit(String s) {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        int maxBytes = 65000;
        if (bytes.length <= maxBytes) {
            return s;
        }

        // find safe cut point so we don’t split a multi-byte UTF-8 character
        int cut = maxBytes;
        while (cut > 0 && (bytes[cut] & 0xC0) == 0x80) {
            cut--;
        }

        return new String(bytes, 0, cut, StandardCharsets.UTF_8);
    }

    @Override
    public void serialize(StreamRepository streamRepository) throws Exception {
        DataOutputStream stream = streamRepository.agentLog.getStream();
        serialize(stream);
    }

    public void serialize(DataOutputStream stream) throws IOException {
        stream.writeInt(text.length);
        for (String line : text) {
            try{
                stream.writeUTF(line);
            } catch(java.io.UTFDataFormatException exp) {
                stream.writeUTF("s:" + shortenToUtfLimit(line));
            }
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
