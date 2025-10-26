package com.vmlens.trace.agent.bootstrap.description;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LoopControl implements SerializableEvent  {

    private final int loopId;
    private final int[] intentionalDataRaces;

    public LoopControl(int loopId,
                       int[] intentionalDataRaces) {
        this.loopId = loopId;
        this.intentionalDataRaces = intentionalDataRaces;
    }

    public static LoopControl deserialize(DataInputStream in) throws IOException {
        int loopId = in.readInt();
        int length = in.readInt();
        int[] intentionalDataRaces = new int[length];
        for(int index = 0; index < length; index++) {
            intentionalDataRaces[index] = in.readInt();
        }
        return new LoopControl(loopId,intentionalDataRaces);
    }

    @Override
    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.loopControl.getStream());
    }

    private void serialize(DataOutputStream out) throws IOException {
        out.writeInt(loopId);
        out.writeInt(intentionalDataRaces.length);
        for(int id : intentionalDataRaces) {
            out.writeInt(id);
        }
    }

    public int loopId() {
        return loopId;
    }

    public int[] intentionalDataRaces() {
        return intentionalDataRaces;
    }
}
