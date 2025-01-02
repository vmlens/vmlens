package com.vmlens.trace.agent.bootstrap.event.warning;

import org.junit.Test;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DeserializeInfoMessageEventTest {

    @Test
    public void serializeDeserialize() throws IOException {
        // Given
        List<InfoMessageEvent> infoMessageEventList = new LinkedList<>();
        infoMessageEventList.add(new InfoMessageEventBuilder()
                .add("firstLine")
                .add("secondLine")
                .build());
        infoMessageEventList.add(new InfoMessageEventBuilder()
                .build());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        // When
        for (InfoMessageEvent infoMessageEvent : infoMessageEventList) {
            infoMessageEvent.serialize(dataOutputStream);
        }

        dataOutputStream.close();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);

        List<InfoMessageEvent> loaded =
                new DeserializeInfoMessageEvent().deserialize(dataInputStream);

        // Then
        assertThat(loaded, is(infoMessageEventList));

    }


}
