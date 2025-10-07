package com.vmlens.trace.agent.bootstrap.description;

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
        threadOrLoopDescription.add(new ThreadDescription(0, 0, 0, 5L, "threadName"));
        threadOrLoopDescription.add(new TestLoopDescription(2, "name" , 100) );
        threadOrLoopDescription.add(new ThreadDescription(3, 3, 0, 5L, "threadName"));
        threadOrLoopDescription.add(new ThreadDescription(2, 2, 0, 5L, "threadName"));
        threadOrLoopDescription.add(new TestLoopDescription(2, "name" , 100));

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
