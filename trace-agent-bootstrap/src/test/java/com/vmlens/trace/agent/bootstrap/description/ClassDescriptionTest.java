package com.vmlens.trace.agent.bootstrap.description;

import com.vmlens.trace.agent.bootstrap.description.*;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ClassDescriptionTest {

    @Test
    public void serializeDeserialize() throws IOException {
        // Given
        MethodDescription methodDescription = new MethodDescription("name", 22,
                "desc", 2, 4);

        MethodDescription[] methodDescriptionArray = new MethodDescription[3];
        methodDescriptionArray[0] = methodDescription;
        methodDescriptionArray[1] = methodDescription;
        methodDescriptionArray[2] = methodDescription;

        FieldInClassDescription fieldInClassDescription = new FieldInClassDescription(99, 1, "name",
                "desc", "signature");

        FieldInClassDescription[] fieldInClassDescriptionArray = new FieldInClassDescription[5];
        for (int i = 0; i < fieldInClassDescriptionArray.length; i++) {
            fieldInClassDescriptionArray[i] = fieldInClassDescription;
        }

        String[] interfaces = new String[3];
        interfaces[0] = "first";
        interfaces[1] = "second";
        interfaces[2] = "third";

        ClassDescription classDescription = new ClassDescription("name", "source",
                methodDescriptionArray, fieldInClassDescriptionArray, "superclass", interfaces);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        // When
        new SerializeAndDeserializeClassDescription().serialize(classDescription, dataOutputStream);
        dataOutputStream.close();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);

        List<ClassDescription> classes = new DeserializeClassDescriptions().deserialize(dataInputStream);

        // Then
        assertThat(classes.size(), is(1));
        assertThat(classes.get(0), is(classDescription));
    }
}
