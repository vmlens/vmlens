package com.anarsoft.trace.agent.preanalyzed.serialize;


import com.anarsoft.trace.agent.preanalyzed.model.PackageOrClass;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.ClassTypeFilter;
import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.MethodTypeThreadStart;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.junit.Test;

import java.io.*;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DeserializePackageOrClassTest {

    @Test
    public void serializeAndDeserialize() throws IOException {
        // Given
        TLinkedList<TLinkableWrapper<PackageOrClass>> packageOrClasses = new TLinkedList<>();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        PackageOrClass filteredPackage = new PackageOrClass("test.java",
                ClassTypeFilter.SINGLETON,
                new PreAnalyzedMethod[0]);


        PreAnalyzedMethod[] preAnalyzedMethods = new PreAnalyzedMethod[1];
        preAnalyzedMethods[0] = new PreAnalyzedMethod("´start", "()V", MethodTypeThreadStart.SINGLETON);
        PackageOrClass withMethod = new PackageOrClass("with.java",
                ClassTypeFilter.SINGLETON, preAnalyzedMethods);


        packageOrClasses.add(wrap(filteredPackage));
        packageOrClasses.add(wrap(withMethod));

        SerializePackageOrClass serializePackageOrClass = new SerializePackageOrClass();
        DeserializePackageOrClass deserializePackageOrClass = new DeserializePackageOrClass();

        // When
        serializePackageOrClass.serialize(packageOrClasses, dataOutputStream);

        dataOutputStream.close();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);

        TLinkedList<TLinkableWrapper<PackageOrClass>> loaded = deserializePackageOrClass.deserialize(dataInputStream);

        // Then
        assertThat(loaded.get(0).element(), is(filteredPackage));
        assertThat(loaded.get(1).element(), is(withMethod));

    }


}
