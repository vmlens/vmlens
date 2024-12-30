package com.anarsoft.trace.agent.runtime.classanalyzer;

import com.anarsoft.trace.agent.description.ClassDescription;
import com.anarsoft.trace.agent.runtime.LoadClassArray;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescription;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionDuringStartup;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForAnalyze;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForAnalyze;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.junit.Test;
import org.objectweb.asm.ClassReader;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ClassVisitorAnalyzeTest {

    @Test
    public void volatileFieldAccess() throws IOException {
        // Given
        byte[] classArray = new LoadClassArray().load("com.vmlens.test.guineaPig.VolatileFieldAccess");
        ClassReader classReader = new ClassReader(classArray);
        MethodRepositoryForAnalyze methodRepositoryForAnalyze = mock(MethodRepositoryForAnalyze.class);
        FieldRepositoryForAnalyze fieldRepositoryForAnalyze = mock(FieldRepositoryForAnalyze.class);
        TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList = new TLinkedList<>();
        WriteClassDescription writeClassDescription = new WriteClassDescriptionDuringStartup(classAnalyzedEventList);
        ClassVisitorAnalyze classVisitorAnalyze = new ClassVisitorAnalyze(methodRepositoryForAnalyze,
                fieldRepositoryForAnalyze, writeClassDescription);

        // When
        classReader.accept(classVisitorAnalyze, 0);

        // Then
        ClassDescription classDescription = classAnalyzedEventList.get(0).element();
        assertThat(classDescription.name(), is("com/vmlens/test/guineaPig/VolatileFieldAccess"));
        assertThat(classDescription.methodArray()[1].name(), is("update"));
    }

}
