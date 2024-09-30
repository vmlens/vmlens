package com.anarsoft.trace.agent.description;

import org.junit.Test;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DeserializeThreadAndLoopDescriptionTest {

    @Test
    public void serializeDeserialize() throws IOException {
        // Given
        List<ThreadOrLoopDescription> threadOrLoopDescription = new LinkedList<>();
        threadOrLoopDescription.add(new ThreadDescription(0, 5L, "threadName"));
        threadOrLoopDescription.add(new WhileLoopDescription(2, "name"));
        threadOrLoopDescription.add(new ThreadDescription(0, 5L, "threadName"));
        threadOrLoopDescription.add(new ThreadDescription(0, 5L, "threadName"));
        threadOrLoopDescription.add(new WhileLoopDescription(2, "name"));

        // When
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        // When
        for (ThreadOrLoopDescription event : threadOrLoopDescription) {
            event.serialize(dataOutputStream);
        }
        dataOutputStream.close();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);

        List<ThreadOrLoopDescription> loaded = new DeserializeThreadAndLoopDescription().deserialize(dataInputStream);

        // Then
        assertThat(loaded, is(threadOrLoopDescription));
    }
}
