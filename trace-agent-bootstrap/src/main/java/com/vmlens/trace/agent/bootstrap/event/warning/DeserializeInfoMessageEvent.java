package com.vmlens.trace.agent.bootstrap.event.warning;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DeserializeInfoMessageEvent {

    public List<InfoMessageEvent> deserialize(DataInputStream inputStream) throws IOException {
        List<InfoMessageEvent> infoMessageEventList = new LinkedList<>();
        try {
            while (true) {
                infoMessageEventList.add(InfoMessageEvent.deserialize(inputStream));
            }
        } catch (EOFException ignored) {
        }
        return infoMessageEventList;
    }
}
