package com.anarsoft.trace.agent.preanalyzed.serialize;

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PackageOrClass;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PreAnalyzedMethod;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl.ClassTypeFilter;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.ThreadStart;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DeserializePackageOrClassTest {

    @Test
    public void serializeAndDeserializeFiltered() throws IOException {
        // Given
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        PackageOrClass filteredPackage = new PackageOrClass("test.java",
                ClassTypeFilter.SINGLETON,
                new PreAnalyzedMethod[0]);

        // When
        filteredPackage.serialize( dataOutputStream);
        dataOutputStream.close();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);

        PackageOrClass loaded = PackageOrClass.deserialize(dataInputStream);

        // Then
        assertThat(loaded, is(filteredPackage));
    }

    @Test
    public void serializeAndDeserializeWithMethod() throws IOException {
        // Given
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        PreAnalyzedMethod[] preAnalyzedMethods = new PreAnalyzedMethod[1];
        preAnalyzedMethods[0] = new PreAnalyzedMethod("´start", "()V", ThreadStart.SINGLETON);
        PackageOrClass withMethod = new PackageOrClass("with.java",
                ClassTypeFilter.SINGLETON, preAnalyzedMethods);


        // When
        withMethod.serialize(dataOutputStream);

        dataOutputStream.close();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);

        PackageOrClass loaded = PackageOrClass.deserialize(dataInputStream);

        // Then
        assertThat(loaded, is(withMethod));

    }


}
