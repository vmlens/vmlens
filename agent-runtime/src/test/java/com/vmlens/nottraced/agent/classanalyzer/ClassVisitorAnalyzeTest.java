package com.vmlens.nottraced.agent.classanalyzer;

import com.vmlens.transformed.agent.bootstrap.description.ClassDescription;
import com.vmlens.nottraced.agent.LoadClassArray;
import com.vmlens.nottraced.agent.write.WriteClassDescriptionAndWarning;
import com.vmlens.nottraced.agent.write.WriteClassDescriptionAndWarningDuringStartup;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.transformed.agent.bootstrap.fieldrepository.FieldRepositoryForTransform;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper;
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
        byte[] classArray = new LoadClassArray().load("com.vmlens.test.guineapig.VolatileFieldAccess");
        ClassReader classReader = new ClassReader(classArray);
        MethodRepositoryForTransform methodRepositoryForAnalyze = mock(MethodRepositoryForTransform.class);
        FieldRepositoryForTransform fieldRepositoryForAnalyze = mock(FieldRepositoryForTransform.class);
        TLinkedList<TLinkableWrapper<ClassDescription>> classAnalyzedEventList = new TLinkedList<>();
        WriteClassDescriptionAndWarning writeClassDescription = new WriteClassDescriptionAndWarningDuringStartup(classAnalyzedEventList, new TLinkedList<>());
        ClassVisitorAnalyze classVisitorAnalyze = new ClassVisitorAnalyze(methodRepositoryForAnalyze,
                fieldRepositoryForAnalyze, writeClassDescription);

        // When
        classReader.accept(classVisitorAnalyze, 0);

        // Then
        ClassDescription classDescription = classAnalyzedEventList.get(0).element();
        assertThat(classDescription.name(), is("com/vmlens/test/guineapig/VolatileFieldAccess"));
        assertThat(classDescription.methodArray()[1].name(), is("update"));
    }

}
